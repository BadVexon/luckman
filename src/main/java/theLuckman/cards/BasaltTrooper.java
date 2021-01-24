package theLuckman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMiscAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.SearingBlowEffect;
import theLuckman.CardIgnore;
import theLuckman.actions.SuperIncreaseMiscAction;


@CardIgnore
public class BasaltTrooper extends AbstractLuckmanCard {

    public static final String ID = makeID(BasaltTrooper.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF_AND_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 2;

    public BasaltTrooper(int upgrades) {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseBlock = 12;
        misc = 6;
        baseDamage = this.misc;
        this.timesUpgraded = upgrades;
        exhaust = true;
    }

    public void applyPowers() {
        this.baseDamage = this.misc;// 46
        super.applyPowers();// 47
        this.initializeDescription();// 48
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new SearingBlowEffect(p.hb.cX, p.hb.cY, this.timesUpgraded), 0.2F));// 46
        atb(blck());
        atb(new SuperIncreaseMiscAction(this.uuid, this.misc, 1));
        atb(dmg(m, makeInfo(), AbstractGameAction.AttackEffect.SMASH));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
    }

    public AbstractCard makeCopy() {
        return new BasaltTrooper(timesUpgraded);
    }

    public boolean canUpgrade() {
        return true;// 73
    }

    public void upgrade() {
        this.upgradeBlock(4 + this.timesUpgraded);// 64
        ++this.timesUpgraded;// 65
        this.upgraded = true;// 66
        this.name = NAME + "+" + this.timesUpgraded;// 67
        this.initializeTitle();
    }
}