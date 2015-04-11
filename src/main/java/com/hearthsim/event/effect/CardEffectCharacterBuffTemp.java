package com.hearthsim.event.effect;

import com.hearthsim.card.Card;
import com.hearthsim.card.minion.Minion;
import com.hearthsim.model.PlayerSide;
import com.hearthsim.util.tree.HearthTreeNode;

public class CardEffectCharacterBuffTemp<T extends Card> implements CardEffectCharacter<T> {
    private final byte attackDelta;

    public CardEffectCharacterBuffTemp(int attackDelta) {
        this.attackDelta = (byte) attackDelta;
    }

    @Override
    public HearthTreeNode applyEffect(PlayerSide originSide, T origin, PlayerSide targetSide, int targetCharacterIndex, HearthTreeNode boardState) {
        Minion targetCharacter = boardState.data_.modelForSide(targetSide).getCharacter(targetCharacterIndex);
        targetCharacter.addExtraAttackUntilTurnEnd(this.attackDelta);
        return boardState;
    }
}
