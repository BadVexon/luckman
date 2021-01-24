package theLuckman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;
import theLuckman.actions.ReplayThisAction;


public class ArrowShot extends AbstractLuckmanCard {

    public static final String ID = makeID(ArrowShot.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 1;

    public ArrowShot() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 25;
        baseDamage = 9;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(new DamageRandomEnemyAction(makeInfo(), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(new ReplayThisAction(m, this));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(3);
            initializeDescription();
        }
    }
}