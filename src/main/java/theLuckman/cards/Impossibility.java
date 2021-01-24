package theLuckman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ViceCrushEffect;
import theLuckman.CardIgnore;


public class Impossibility extends AbstractLuckmanCard {

    public static final String ID = makeID(Impossibility.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 3;

    public boolean impossible = true;

    public Impossibility() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage = 200000;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new ViceCrushEffect(m.hb.cX, m.hb.cY)));
        atb(dmg(m, makeInfo(), AbstractGameAction.AttackEffect.NONE));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (impossible) {
            this.cantUseMessage = EXTENDED_DESCRIPTION[0];
            return false;
        }
        return super.canUse(p, m);
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(980000);
            initializeDescription();
        }
    }
}