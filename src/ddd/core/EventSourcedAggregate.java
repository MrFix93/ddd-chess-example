package ddd.core;

import java.util.List;
import java.util.Map;

public abstract class EventSourcedAggregate<TId extends AggregateIdentifier> extends Entity<TId> {
    protected EventProcessor eventProcessor = SimpleEventProcessor.getInstance();

    protected EventSourcedAggregate(TId id) {
        super(id);
    }

    /**
     * Build an instance of the aggregate based on given events
     * @param events for which eventsourcing handlers are defined
     * @return a new instance of the aggregate
     */
    public EventSourcedAggregate<TId> build(List<DomainEvent<TId>> events) {
        final var handlers = getHandlers();
        final var aggregate = this.initialize();

        for (DomainEvent<TId> event : events) {
            handlers.get(event.getClass()).apply(aggregate, event);
        }

        return aggregate;
    }

    /**
     * Return the event sourcing handlers for this aggregate
     * @return a map of domain type mapped to eventsourcing handler
     */
    protected abstract <E extends EventSourcedAggregate<TId>> Map<Class<?>, EventSourcingHandler<E, DomainEvent<TId>>> getHandlers();

    /**
     * Create a new instance of the aggregate
     * @return
     */
    public abstract EventSourcedAggregate<TId> initialize();
}
