package theLuckman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;
import theLuckman.actions.DemoralizeAction;


public class TableClearer extends AbstractLuckmanCard {

    public static final String ID = makeID(TableClearer.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 0;

    public TableClearer() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage = 3;
        baseMagicNumber = magicNumber = 1;
        baseLuck = luck = 10;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(dmg(m, makeInfo(), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        atb(applyToEnemy(m, autoWeak(m, magicNumber)));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(new DemoralizeAction(m, p, true));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(1);
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}