package main.java.ddd.core.domain;

import main.java.ddd.core.event.DomainEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represents an aggregate-root of a domain aggregate (DDD). An aggregate-root is always an entity.
 * @param <Id> The type of the Id of the entity.
 */
public abstract class AggregateRoot<Id extends AggregateIdentifier> extends Entity<Id> {
    private final List<DomainEvent<Id>> events;
    
    /**
     * Indication whether the aggregate is replaying events (true) or not (false).
     */
    private boolean isReplaying = false;
    
    /**
     * The current version after handling any commands.
     */
    private final AtomicInteger version;

    /**
     * The original version of the aggregate after replaying all events in the event-store.
     */
    private final AtomicInteger originalVersion;

    /**
     * Constructor for creating an empty aggregate.
     * @param id The unique id of the aggregate-root.
     */
    public AggregateRoot(Id id)  {
        super(id);
        originalVersion = new AtomicInteger(0);
        version = new AtomicInteger(0);
        events = new ArrayList<>();
    }

    /**
     * Constructor for creating an aggregate of which the state is initialized by
     * replaying the list of events specified.
     * @param id The unique Id of the aggregate.
     * @param events The events to replay.
     */
    public AggregateRoot(Id id, List<DomainEvent<Id>> events) {
        this(id);
        isReplaying = true;
        
        for (var event : events) {
            handle(event);
            originalVersion.incrementAndGet();
            version.incrementAndGet();
        }
        isReplaying = false;
    }


    /**
     * Let the aggregate handle an event and save it in the list of events
     * so it can be used outside the aggregate (persisted, published on a bus, ...).
     * Use getEvents to retrieve the list of events.
     * @param domainEvent The event to handle.
     */
    protected void raiseEvent(DomainEvent<Id> domainEvent) {
        // let the derived aggregate handle the event
        handle(domainEvent);

        // save the event so it can be published outside the aggregate
        events.add(domainEvent);
        version.incrementAndGet();
    }

    /**
     * Clear the list of events that occurred while handling a command.
     */
    public void clearEvents() {
        events.clear();
    }

    /**
     * Handle a specific event. Derived classes should implement this method
     * and let it handle every event type that is supported.
     *
     * @param domainEvent The event to handle.
     */
    protected abstract void handle(DomainEvent<Id> domainEvent);
    
    /**
     * The list of events that occurred while handling commands.
     */
    public List<DomainEvent<Id>> getEvents() {
        return events;
    }
    
    protected boolean isReplaying() {
        return isReplaying;
    }

    public int getVersion() {
        return version.get();
    }

    public int getOriginalVersion() {
        return originalVersion.get();
    }
}