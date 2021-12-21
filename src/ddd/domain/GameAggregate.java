package ddd.domain;

import ddd.core.EventSourcedAggregate;
import ddd.core.EventSourcingHandler;
import ddd.domain.events.GameEndedEvent;
import ddd.domain.events.GameStartedEvent;
import ddd.domain.events.MoveMadeEvent;
import ddd.domain.exceptions.IllegalChessMoveException;
import ddd.domain.exceptions.PolicyViolatedException;
import ddd.domain.models.*;
import ddd.domain.models.rules.*;
import ddd.domain.printer.ConsoleBoardPrinter;
import ddd.domain.printer.FancyBoardPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ddd.domain.models.GameState.FINISHED;

public class GameAggregate extends EventSourcedAggregate<GameId> {

    private GameId gameId;
    private Board board;
    private GameState gameState;

    public static final List<ChessMoveRule> rules = List.of(
            new TargetIsInAttackRangeRule(),
            new TargetIsInNormalRangeRule(),
            new PieceShouldBeOnStartSquareRule(),
            new PieceCanNotAttackOwnColorRule(),
            new PathIsObstructedRule(),
            new MoveShouldNotResultInCheckStateRule()
    );

    private final ConsoleBoardPrinter printer = new FancyBoardPrinter(System.out);

    private Player whitePlayer;
    private Player blackPlayer;
    private List<Move> movesMade;

    private ChessColor firstMove;

    public GameAggregate(GameId aggregateIdentifier) {
        super(aggregateIdentifier);
    }

    public void start(GameId gameId, Player player1, Player player2) {
        final GameStartedEvent gameStartedEvent = new GameStartedEvent(gameId, BoardCreator.fullBoard(), GameState.STARTED, player1, player2, ChessColor.WHITE);
        apply(gameStartedEvent);
    }

    public void make(Player player, Move move) throws PolicyViolatedException {
        if (gameState != GameState.STARTED) {
            throw new PolicyViolatedException("Cannot make move when game is not started");
        }

        if (!inTurn(player.getColor())) {
            throw new PolicyViolatedException("It's not your turn");
        }

        if (player.getColor() != move.getPiece().getColor()) {
            throw new PolicyViolatedException("You cannot move the pieces of your opponent");
        }

        try {
            board.make(move);
        } catch (IllegalChessMoveException e) {
            throw new PolicyViolatedException("Unable to make move", e);
        }

        apply(new MoveMadeEvent(gameId, move));

        if (CheckMateRule.isCheckMate(board, ChessColor.WHITE)) {
            apply(new GameEndedEvent(gameId, movesMade, EndingReason.BLACK_WON, blackPlayer));
        }

        if (CheckMateRule.isCheckMate(board, ChessColor.BLACK)) {
            apply(new GameEndedEvent(gameId, movesMade, EndingReason.WHITE_WON, whitePlayer));
        }
    }

    private boolean inTurn(ChessColor color) {
        return !((movesMade.size() + 1) % 2 == 1 && color == firstMove.invert());
    }

    public static EventSourcingHandler<GameAggregate, GameStartedEvent> gameStarted() {
        return (aggregate, event) -> {
            aggregate.gameId = event.getId();
            aggregate.board = event.getBoard();
            aggregate.gameState = event.getGameState();
            aggregate.whitePlayer = event.getWhitePlayer();
            aggregate.blackPlayer = event.getBlackPlayer();
            aggregate.firstMove = event.getFirstMove();
            aggregate.movesMade = new ArrayList<>();

            return aggregate;
        };
    }

    public static EventSourcingHandler<GameAggregate, MoveMadeEvent> moveMade() {
        return (aggregate, event) -> {
            final Move move = event.getMove();
            aggregate.board.commit(move);
            aggregate.movesMade.add(event.getMove());

            return aggregate;
        };
    }

    public static EventSourcingHandler<GameAggregate, GameEndedEvent> gameEnded() {
        return (aggregate, event) -> {
            aggregate.gameState = FINISHED;

            return aggregate;
        };
    }

    public void printBoard() {
        printer.print(board);
    }

    @Override
    public Map<Class, EventSourcingHandler> getHandlers() {
        return Map.of(MoveMadeEvent.class, moveMade(),
                GameEndedEvent.class, gameEnded(),
                GameStartedEvent.class, gameStarted()
        );
    }

    @Override
    public GameAggregate initialize() {
        return new GameAggregate(gameId);
    }
}
