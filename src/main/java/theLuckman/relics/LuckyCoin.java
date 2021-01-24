package theLuckman.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theLuckman.LuckmanMod;
import theLuckman.util.TextureLoader;

import static theLuckman.LuckmanMod.makeRelicOutlinePath;
import static theLuckman.LuckmanMod.makeRelicPath;

public class LuckyCoin extends CustomRelic {

    public static final String ID = LuckmanMod.makeID("LuckyCoin");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("LuckyCoin.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("LuckyCoin.png"));

    public LuckyCoin() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.FLAT);
    }

    public void atBattleStart() {
        this.counter = 0;// 25
    }

    public void atTurnStart() {
        this.counter++;
        if (this.counter == 2) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
        }
    }

    public void onVictory() {
        this.counter = -1;// 53
        this.stopPulse();// 54
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
