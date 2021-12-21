package ddd.core;

import java.util.List;
import java.util.Map;

public abstract class EventSourcedAggregate<TId extends AggregateIdentifier> extends Entity<TId> {
    protected static EventProcessor eventProcessor = SimpleEventProcessor.getInstance();

    protected EventSourcedAggregate(TId id) {
        super(id);
    }

    protected static void apply(DomainEvent<? extends AggregateIdentifier> event) {
        eventProcessor.raise(event);
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
