package main.java.ddd.core.event;

public interface EventHandler<T extends DomainEvent<?>> {
    /**
     * Handle the event.
     * @param event
     */
    void handle(T event);
}
