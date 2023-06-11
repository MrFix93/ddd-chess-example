package ddd.domain.events;

import ddd.core.DomainEvent;
import ddd.domain.models.GameId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ddd.domain.models.Move;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MoveMadeEvent extends DomainEvent<GameId> {
    private final Move move;

    public MoveMadeEvent(GameId id, Move move) {
        super(id);
        this.move = move;
    }
}
