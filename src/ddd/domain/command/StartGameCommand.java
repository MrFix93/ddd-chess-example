package ddd.domain.command;

import ddd.core.Command;
import ddd.domain.models.GameId;
import lombok.Getter;

@Getter
public class StartGameCommand extends Command<GameId> {
    private final String whitePlayer;
    private final String blackPlayer;

    public StartGameCommand(GameId id, String whitePlayer, String blackPlayer) {
        super(id);
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
    }
}


