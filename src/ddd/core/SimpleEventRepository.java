package ddd.core;

import ddd.domain.models.GameId;

import java.util.ArrayList;
import java.util.List;

public class SimpleEventRepository implements EventRepository<DomainEvent<GameId>> {
    private final List<DomainEvent<GameId>> eventList;

    public SimpleEventRepository() {
        this.eventList = new ArrayList<>();
    }

    public void addEvent(DomainEvent<GameId> domainEvent) {
        eventList.add(domainEvent);
    }

    public List<DomainEvent<GameId>> getEventListById(AggregateIdentifier id) {
        return eventList.stream()
                .filter(event -> event.getId().equals(id))
                .toList();
    }
}
