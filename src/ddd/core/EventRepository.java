package ddd.core;


import java.util.List;

public interface EventRepository<T extends DomainEvent<?>> {

    /**
     * Persist event
     * @param domainEvent
     */
    void addEvent(T domainEvent);

    /**
     * Retrieve all events by ID
     * @param id
     * @return
     */
     List<T> getEventListById(AggregateIdentifier id);
}
