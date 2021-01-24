package theLuckman.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.combat.DarkOrbActivateEffect;
import com.megacrit.cardcrawl.vfx.combat.DarkOrbPassiveEffect;
import theLuckman.LuckmanMod;

import static theLuckman.LuckmanMod.makeCardPath;

public class EvilEye extends AbstractOrb {

    // Standard ID/Description
    public static final String ORB_ID = LuckmanMod.makeID("EvilEye");

    private static final Texture IMG = TextureLoader.getTexture(makeCardPath("EvilEye.png"));
    // Animation Rendering Numbers - You can leave these at default, or play around with them and see what they change.
    private float vfxTimer = 1.0f;
    private float vfxIntervalMin = 0.1f;
    private float vfxIntervalMax = 0.4f;
    private static final float ORB_WAVY_DIST = 0.04f;
    private static final float PI_4 = 12.566371f;

    public EvilEye() {

        ID = ORB_ID;
        name = "Evil Eye";
        img = IMG;

        evokeAmount = baseEvokeAmount = 1;

        updateDescription();

        angle = MathUtils.random(360.0f); // More Animation-related Numbers
        channelAnimTimer = 0.5f;
    }

    @Override
    public void updateDescription() { // Set the on-hover description of the orb
        applyFocus(); // Apply Focus (Look at the next method)
        if (evokeAmount == 1) {
            description = "#yEvoke: Stun a random enemy for #b" + evokeAmount + " turn. Remove #b4 random Orbs."; // Set the description
        } else {
            description = "#yEvoke: Stun a random enemy for #b" + evokeAmount + " turns. Remove #b4 random Orbs."; // Set the description
        }
    }

    @Override
    public void applyFocus() {
        evokeAmount = baseEvokeAmount;
    }

    @Override
    public void onEvoke() { // 1.On Orb Evoke

        AbstractDungeon.actionManager.addToTop(new AbstractGameAction() {
            @Override
            public void update() {
                for (int i = 0; i < 4; i++) {
                    AbstractDungeon.player.removeNextOrb();
                }
                isDone = true;
            }
        });

        AbstractDungeon.actionManager.addToTop(new StunMonsterAction(AbstractDungeon.getRandomMonster(), AbstractDungeon.player, evokeAmount));

        AbstractDungeon.actionManager.addToTop(new SFXAction("ATTACK_PIERCING_WAIL")); // 3.And play a Jingle Sound.
        // For a list of sound effects you can use, look under com.megacrit.cardcrawl.audio.SoundMaster - you can see the list of keys you can use there. As far as previewing what they sound like, open desktop-1.0.jar with something like 7-Zip and go to audio. Reference the file names provided. (Thanks fiiiiilth)

    }

    @Override
    public void updateAnimation() {// You can totally leave this as is.
        // If you want to create a whole new orb effect - take a look at conspire's Water Orb. It includes a custom sound, too!
        super.updateAnimation();
        angle += Gdx.graphics.getDeltaTime() * 45.0f;
        vfxTimer -= Gdx.graphics.getDeltaTime();
        if (vfxTimer < 0.0f) {
            AbstractDungeon.effectList.add(new DarkOrbPassiveEffect(cX, cY)); // This is the purple-sparkles in the orb. You can change this to whatever fits your orb.
            vfxTimer = MathUtils.random(vfxIntervalMin, vfxIntervalMax);
        }
    }

    // Render the orb.
    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE.cpy());
        sb.draw(img, cX - 48.0f, cY - 48.0f + bobEffect.y, 48.0f, 48.0f, 96.0f, 96.0f, scale + MathUtils.sin(angle / PI_4) * ORB_WAVY_DIST * Settings.scale, scale, angle, 0, 0, 96, 96, false, false);
        renderText(sb);
        hb.render(sb);
    }

    protected void renderText(SpriteBatch sb) {
        if (this.showEvokeValue) {// 126
            FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, "!!!", this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET - 4.0F * Settings.scale, new Color(0.2F, 1.0F, 1.0F, this.c.a), this.fontScale);// 127 130
        }

    }// 136

    @Override
    public void triggerEvokeAnimation() { // The evoke animation of this orb is the dark-orb activation effect.
        AbstractDungeon.effectsQueue.add(new DarkOrbActivateEffect(cX, cY));
    }

    @Override
    public void playChannelSFX() { // When you channel this orb, the ATTACK_FIRE effect plays ("Fwoom").
        CardCrawlGame.sound.play("ATTACK_FIRE", 0.1f);
    }

    @Override
    public AbstractOrb makeCopy() {
        return new EvilEye();
    }
}