package ddd.domain.events;

import ddd.core.DomainEvent;
import ddd.domain.models.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class GameStartedEvent extends DomainEvent<GameId> {
    private final Board board;
    private final GameState gameState;
    private final Player whitePlayer;
    private final Player blackPlayer;
    private final ChessColor firstMove;

    public GameStartedEvent(GameId id, Board board, GameState gameState, Player whitePlayer, Player blackPlayer, ChessColor firstMove) {
        super(id);
        this.board = board;
        this.gameState = gameState;
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.firstMove = firstMove;
    }
}
