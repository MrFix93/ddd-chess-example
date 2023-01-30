package main.java.ddd.core.cqrs;

import main.java.ddd.core.domain.AggregateIdentifier;

import java.util.Objects;

public abstract class Command<Id extends AggregateIdentifier> {
    private final Id id;

    protected Command(Id id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command<?> command = (Command<?>) o;
        return id.equals(command.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Command{" +
                "id=" + id +
                '}';
    }

    public Id getId() {
        return id;
    }
}
