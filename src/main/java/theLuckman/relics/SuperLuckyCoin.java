package theLuckman.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.LuckmanMod;
import theLuckman.util.TextureLoader;

import static theLuckman.LuckmanMod.makeRelicOutlinePath;
import static theLuckman.LuckmanMod.makeRelicPath;

public class SuperLuckyCoin extends CustomRelic {

    public static final String ID = LuckmanMod.makeID("SuperLuckyCoin");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("SuperLuckyCoin.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("LuckyCoin.png"));

    public SuperLuckyCoin() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.FLAT);
    }

    public void atBattleStart() {
        this.counter = 0;// 25
    }

    public void atTurnStart() {
        this.counter = 0;
    }

    @Override
    public void obtain() {
        // Replace the starter relic, or just give the relic if starter isn't found
        if (AbstractDungeon.player.hasRelic(LuckyCoin.ID)) {
            for (int i=0; i<AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(LuckyCoin.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }

    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (this.counter < 6 && card.type != AbstractCard.CardType.CURSE) {// 61
            ++this.counter;// 62
            if (this.counter >= 3) {// 63
                this.flash();// 64
            }
        }

    }// 67

    public boolean canPlay(AbstractCard card) {
        if (this.counter >= 3 && card.type != AbstractCard.CardType.CURSE) {// 71
            card.cantUseMessage = "I can only play up to NL #r" + 3 + " cards per turn.";// 72
            return false;// 73
        } else {
            return true;// 75
        }
    }

    public void onVictory() {
        this.counter = -1;// 53
    }

    @Override
    public String getUpdatedDescription()
    {
        // Colorize the starter relic's name
        String name = new LuckyCoin().name;
        StringBuilder sb = new StringBuilder();
        for (String word : name.split(" ")) {
            sb.append("[#").append(LuckmanMod.LUCKMAN_RED.toString()).append("]").append(word).append("[] ");
        }
        sb.setLength(sb.length()-1);
        sb.append("[#").append(LuckmanMod.LUCKMAN_RED.toString()).append("]");

        return DESCRIPTIONS[0] + sb.toString() + DESCRIPTIONS[1];
    }

    @Override
    public boolean canSpawn()
    {
        // Only spawn if player has the starter relic
        return AbstractDungeon.player.hasRelic(LuckyCoin.ID);
    }
}
