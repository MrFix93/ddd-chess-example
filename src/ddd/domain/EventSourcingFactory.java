package ddd.domain;

import ddd.core.AggregateIdentifier;
import ddd.core.DomainEvent;
import ddd.core.EventSourcedAggregate;
import ddd.core.EventSourcingHandler;

import java.util.List;
import java.util.Map;

public class EventSourcingFactory<TId extends AggregateIdentifier> {

    public EventSourcedAggregate<TId> build(List<DomainEvent<TId>> events, EventSourcedAggregate<TId> aggregate) {
        final var aggregateCopy = aggregate.initialize();
        Map<Class<?>, EventSourcingHandler<EventSourcedAggregate<TId>, DomainEvent<TId>>> handlers = aggregateCopy.getHandlers();

        for (DomainEvent<TId> event : events) {
            handlers.get(event.getClass()).apply(aggregateCopy, event);
        }

        return aggregateCopy;
    }
}
