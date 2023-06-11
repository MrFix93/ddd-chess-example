package ddd.core.eventsourcing;

import ddd.core.event.DomainEvent;

/**
 * Event sourced handler that defines a state change on a EventSourcedAggregate
 * @param <A>
 * @param <E>
 */
public interface EventSourcingHandler<A extends EventSourcedAggregate<?>, E extends DomainEvent<?>> {
    A apply(A aggregate, E event);
}
