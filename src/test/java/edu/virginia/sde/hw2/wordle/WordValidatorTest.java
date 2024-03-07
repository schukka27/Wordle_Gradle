package edu.virginia.sde.hw2.wordle;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WordValidatorTest {
    private static WordValidator wordValidator;
    @BeforeAll
    public static void initialize() {
        wordValidator = new WordValidator();
    }
    @Test
    public void isValidWord_false_tooLong() {
        assertFalse(wordValidator.isValidWord("DOGGIE"));
    }

    @Test
    public void isAllLetters_true_allLetters() { assertTrue(wordValidator.isAllLetters("abcde"));}

    @Test
    public void isCorrectLength_false_tooLong() {assertFalse(wordValidator.isCorrectLength("abcdef"));}

    @Test
    public void isAllLetters_false_hasANumber() {assertFalse(wordValidator.isAllLetters("djs8n"));}
}
