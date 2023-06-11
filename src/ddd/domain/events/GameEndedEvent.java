package ddd.domain.events;

import ddd.core.DomainEvent;
import ddd.domain.models.EndingReason;
import ddd.domain.models.GameId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ddd.domain.models.Move;
import ddd.domain.models.Player;

import java.util.List;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class GameEndedEvent extends DomainEvent<GameId> {
    private final EndingReason reason;
    private final List<Move> movesMade;
    private final Player winner;

    public GameEndedEvent(GameId id, List<Move> movesMade, EndingReason reason, Player winner) {
        super(id);
        this.reason = reason;
        this.movesMade = movesMade;
        this.winner = winner;
    }
}
