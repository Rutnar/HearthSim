package com.hearthsim.card.minion.concrete;

import com.hearthsim.card.Card;
import com.hearthsim.card.minion.Minion;
import com.hearthsim.card.minion.MinionTargetableBattlecry;
import com.hearthsim.event.battlecry.BattlecryActionTargetable;
import com.hearthsim.exception.HSException;
import com.hearthsim.model.BoardModel;
import com.hearthsim.model.PlayerSide;
import com.hearthsim.util.tree.HearthTreeNode;

public class TempleEnforcer extends Minion implements MinionTargetableBattlecry {

    /**
     * Battlecry: Give a friendly minion +3 Health
     */
    private final static BattlecryActionTargetable battlecryAction = new BattlecryActionTargetable() {
        protected boolean canTargetOwnMinions() { return true; }

        @Override
        public HearthTreeNode useTargetableBattlecry_core(PlayerSide originSide, Minion origin, PlayerSide targetSide, Minion targetMinion, HearthTreeNode boardState) throws HSException {
            HearthTreeNode toRet = boardState;
            targetMinion.setHealth((byte)(targetMinion.getHealth() + 3));
            return toRet;
        }
    };

    public TempleEnforcer() {
        super();
    }

    @Override
    public boolean canTargetWithBattlecry(PlayerSide originSide, Card origin, PlayerSide targetSide, int targetCharacterIndex, BoardModel board) {
        return TempleEnforcer.battlecryAction.canTargetWithBattlecry(originSide, origin, targetSide, targetCharacterIndex, board);
    }

    @Override
    public HearthTreeNode useTargetableBattlecry_core(PlayerSide originSide, Minion origin, PlayerSide targetSide, Minion targetMinion, HearthTreeNode boardState) throws HSException {
        return TempleEnforcer.battlecryAction.useTargetableBattlecry_core(originSide, origin, targetSide, targetMinion, boardState);
    }
}
