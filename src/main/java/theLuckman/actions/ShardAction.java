package theLuckman.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import theLuckman.powers.LuckPower;

public class ShardAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private int damageIncrease;
    private AbstractMonster targetMonster;

    public ShardAction(int damageIncrease, AbstractMonster m) {
        this.duration = 0.0F;// 20
        this.actionType = ActionType.WAIT;// 21
        this.damageIncrease = damageIncrease;// 22
        this.targetMonster = m;// 23
    }// 24

    public void update() {
        if (this.targetMonster != null && this.targetMonster.getIntentBaseDmg() >= 0) {
            AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, "That enemy intends to attack!", true));// 36
        } else {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new LuckPower(this.damageIncrease), this.damageIncrease));// 29
        }

        this.isDone = true;// 40
    }// 41

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("OpeningAction");// 13
        TEXT = uiStrings.TEXT;// 14
    }
}
