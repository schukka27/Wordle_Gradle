package edu.virginia.sde.hw2.wordle;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryTest {

    @Test
    void getWordSet() {
        var startingWordSet = new HashSet<>(Set.of("apple", "black", "camel"));
        var dictionary = new Dictionary(startingWordSet, new WordValidator());

        var wordSet = dictionary.getWordSet();
        assertEquals(3, wordSet.size());
        assertTrue(wordSet.contains("apple"));
        assertTrue(wordSet.contains("black"));
        assertTrue(wordSet.contains("camel"));
    }

    @Test
    void getWordSet_initiallyEmpty() {
        var dictionary = new Dictionary();

        var wordSet = dictionary.getWordSet();
        assertTrue(wordSet.isEmpty());
    }
    @Test
    void size_initally_empty(){
        var dictionary = new Dictionary();

        var wordSet = dictionary.getWordSet();
        assertTrue(dictionary.size() == 0);
    }

    @Test
    void size_larger_amount(){
        var startingWordSet = new HashSet<>(Set.of("apple", "black", "camel","adieu","short"));
        var dictionary = new Dictionary(startingWordSet, new WordValidator());

        var wordSet = dictionary.getWordSet();
        assertTrue(dictionary.size() == 5);
        assertTrue(wordSet.contains("apple"));
        assertTrue(wordSet.contains("black"));
        assertTrue(wordSet.contains("camel"));
        assertTrue(wordSet.contains("adieu"));
        assertTrue(wordSet.contains("short"));
        // making sure state of object hasn't changed
    }

    @Test
    void dict_contains_true(){
        var startingWordSet = new HashSet<>(Set.of("apple", "black", "camel"));
        var dictionary = new Dictionary(startingWordSet, new WordValidator());

        var wordSet = dictionary.getWordSet();
        assertTrue(wordSet.contains("apple"));
        // should output true
        assertTrue(wordSet.contains("black"));
        assertTrue(wordSet.contains("camel"));
        assertTrue(dictionary.size() == 3);
        // making sure state of object hasn't changed
    }

    @Test
    void dict_contains_false(){
        var startingWordSet = new HashSet<>(Set.of("apple", "black", "camel"));
        var dictionary = new Dictionary(startingWordSet, new WordValidator());

        var wordSet = dictionary.getWordSet();
        assertFalse(wordSet.contains("green"));
        // should output false
        assertTrue(wordSet.contains("apple"));
        assertTrue(wordSet.contains("black"));
        assertTrue(wordSet.contains("camel"));
        assertTrue(dictionary.size() == 3);
        // making sure state of object hasn't changed
    }

    @Test
    void dict_contains_true_uppercase(){
        var startingWordSet = new HashSet<>(Set.of("apple", "black", "camel"));
        var dictionary = new Dictionary(startingWordSet, new WordValidator());

        var wordSet = dictionary.getWordSet();
        assertTrue(dictionary.contains("BLacK"));
        // should output true
        assertTrue(wordSet.contains("apple"));
        assertTrue(wordSet.contains("black"));
        assertTrue(wordSet.contains("camel"));
        assertEquals(3,dictionary.size());
        // making sure state of object hasn't changed
    }

    @Test
    void dict_add_valid_lowercase(){
        var startingWordSet = new HashSet<>(Set.of("apple", "black", "camel"));
        var dictionary = new Dictionary(startingWordSet, new WordValidator());

        var wordSet = dictionary.getWordSet();
        dictionary.addWord("shoes");
        assertTrue(wordSet.contains("shoes"));
        // dictionary should add this
        assertTrue(wordSet.contains("apple"));
        assertTrue(wordSet.contains("black"));
        assertTrue(wordSet.contains("camel"));
        assertEquals(4,dictionary.size());
        // making sure state of object hasn't changed
    }

    @Test
    void dict_add_valid_Word_UpperCase(){
        var startingWordSet = new HashSet<>(Set.of("apple", "black", "camel"));
        var dictionary = new Dictionary(startingWordSet, new WordValidator());

        var wordSet = dictionary.getWordSet();
        dictionary.addWord("SHORT");
        assertTrue(wordSet.contains("short"));
        // dictionary should be case insensitive so when adding a word "BOOOT" should lowercase it when it adds it
        // to the dictionary. So, the dictionary should contain "booot". But it fails this equivalence test.
        assertTrue(wordSet.contains("apple"));
        assertTrue(wordSet.contains("black"));
        assertTrue(wordSet.contains("camel"));
        assertEquals(4,dictionary.size());
        // making sure state of object hasn't changed
    }
    @Test
    void dict_add_invalid_word(){
        var startingWordSet = new HashSet<>(Set.of("apple", "black", "camel"));
        var dictionary = new Dictionary(startingWordSet, new WordValidator());

        var wordSet = dictionary.getWordSet();

        try{
            dictionary.addWord("doe");

        } catch (IllegalArgumentException arr){

        }
        assertFalse(wordSet.contains("doe"));
        // dictionary shouldn’t add this
        assertTrue(wordSet.contains("apple"));
        assertTrue(wordSet.contains("black"));
        assertTrue(wordSet.contains("camel"));
        assertEquals(3,dictionary.size());
    }






}