package ddd.core;

public class SimpleEventBus implements EventBus {
    @Override
    public void publish(DomainEvent<?> domainEvent) {
        System.out.println("Publish event" + domainEvent);
    }

    @Override
    public <T extends DomainEvent<?>> void subscribe(EventHandler<T> eventListener) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
