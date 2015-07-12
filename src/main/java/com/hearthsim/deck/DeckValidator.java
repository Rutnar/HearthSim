package com.hearthsim.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.hearthsim.card.minion.Hero;
import com.hearthsim.io.DeckListFile;

public class DeckValidator {

    private Deck deck_;
    private Hero hero_;
    private String[] constructedErrors_;
    private boolean isValidConstructedDeck_;

    public DeckValidator(Deck deck, Hero hero) {
        deck_ = deck;
        hero_ = hero;
        constructedErrors_ = null;
        isValidConstructedDeck_ = true;
        
        validateDeck();
    }
    
    public DeckValidator(DeckListFile decklist) {
        this(decklist.getDeck(), decklist.getHero());
    }
    
    private void validateDeck() {
        // check size = 30
        if (!validateDeckSize()) {
            isValidConstructedDeck_ = false;
        }
        
        // check hero class selected
        if (!validateHeroSelected()) {
            isValidConstructedDeck_ = false;
        }
        
        // check any class cards match hero's class
        if (!validateClassCards()) {
            isValidConstructedDeck_ = false;
        }
        
        // check 2 or less of each card 
        if (!validateTwoCopiesOrLess()) {
            isValidConstructedDeck_ = false;
        }
    }
    
    /**
     * This method needs to do all the validation checks and not return early
     * Each check writes all error messages to constructedErrors_
     * @return true when the deck is a legal and valid deck for constructed games
     */
    public boolean isValidConstructedDeck() {
        
        return isValidConstructedDeck_;
    }
    
    public String getConstructedDeckStatusText() {
        if (isValidConstructedDeck_) {
            return "Deck is Valid";
        }
        else {
            return "Invalid Deck";
        }

    }
    
    private boolean validateHeroSelected() {
        
        String heroClass = hero_.getHeroClass();
        
        if (heroClass == "None") {
            return false;
        }
        else {
            return true;
        }
    }

    private boolean validateDeckSize() {
        boolean isValid = true;
        
        if (deck_.getNumCards() < 30) {
            // add 'not enough cards' message to errors
            isValid = false;
        }
        
        if (deck_.getNumCards() > 30) {
            // add 'too many cards' message to errors
            isValid = false;
        }
        
        return isValid;        
    }

    private boolean validateClassCards() {

        int deckSize = deck_.getNumCards();
        String heroClass = hero_.getHeroClass();
    
        // build a local list of the cards
        List<String> cardList = new ArrayList<String>();
        for (int i = 0; i < deckSize; i++) {
            cardList.add(deck_.drawCard(i).getCardClass());
        }
    
        // Check the deck and ensure they are all neutral cards or the class matches the hero class
        for (int j = 0; j < deckSize; j++) {
            String cardClass = deck_.drawCard(j).getCardClass();
            
            if (!cardClass.equalsIgnoreCase("neutral") && !cardClass.equalsIgnoreCase(heroClass)) {
                return false;
            }
        }

        return true;
    
    }

    private boolean validateTwoCopiesOrLess() {
        int deckSize = deck_.getNumCards();
        
        // build a local list of the cards
        List<String> cardList = new ArrayList<String>();
        for (int i = 0; i < deckSize; i++) {
            cardList.add(deck_.drawCard(i).getName());
        }
        
        // check the deck for too many copies
        for (int j = 0; j < deckSize; j++) {
            String card = deck_.drawCard(j).getName();
            int occurrences = Collections.frequency(cardList, card);
            
            String rarity = deck_.drawCard(j).getRarity();
            
            // check for non-legendaries with more than 2 copies
            if (occurrences > 2 && !rarity.equalsIgnoreCase("legendary")) {
                return false;
            }
            // check for legendaries with more than 1 copy
            else if (occurrences > 1 && rarity.equalsIgnoreCase("legendary")) {
                return false;
            }
        }
        
        return true;
    }

    public String[] getConstructedDeckErrors() {
        return constructedErrors_;
    }
}
