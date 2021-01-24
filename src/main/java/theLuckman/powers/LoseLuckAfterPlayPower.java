package theLuckman.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theLuckman.LuckmanMod;
import theLuckman.util.TextureLoader;

public class LoseLuckAfterPlayPower extends TwoAmountPower implements CloneablePowerInterface {
    public static final String POWER_ID = LuckmanMod.makeID("LoseLuckAfterPlayPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture("luckmanmodResources/images/powers/LoseLuckAfterPlayPower_84.png");
    private static final Texture tex32 = TextureLoader.getTexture("luckmanmodResources/images/powers/LoseLuckAfterPlayPower_32.png");

    public LoseLuckAfterPlayPower(final int amount, final int amount2) {
        name = NAME;
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        this.amount = amount;
        this.amount2 = amount2;

        type = PowerType.DEBUFF;
        isTurnBased = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        this.amount2--;
        if (this.amount2 == 0) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, LuckPower.POWER_ID, this.amount));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        }

    }

    @Override
    public AbstractPower makeCopy() {
        return new LoseLuckAfterPlayPower(amount, amount2);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}