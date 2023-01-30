package ddd.core.domain;

public abstract class Entity<Id extends AggregateIdentifier> {
    private final Id id;

    public Entity(Id id) {
        this.id = id;
    }

    public Id geT() {
        return id;
    }
}