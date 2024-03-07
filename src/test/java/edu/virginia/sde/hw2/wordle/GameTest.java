package edu.virginia.sde.hw2.wordle;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static edu.virginia.sde.hw2.wordle.GameStatus.*;
import static edu.virginia.sde.hw2.wordle.LetterResult.*;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private static Dictionary defaultGuessesDictionary, defaultAnswersDictionary;
    @BeforeAll
    public static void initialize() {
        defaultGuessesDictionary = DefaultDictionaries.getGuessesDictionary();
        defaultAnswersDictionary = DefaultDictionaries.getAnswersDictionary();
    }
    @Test
    public void test_init_zeroArgumentConstructor() {
        var game = new Game();

        assertEquals(defaultGuessesDictionary, game.getGuessDictionary());
        assertTrue(defaultAnswersDictionary.contains(game.getAnswer()));
        assertEquals(6, game.getGuessesRemaining());
        assertEquals(PLAYING, game.getGameStatus());
    }

    @Test
    public void test_init_4ArgumentConstructor() {
        var game = new Game(defaultGuessesDictionary, "TREND", 6, PLAYING);

        assertEquals(defaultGuessesDictionary, game.getGuessDictionary());
        assertEquals("TREND", game.getAnswer());
        assertEquals(6, game.getGuessesRemaining());
        assertEquals(PLAYING, game.getGameStatus());
    }

    @Test
    public void test_isGameOver_WIN_True() {
        var game = new Game(defaultGuessesDictionary, "TREND", 5, WIN);

        assertEquals(WIN, game.getGameStatus());
        assertTrue(game.isGameOver());
    }
    @Test
    public void test_submitGuess_guessesRemainingDecreaseWithWrongGuess() {
        Game game = new Game(DefaultDictionaries.getGuessesDictionary(), "story", 6, PLAYING);
        game.submitGuess("candy");
        assertEquals(5, game.getGuessesRemaining());
    }
    @Test
    public void test_submitGuess_guessesRemainingDecreaseWithRightGuess() {
        Game game = new Game(DefaultDictionaries.getGuessesDictionary(), "dream", 4, PLAYING);
        game.submitGuess("wrong");
        assertEquals(3, game.getGuessesRemaining());
    }
    @Test
    public void test_submitGuess_gameStatusChangeToLoss() {
        Game game = new Game(DefaultDictionaries.getGuessesDictionary(), "eerie", 1, PLAYING);
        game.submitGuess("earth");
        assertEquals(LOSS, game.getGameStatus());
    }
    @Test
    public void test_submitGuess_gameStatus_Playing() {
        Game game = new Game(DefaultDictionaries.getGuessesDictionary(), "eerie", 2, PLAYING);
        game.submitGuess("small");
        assertEquals(PLAYING, game.getGameStatus());
    }
    @Test
    public void test_submitGuess_gameStatusChangeToWin() {
        Game game = new Game(DefaultDictionaries.getGuessesDictionary(), "break", 2, PLAYING);
        game.submitGuess("break");
        assertEquals(WIN, game.getGameStatus());
    }
    @Test
    public void test_submitGuess_gameStatusStayPlaying() {
        Game game = new Game(DefaultDictionaries.getGuessesDictionary(), "break", 2, PLAYING);
        game.submitGuess("brake");
        assertEquals(PLAYING, game.getGameStatus());
    }
    @Test
    public void test_submitGuess_gameStatusAlmostWinning() {
        Game game = new Game(DefaultDictionaries.getGuessesDictionary(), "break", 5, PLAYING);
        game.submitGuess("brake");
        assertEquals(PLAYING, game.getGameStatus());
    }
    @Test
    public void test_invalidGuess() {
        Game game = new Game();
        String invalidGuess = "lsejf";

        assertThrows(IllegalWordException.class, () -> game.submitGuess(invalidGuess));
        assertEquals(PLAYING, game.getGameStatus());
        assertEquals(game.getGuessesRemaining(), 6);
    }
    @Test
    public void testValidCorrectGuessReturnValue() {
        Game game = new Game();
        String correctGuess = "flyer";
        GuessResult result = game.submitGuess(correctGuess);

        assertNotNull(result);

    }
    @Test
    public void testIncorrectGuessReturnValue(){
        Game game = new Game();
        String incorrectGuess = "flyer";
        GuessResult result = game.submitGuess(incorrectGuess);

        assertNotNull(result);
        assertEquals(PLAYING, game.getGameStatus());
    }

}