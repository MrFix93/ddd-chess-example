package main.java.ddd.core.eventsourcing;

import main.java.ddd.core.event.DomainEvent;
import main.java.ddd.core.event.EventHandler;


interface EventBus {
    /**
     * Publish event to its subscribers.
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
