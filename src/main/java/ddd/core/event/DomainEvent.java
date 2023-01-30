package main.java.ddd.core.event;

import main.java.ddd.core.domain.AggregateIdentifier;

import java.util.Objects;

public abstract class DomainEvent<Id extends AggregateIdentifier> {
    private final Id id;

    protected DomainEvent(Id id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainEvent<?> that = (DomainEvent<?>) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DomainEvent{" +
                "id=" + id +
                '}';
    }

    public Id getId() {
        return id;
    }
}
