package ddd.core;

interface EventHandler<T extends DomainEvent<?>> {
    /**
     * Handle the event
     * @param event
     */
    void handle(T event);
}
