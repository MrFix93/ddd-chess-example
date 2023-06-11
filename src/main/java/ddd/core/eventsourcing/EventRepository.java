package ddd.core.eventsourcing;

import ddd.core.domain.AggregateIdentifier;
import ddd.core.event.DomainEvent;

import java.util.List;

public interface EventRepository<Id extends DomainEvent<?>> {

    /**
     * Persist event
     * @param domainEvent
     */
    void addEvent(Id domainEvent);

    /**
     * Retrieve all events by ID
     * @param id
     * @return
     */
     List<Id> getEventListById(AggregateIdentifier id);
}
