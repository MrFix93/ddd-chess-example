package ddd.core;

public class SimpleEventProcessor implements EventProcessor {
    private static SimpleEventProcessor instance = new SimpleEventProcessor(new SimpleEventRepository(), new SimpleEventBus());

    private final SimpleEventRepository eventRepository;
    private final EventBus eventBus;

    private SimpleEventProcessor(SimpleEventRepository eventRepository, EventBus eventBus) {
        this.eventRepository = eventRepository;
        this.eventBus = eventBus;
    }

    public static SimpleEventProcessor getInstance() {
        return instance;
    }

    public SimpleEventRepository getEventRepository() {
        return eventRepository;
    }

    @Override
    public void raise(DomainEvent event) {
        eventRepository.addEvent(event);
        eventBus.publish(event);
    }
}
