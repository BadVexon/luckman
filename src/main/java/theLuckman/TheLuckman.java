package theLuckman;

import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.powers.BackAttackPower;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BobEffect;
import theLuckman.cards.*;
import theLuckman.relics.LuckyCoin;

import java.util.ArrayList;
import java.util.Iterator;

import static theLuckman.LuckmanMod.*;
import static theLuckman.TheLuckman.Enums.COLOR_RED;

public class TheLuckman extends CustomPlayer {
    private static final String[] orbTextures = {
            "luckmanmodResources/images/char/luckmanChar/orb/layer1.png",
            "luckmanmodResources/images/char/luckmanChar/orb/layer2.png",
            "luckmanmodResources/images/char/luckmanChar/orb/layer3.png",
            "luckmanmodResources/images/char/luckmanChar/orb/layer4.png",
            "luckmanmodResources/images/char/luckmanChar/orb/layer5.png",
            "luckmanmodResources/images/char/luckmanChar/orb/layer6.png",
            "luckmanmodResources/images/char/luckmanChar/orb/layer1d.png",
            "luckmanmodResources/images/char/luckmanChar/orb/layer2d.png",
            "luckmanmodResources/images/char/luckmanChar/orb/layer3d.png",
            "luckmanmodResources/images/char/luckmanChar/orb/layer4d.png",
            "luckmanmodResources/images/char/luckmanChar/orb/layer5d.png",};
    private static final String ID = makeID("theLuckman");
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    private static final String[] NAMES = characterStrings.NAMES;
    private static final String[] TEXT = characterStrings.TEXT;

    public TheLuckman(String name, PlayerClass setClass) {
        super(name, setClass, orbTextures, "luckmanmodResources/images/char/luckmanChar/orb/vfx.png", null, new SpriterAnimation(
                "luckmanmodResources/images/char/luckmanChar/luckman.scml"));
        initializeClass(null,
                LUCKMAN_CHAR_SHOULDER_PNG,
                LUCKMAN_CHAR_SHOULDER_2_PNG,
                LUCKMAN_CHAR_CORPSE_PNG,
                getLoadout(), 0.0F, 0.0F, 382.0F, 262.0F, new EnergyManager(3));


        dialogX = (drawX + 0.0F * Settings.scale);
        dialogY = (drawY + 240.0F * Settings.scale);
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                66, 66, 15, 99, 5, this, getStartingRelics(),
                getStartingDeck(), false);
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(Strike.ID);
        retVal.add(Strike.ID);
        retVal.add(Strike.ID);
        retVal.add(Strike.ID);
        retVal.add(Strike.ID);
        retVal.add(Defend.ID);
        retVal.add(Defend.ID);
        retVal.add(Defend.ID);
        retVal.add(Defend.ID);
        retVal.add(RouletteCrash.ID);
        retVal.add(Horrify.ID);
        return retVal;
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(LuckyCoin.ID);
        return retVal;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_PIERCING_WAIL", MathUtils.random(-0.2F, 0.2F));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                false);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_PIERCING_WAIL";
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 6;
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return COLOR_RED;
    }

    @Override
    public Color getCardTrailColor() {
        return LuckmanMod.LUCKMAN_RED;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new Jackpot();
    }

    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return NAMES[1];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new TheLuckman(name, chosenClass);
    }

    @Override
    public Color getCardRenderColor() {
        return LUCKMAN_RED;
    }

    @Override
    public Color getSlashAttackColor() {
        return LUCKMAN_RED;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.FIRE,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.FIRE};
    }

    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }

    @Override
    public String getVampireText() {
        return TEXT[2];
    }

    public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass THE_LUCKMAN;
        @SpireEnum(name = "LUCKMAN_RED_COLOR")
        public static AbstractCard.CardColor COLOR_RED;
        @SpireEnum(name = "LUCKMAN_RED_COLOR")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }

}
