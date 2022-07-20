package ddd.core;

import ddd.domain.exceptions.IllegalChessMoveException;

/**
 * Event sourced handler that defines a state change on a EventSourcedAggregate
 * @param <A>
 * @param <E>
 */
public interface EventSourcingHandler<A extends EventSourcedAggregate<?>, E extends DomainEvent<?>> {
    A apply(A aggregate, E event);
}
