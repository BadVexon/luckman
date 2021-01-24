package theLuckman.cards;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theLuckman.TheLuckman;
import theLuckman.powers.*;
import theLuckman.relics.SuperLuckyCoin;
import theLuckman.relics.UnluckyCoin;
import theLuckman.util.DamageCurvy;
import theLuckman.util.DamageLine;
import theLuckman.util.SuperWordAboveCreatureEffect;

import java.util.ArrayList;

import static theLuckman.LuckmanMod.makeCardPath;

public abstract class AbstractLuckmanCard extends CustomCard {

    protected final CardStrings cardStrings;
    protected final String NAME;
    protected final String DESCRIPTION;
    protected final String UPGRADE_DESCRIPTION;
    protected final String[] EXTENDED_DESCRIPTION;

    public int baseMagicNumber2 = -1;
    public int magicNumber2 = -1;
    public boolean isMagicNumber2Modified = false;
    public boolean upgradedMagicNumber2 = false;

    public int baseLuck = -1;
    public int luck = -1;
    public boolean isLuckModified = false;
    public boolean upgradedLuck = false;

    private int maxLines = 36;
    private int stride = 360 / maxLines;
    private float offset = MathUtils.random(-180.0F, 180.0F);

    public AbstractLuckmanCard(final String id, final int cost, final CardType type, final CardRarity rarity, final CardTarget target) {
        super(id, "ERROR", getCorrectPlaceholderImage(type), cost, "ERROR", type, TheLuckman.Enums.COLOR_RED, rarity, target);
        cardStrings = CardCrawlGame.languagePack.getCardStrings(id);
        name = NAME = cardStrings.NAME;
        originalName = NAME;
        rawDescription = DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
        initializeTitle();
        initializeDescription();
    }

    private static String getCorrectPlaceholderImage(CardType type) {
        switch (type) {
            case ATTACK:
                return makeCardPath("Attack.png");
            case SKILL:
                return makeCardPath("Skill.png");
            case POWER:
                return makeCardPath("Power.png");
        }
        return makeCardPath("Attack.png");
    }

    protected void upgradeMagicNumber2(int amount) {
        baseMagicNumber2 += amount;
        magicNumber2 = baseMagicNumber2;
        upgradedMagicNumber2 = true;
    }

    protected void upgradeLuck(int amount) {
        baseLuck += amount;
        luck = baseLuck;
        upgradedLuck = true;
    }

    public void applyPowers() {
        super.applyPowers();
        int base = this.baseLuck;
        if (AbstractDungeon.player.hasPower(LuckPower.POWER_ID)) {
            base += AbstractDungeon.player.getPower(LuckPower.POWER_ID).amount;
        }
        if (AbstractDungeon.player.hasPower(CoinflipPower.POWER_ID)) {
            base = AbstractDungeon.player.getPower(CoinflipPower.POWER_ID).amount;
        }
        if (AbstractDungeon.player.hasPower(RiggedPower.POWER_ID) || AbstractDungeon.player.hasRelic(SuperLuckyCoin.ID)) {
            base = 100;
        }
        if (AbstractDungeon.player.hasRelic(UnluckyCoin.ID)) {
            base = 0;
        }
        this.luck = base;
        this.isLuckModified = (this.luck != this.baseLuck);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        act(p, m);
        int rng = AbstractDungeon.cardRandomRng.random(99) + 1;
        if (rng <= luck) {
            CardCrawlGame.sound.play("UNLOCK_PING");
            for (int i = 0; i < maxLines; i++) {
                AbstractDungeon.effectList.add(new DamageLine(p.hb.cX, p.hb.cY, new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1), ((stride * i) + MathUtils.random(-stride, stride) + offset)));
                if (i % 2 == 0) {
                    AbstractDungeon.effectList.add(new DamageCurvy(p.hb.cX, p.hb.cY, new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1)));
                }
            }
            AbstractDungeon.effectList.add(new SuperWordAboveCreatureEffect(p, p.hb.cX, p.hb.cY, "LUCKY!"));
            lock(p, m);
            if (p.hasPower(RidiculousCrusadePower.POWER_ID)) {
                p.getPower(RidiculousCrusadePower.POWER_ID).flash();
                atb(new DrawCardAction(p, p.getPower(RidiculousCrusadePower.POWER_ID).amount));
            }
        } else {
            if (p.hasPower(BetAgainstPower.POWER_ID)) {
                p.getPower(BetAgainstPower.POWER_ID).flash();
                atb(new GainBlockAction(p, p, p.getPower(BetAgainstPower.POWER_ID).amount));
            }
        }
    }

    public abstract void act(AbstractPlayer p, AbstractMonster m);

    public abstract void lock(AbstractPlayer p, AbstractMonster m);

    public static String makeID(String blah) {
        return "luckmanmod:" + blah;
    }

    public void atb(AbstractGameAction action) {
        addToBot(action);
    }

    public DamageInfo makeInfo() {
        return makeInfo(damageTypeForTurn);
    }

    public DamageInfo makeInfo(DamageInfo.DamageType type) {
        return new DamageInfo(AbstractDungeon.player, damage, type);
    }

    public DamageAction dmg(AbstractMonster m, DamageInfo info, AbstractGameAction.AttackEffect fx) {
        return new DamageAction(m, info, fx);
    }

    public DamageAllEnemiesAction allDmg(AbstractGameAction.AttackEffect fx) {
        return new DamageAllEnemiesAction(AbstractDungeon.player, multiDamage, damageTypeForTurn, fx);
    }

    public GainBlockAction blck() {
        return new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, block);
    }

    public MakeTempCardInHandAction makeInHand(AbstractCard c, int i) {
        return new MakeTempCardInHandAction(c, i);
    }

    public MakeTempCardInHandAction makeInHand(AbstractCard c) {
        return makeInHand(c, 1);
    }

    public MakeTempCardInDrawPileAction shuffleIn(AbstractCard c, int i) {
        return new MakeTempCardInDrawPileAction(c, i, false, true);
    }

    public MakeTempCardInDrawPileAction shuffleIn(AbstractCard c) {
        return shuffleIn(c, 1);
    }

    public ArrayList<AbstractMonster> monsterList() {
        return AbstractDungeon.getMonsters().monsters;
    }

    ApplyPowerAction applyToEnemy(AbstractMonster m, AbstractPower po) {
        return new ApplyPowerAction(m, AbstractDungeon.player, po, po.amount);
    }

    ApplyPowerAction applyToSelf(AbstractPower po) {
        return new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, po, po.amount);
    }

    WeakPower autoWeak(AbstractMonster m, int i) {
        return new WeakPower(m, i, false);
    }

    VulnerablePower autoVuln(AbstractMonster m, int i) {
        return new VulnerablePower(m, i, false);
    }
}