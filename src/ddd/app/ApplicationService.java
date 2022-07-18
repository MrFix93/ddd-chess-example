package ddd.app;

import ddd.core.SimpleEventRepository;
import ddd.core.SimpleEventProcessor;
import ddd.domain.command.MakeMoveCommand;
import ddd.domain.command.StartGameCommand;
import ddd.core.DomainEvent;
import ddd.domain.GameAggregate;
import ddd.domain.exceptions.PolicyViolatedException;
import ddd.domain.models.ChessColor;
import ddd.domain.models.GameId;
import ddd.domain.models.Move;
import ddd.domain.models.Player;

import java.util.List;

public class ApplicationService {
    private final SimpleEventRepository repository = SimpleEventProcessor.getInstance().getEventRepository();

    public void doCommand(StartGameCommand command) {
        final GameId id = command.getId();

        final List<DomainEvent<GameId>> eventList = repository.getEventListById(id);
        final GameAggregate aggregate = (GameAggregate) new GameAggregate(id).build(eventList);

        aggregate.start(id, new Player(command.getWhitePlayer(), ChessColor.WHITE), new Player(command.getBlackPlayer(), ChessColor.BLACK));
    }

    public void doCommand(MakeMoveCommand command) throws PolicyViolatedException {
        final GameId id = command.getId();

        final List<DomainEvent<GameId>> eventList = repository.getEventListById(id);
        final GameAggregate aggregate = (GameAggregate) new GameAggregate(id).build(eventList);

        final Move move = new Move(command.getPiece(), command.getStartPosition(), command.getEndPosition());

        aggregate.make(command.getPlayer(), move);
    }

    public void print(GameId id) {
        final List<DomainEvent<GameId>> eventList = repository.getEventListById(id);
        final GameAggregate aggregate = (GameAggregate) new GameAggregate(id).build(eventList);

        aggregate.printBoard();
    }
}
