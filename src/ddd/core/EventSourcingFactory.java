package ddd.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class EventSourcingFactory<A extends EventSourcedAggregate<T>, T extends AggregateIdentifier> {
    private Constructor<A> ctor;

    private T gameId;
    private List<DomainEvent<T>> events;

    public EventSourcingFactory(Class<A> ctor) {
        try {
            this.ctor = ctor.getConstructor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public EventSourcingFactory<A, T> withInstance(T gameId) {
        this.gameId = gameId;
        return this;
    }

    public EventSourcingFactory<A, T> fromEvents(List<DomainEvent<T>> domainEvents) {
        this.events = domainEvents;
        return this;
    }

    private A applyEvents(List<DomainEvent<T>> events, A aggregateInstance) {
        final var handlers = aggregateInstance.getHandlers();
        final A aggregate = (A) aggregateInstance.initialize();

        for (DomainEvent<T> event : events) {
            handlers.get(event.getClass()).apply(aggregate, event);
        }

        return aggregate;
    }

    public A build() {
        final A aggregate;
        try {
            aggregate = this.ctor.newInstance(gameId);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("Could not instantiate this aggregate");
        }

        return applyEvents(events, aggregate);
    }
}
