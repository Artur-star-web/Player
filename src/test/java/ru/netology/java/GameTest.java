package ru.netology.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {

    @Test
    public void testRegisterAndPlay() {
        Game game = new Game();
        Player player1 = new Player(1, "Sergei", 100);
        Player player2 = new Player(2, "Igor", 90);

        game.register(player1);
        game.register(player2);

        Assertions.assertEquals(1, game.round("Sergei", "Igor"));
        Assertions.assertEquals(2, game.round("Igor", "Sergei"));
        Assertions.assertEquals(0, game.round("Sergei", "Sergei"));
    }

    @Test
    public void testUnregisteredPlayerThrowsException() {
        Game game = new Game();
        game.register(new Player(1, "Sergei", 100));

        Exception exception = Assertions.assertThrows(NotRegisteredException.class, () -> game.round("Sergei", "Masha"));

        Assertions.assertEquals("Один или оба игрока не зарегистрированы", exception.getMessage());
    }

    @Test
    public void testRoundWithOneUnregisteredPlayer() {
        Game game = new Game();
        game.register(new Player(1, "Sergei", 100));

        // Один из игроков не зарегистрирован
        Assertions.assertThrows(NotRegisteredException.class, () -> game.round("Sergei", "Igor"));
    }

    @Test
    public void testRoundWithTwoUnregisteredPlayers() {
        Game game = new Game();

        // Оба игрока не зарегистрированы
        Assertions.assertThrows(NotRegisteredException.class, () -> game.round("Sergei", "Igor"));
    }

    @Test
    public void testRound_PlayerWithHigherStrengthWins() {
        Game game = new Game();
        Player player1 = new Player(1, "Sergei", 100);
        Player player2 = new Player(2, "Igor", 90);

        game.register(player1);
        game.register(player2);

        Assertions.assertEquals(1, game.round("Sergei", "Igor"));
        Assertions.assertEquals(2, game.round("Igor", "Sergei"));
    }

    @Test
    public void testRound_DrawBetweenEqualPlayers() {
        Game game = new Game();
        Player player1 = new Player(1, "Sergei", 100);
        Player player2 = new Player(2, "Igor", 100);

        game.register(player1);
        game.register(player2);

        Assertions.assertEquals(0, game.round("Sergei", "Igor"));
        Assertions.assertEquals(0, game.round("Igor", "Sergei"));
    }
}
