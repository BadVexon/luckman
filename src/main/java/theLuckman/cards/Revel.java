package theLuckman.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;


public class Revel extends AbstractLuckmanCard {

    public static final String ID = makeID(Revel.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 0;

    public Revel() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 15;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(1));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(1));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeLuck(10);
            initializeDescription();
        }
    }
}