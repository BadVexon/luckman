package theLuckman.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.LuckmanMod;
import theLuckman.util.TextureLoader;

import static theLuckman.LuckmanMod.makeRelicOutlinePath;
import static theLuckman.LuckmanMod.makeRelicPath;

public class UnluckyCoin extends CustomRelic {

    public static final String ID = LuckmanMod.makeID("UnluckyCoin");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("UnluckyCoin.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("LuckyCoin.png"));

    public UnluckyCoin() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.FLAT);
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

    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;// 37
    }// 38

    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;// 42
    }// 43


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
        return AbstractDungeon.player.hasRelic(LuckyCoin.ID);
    }
}
