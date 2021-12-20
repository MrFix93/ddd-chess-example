package ddd.core;

import ddd.domain.models.AggregateIdentifier;
import ddd.domain.models.GameId;

import java.util.List;

public interface EventRepository {

    /**
     * Persist event
     * @param domainEvent
     */
    void addEvent(DomainEvent<GameId> domainEvent);

    /**
     * Retrieve all events by ID
     * @param id
     * @return
     */
     List<DomainEvent<GameId>> getEventListById(AggregateIdentifier id);
}
