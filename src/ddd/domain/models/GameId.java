package ddd.domain.models;

import ddd.core.AggregateIdentifier;

public record GameId(String gameId) implements AggregateIdentifier {}
