package theLuckman.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import theLuckman.LuckmanMod;
import theLuckman.util.TextureLoader;

public class LuckPower extends AbstractPower implements CloneablePowerInterface {
    public static final String POWER_ID = LuckmanMod.makeID("LuckPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture("luckmanmodResources/images/powers/LuckPower_84.png");
    private static final Texture tex32 = TextureLoader.getTexture("luckmanmodResources/images/powers/LuckPower_32.png");

    public LuckPower(final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;// 42
        this.amount += stackAmount;// 43
        if (this.amount == 0) {// 44
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));// 45
        }

        if (this.amount >= 999) {// 52
            this.amount = 999;// 53
        }

        if (this.amount <= -999) {// 56
            this.amount = -999;// 57
        }

    }

    public void reducePower(int reduceAmount) {
        this.fontScale = 8.0F;// 63
        this.amount -= reduceAmount;// 64
        if (this.amount == 0) {// 66
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));// 67
        }

        if (this.amount >= 999) {// 70
            this.amount = 999;// 71
        }

        if (this.amount <= -999) {// 74
            this.amount = -999;// 75
        }

    }

    @Override
    public AbstractPower makeCopy() {
        return new LuckPower(amount);
    }

    public void updateDescription() {
        if (this.amount > 0) {// 81
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];// 82
            this.type = PowerType.BUFF;// 83
        } else {
            int tmp = -this.amount;// 85
            this.description = DESCRIPTIONS[1] + tmp + DESCRIPTIONS[2];// 86
            this.type = PowerType.DEBUFF;// 87
        }
    }
}