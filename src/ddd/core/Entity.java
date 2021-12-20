package ddd.core;

import ddd.domain.models.AggregateIdentifier;

public abstract class Entity<TId extends AggregateIdentifier> {

    private final TId _id;

    Entity(TId id) {
        _id = id;
    }

    public TId getId() {
        return _id;
    }
}