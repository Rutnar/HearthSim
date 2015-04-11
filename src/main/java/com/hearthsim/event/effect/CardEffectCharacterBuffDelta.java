package com.hearthsim.event.effect;

import com.hearthsim.card.Card;
import com.hearthsim.card.minion.Minion;
import com.hearthsim.model.PlayerSide;
import com.hearthsim.util.tree.HearthTreeNode;

public class CardEffectCharacterBuffDelta<T extends Card> implements CardEffectCharacter<T> {
    private final byte attackDelta;
    private final byte healthDelta;
    private final boolean addTaunt;

    public CardEffectCharacterBuffDelta(int attackDelta, int healthDelta) {
        this(attackDelta, healthDelta, false);
    }

    public CardEffectCharacterBuffDelta(int attackDelta, int healthDelta, boolean addTaunt) {
        this.attackDelta = (byte) attackDelta;
        this.healthDelta = (byte) healthDelta;
        this.addTaunt = addTaunt;
    }


    @Override
    public HearthTreeNode applyEffect(PlayerSide originSide, T origin, PlayerSide targetSide, int targetCharacterIndex, HearthTreeNode boardState) {
        Minion targetCharacter = boardState.data_.modelForSide(targetSide).getCharacter(targetCharacterIndex);
        targetCharacter.addAttack(this.attackDelta);
        targetCharacter.addHealth(this.healthDelta);
        targetCharacter.addMaxHealth(this.healthDelta);

        if (addTaunt) {
            targetCharacter.setTaunt(true);
        }
        return boardState;
    }
}
