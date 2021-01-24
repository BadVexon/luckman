package theLuckman.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;


public class InTheCards extends AbstractLuckmanCard {

    public static final String ID = makeID(InTheCards.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 1;

    public InTheCards() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 20;
        baseMagicNumber = magicNumber = 2;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(p, magicNumber));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(2));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}