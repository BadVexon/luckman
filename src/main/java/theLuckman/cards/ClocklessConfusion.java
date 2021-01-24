package theLuckman.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;


public class ClocklessConfusion extends AbstractLuckmanCard {

    public static final String ID = makeID(ClocklessConfusion.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 1;

    public ClocklessConfusion() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = 1;
        baseLuck = luck = 20;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster q : monsterList()) {
            atb(applyToEnemy(q, autoWeak(q, magicNumber)));
        }
        for (AbstractMonster q : monsterList()) {
            atb(applyToEnemy(q, autoVuln(q, magicNumber)));
        }
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(1));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}