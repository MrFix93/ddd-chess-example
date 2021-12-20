package ddd.core;

interface EventBus {
    /**
     * Publish event to it's subscribers.
     * @param domainEvent
     */
    void publish(DomainEvent<?> domainEvent);

    /**
     * Register a subscriber that gets called when a new event is published
     * @param eventListener
     * @param <T>
     */
    <T extends DomainEvent<?>> void subscribe(EventHandler<T> eventListener);
}
