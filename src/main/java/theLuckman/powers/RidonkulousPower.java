package theLuckman.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theLuckman.LuckmanMod;
import theLuckman.util.EvilEye;
import theLuckman.util.TextureLoader;

public class RidonkulousPower extends AbstractPower implements CloneablePowerInterface {
    public static final String POWER_ID = LuckmanMod.makeID("RidonkulousPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture("luckmanmodResources/images/powers/RidonkulousPower_84.png");
    private static final Texture tex32 = TextureLoader.getTexture("luckmanmodResources/images/powers/RidonkulousPower_32.png");

    public RidonkulousPower(final int amount) {
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

    @Override
    public void atStartOfTurnPostDraw() {
        this.flash();
        for (int i = 0; i < amount; i++) {
            AbstractDungeon.actionManager.addToBottom(new ChannelAction(new EvilEye()));
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new RidonkulousPower(amount);
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        } else {
            description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        }
    }
}