package theLuckman.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;

import java.util.UUID;

public class MakeFreeUntilPlayedAction extends AbstractGameAction {
    UUID uuid;

    public MakeFreeUntilPlayedAction(UUID targetUUID) {
        this.uuid = targetUUID;// 14
        this.duration = Settings.ACTION_DUR_XFAST;// 16
    }// 17

    public void update() {
        for (AbstractCard c : GetAllInBattleInstances.get(this.uuid)) {
            c.freeToPlayOnce = true;// 22
        }

        this.isDone = true;// 24
    }// 25
}
