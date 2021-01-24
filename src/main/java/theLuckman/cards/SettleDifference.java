package theLuckman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;


public class SettleDifference extends AbstractLuckmanCard {

    public static final String ID = makeID(SettleDifference.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 1;

    public SettleDifference() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseBlock = 8;
        baseDamage = 2;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(blck());
        atb(dmg(m, makeInfo(), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(2);
            upgradeBlock(2);
            initializeDescription();
        }
    }
}