package main.java.ddd.core.eventsourcing;

import main.java.ddd.core.domain.AggregateIdentifier;
import main.java.ddd.core.event.DomainEvent;
import main.java.ddd.core.domain.Entity;

import java.util.List;
import java.util.Map;

public abstract class EventSourcedAggregate<Id extends AggregateIdentifier> extends Entity<Id> {
    protected EventSourcedAggregate(Id id) {
        super(id);
    }

    /**
     * Build an instance of the aggregate based on given events
     * @param events for which eventsourcing handlers are defined
     * @return a new instance of the aggregate
     */
    public EventSourcedAggregate<Id> build(List<DomainEvent<Id>> events) {
        throw new UnsupportedOperationException("Implement yourself");
    }

    /**
     * Return the event sourcing handlers for this aggregate
     * @return a map of domain type mapped to eventsourcing handler
     */
    public abstract <E extends EventSourcedAggregate<Id>> Map<Class<?>, EventSourcingHandler<E, DomainEvent<Id>>> getHandlers();

    /**
     * Create a new instance of the aggregate
     * @return
     */
    public abstract EventSourcedAggregate<Id> initialize();
}
