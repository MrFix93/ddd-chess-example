package ddd.app;

import ddd.domain.command.MakeMoveCommand;
import ddd.domain.command.StartGameCommand;
import ddd.domain.exceptions.PolicyViolatedException;
import ddd.domain.models.*;
import ddd.domain.models.pieces.Pawn;

public class App {

    public static void main(String[] args) throws PolicyViolatedException {
        final ApplicationService applicationService = new ApplicationService();

        final GameId gameId = new GameId("1");
        final StartGameCommand startGameCommand = new StartGameCommand(gameId, "Peter", "Loraine");
        applicationService.doCommand(startGameCommand);
        applicationService.print(gameId);

        final MakeMoveCommand makeMoveCommand = new MakeMoveCommand(gameId, Pawn.white(), Square.of(File.a, Rank.two), Square.of(File.a, Rank.four), false, new Player("Peter", ChessColor.WHITE));
        applicationService.doCommand(makeMoveCommand);
        applicationService.print(gameId);
    }
}
