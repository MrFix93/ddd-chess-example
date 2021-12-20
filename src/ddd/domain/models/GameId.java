package ddd.domain.models;

import ddd.core.AggregateIdentifier;

public class GameId implements AggregateIdentifier {
    private String gameId;

    public GameId(String gameId) {
        this.gameId = gameId;
    }
}
