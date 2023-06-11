package ddd.core;


import ddd.core.domain.AggregateIdentifier;
import ddd.core.event.DomainEvent;

public interface EventProcessor {
    /**
     * Facade to transactionally proces the domain event
     * @param event
     * @param <A>
     */
    <A extends AggregateIdentifier> void raise(DomainEvent<A> event);
}
