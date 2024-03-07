package edu.virginia.sde.hw2.wordle;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;


public class GuessResultTest {

    @Test
    public void testGetLetterResults_AllCorrectLetters() {
        // Arrange
        GuessResult guessResult = new GuessResult("story", "story");

        // Act
        LetterResult[] letterResults = guessResult.getLetterResults();

        // Assert
        LetterResult[] expectedResults = {
                LetterResult.GREEN, LetterResult.GREEN, LetterResult.GREEN, LetterResult.GREEN, LetterResult.GREEN
        };
        assertArrayEquals(expectedResults, letterResults);
    }
    @Test
    public void testGetLetterResults_Some_Correct_Some_Incorrect() {
        // Arrange
        GuessResult guessResult = new GuessResult("story", "stark");

        // Act
        LetterResult[] letterResults = guessResult.getLetterResults();

        // Assert
        LetterResult[] expectedResults = {
                LetterResult.GREEN, LetterResult.GREEN, LetterResult.GRAY, LetterResult.GREEN, LetterResult.GRAY
        };
        assertArrayEquals(expectedResults, letterResults);
    }

    @Test
    public void testGetLetterResults_Some_Correct_Some_Incorrect_Positions_Some_Wrong() {
        // Arrange
        GuessResult guessResult = new GuessResult("plans", "spiny");

        // Act
        LetterResult[] letterResults = guessResult.getLetterResults();

        // Assert
        LetterResult[] expectedResults = {
                LetterResult.YELLOW, LetterResult.GRAY, LetterResult.GRAY, LetterResult.GREEN, LetterResult.YELLOW
        };
        assertArrayEquals(expectedResults, letterResults);
    }

    @Test
    public void testGetLetterResults_All_Are_Wrong() {
        // Arrange
        GuessResult guessResult = new GuessResult("lucky", "spans");

        // Act
        LetterResult[] letterResults = guessResult.getLetterResults();

        // Assert
        LetterResult[] expectedResults = {
                LetterResult.GRAY, LetterResult.GRAY, LetterResult.GRAY, LetterResult.GRAY, LetterResult.GRAY
        };
        assertArrayEquals(expectedResults, letterResults);
    }

    @Test
    public void testGetLetterResults_All_Are_In_Wrong_Position() {
        // Arrange
        GuessResult guessResult = new GuessResult("ozark", "zorka");

        // Act
        LetterResult[] letterResults = guessResult.getLetterResults();

        // Assert
        LetterResult[] expectedResults = {
                LetterResult.YELLOW, LetterResult.YELLOW, LetterResult.YELLOW, LetterResult.YELLOW, LetterResult.YELLOW
        };
        assertArrayEquals(expectedResults, letterResults);
    }
    @Test
    public void testGetLetterResults_All_In_Correct_Positions_Case_Changes() {
        // Arrange
        GuessResult guessResult = new GuessResult("BLobs", "blobs");

        // Act
        LetterResult[] letterResults = guessResult.getLetterResults();

        // Assert
        LetterResult[] expectedResults = {
                LetterResult.GREEN, LetterResult.GREEN, LetterResult.GREEN, LetterResult.GREEN, LetterResult.GREEN
        };
        assertArrayEquals(expectedResults, letterResults);
    }
    @Test
    public void testGetLetterResults_DoubleLetter_Wrong_Position() {
        // Arrange
        GuessResult guessResult = new GuessResult("boost", "ozark");

        // Act
        LetterResult[] letterResults = guessResult.getLetterResults();

        // Assert
        LetterResult[] expectedResults = {
                LetterResult.GRAY, LetterResult.YELLOW, LetterResult.GRAY, LetterResult.GRAY, LetterResult.GRAY
        };
        assertArrayEquals(expectedResults, letterResults);
    }

    @Test
    public void testGetLetterResults_DoubleLetter_In_Answer() {
        // Arrange
        GuessResult guessResult = new GuessResult("ozark", "boost");

        // Act
        LetterResult[] letterResults = guessResult.getLetterResults();

        // Assert
        LetterResult[] expectedResults = {
                LetterResult.YELLOW, LetterResult.GRAY, LetterResult.GRAY, LetterResult.GRAY, LetterResult.GRAY
        };
        assertArrayEquals(expectedResults, letterResults);
    }

    @Test
    public void testGetLetterResults_Triple_Double_Letter() {
        // Arrange
        GuessResult guessResult = new GuessResult("eerie", "beets");

        // Act
        LetterResult[] letterResults = guessResult.getLetterResults();

        // Assert
        LetterResult[] expectedResults = {
                LetterResult.YELLOW, LetterResult.GREEN, LetterResult.GRAY, LetterResult.GRAY, LetterResult.GRAY
        };
        assertArrayEquals(expectedResults, letterResults);
    }

    @Test
    public void testGetLetterResults_Triple_Single_Letter() {
        // Arrange
        GuessResult guessResult = new GuessResult("eerie", "berts");

        // Act
        LetterResult[] letterResults = guessResult.getLetterResults();

        // Assert
        LetterResult[] expectedResults = {
                LetterResult.GRAY, LetterResult.GREEN, LetterResult.GREEN, LetterResult.GRAY, LetterResult.GRAY
        };
        assertArrayEquals(expectedResults, letterResults);
    }
    @Test
    public void testIsCorrect_true_correctGuess() {
        GuessResult guessResult = new GuessResult("cRiMe", "crime");
        assertTrue(guessResult.isCorrect());
    }
    @Test
    public void testIsCorrect_false_wrongGuess() {
        GuessResult guessResult = new GuessResult("brake", "break");
        assertFalse(guessResult.isCorrect());

    }
}
