package ddd.core;


public interface EventProcessor {
    /**
     * Facade to transactionally proces the domain event
     * @param event
     * @param <A>
     */
    <A extends AggregateIdentifier> void raise(DomainEvent<A> event);
}
