package com.hearthsim.card.basic.spell;

import com.hearthsim.card.minion.Minion;
import com.hearthsim.card.spellcard.SpellDamageTargetableCard;
import com.hearthsim.exception.HSException;
import com.hearthsim.model.PlayerSide;
import com.hearthsim.util.tree.CardDrawNode;
import com.hearthsim.util.tree.HearthTreeNode;

public class Starfire extends SpellDamageTargetableCard {


    public Starfire() {
        super();
    }

    /**
     *
     * Use the card on the given target
     *
     * Deals 5 damage and draws a card
     *
     *
     *
     * @param side
     * @param boardState The BoardState before this card has performed its action.  It will be manipulated and returned.
     *
     * @return The boardState is manipulated and returned
     */
    @Override
    protected HearthTreeNode use_core(
            PlayerSide side,
            Minion targetMinion,
            HearthTreeNode boardState)
        throws HSException {
        HearthTreeNode toRet = super.use_core(side, targetMinion, boardState);
        if (toRet instanceof CardDrawNode) {
            ((CardDrawNode) toRet).addNumCardsToDraw(1);
        } else {
            toRet = new CardDrawNode(toRet, 1); //draw two cards
        }
        return toRet;
    }
}
