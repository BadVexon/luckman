package theLuckman.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DemonFormPower;
import theLuckman.CardIgnore;
import theLuckman.powers.ImpossiblePrecisionPower;


public class    ImpossiblePrecision extends AbstractLuckmanCard {

    public static final String ID = makeID(ImpossiblePrecision.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    private static final int COST = 1;

    public ImpossiblePrecision() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = 3;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(applyToSelf(new DemonFormPower(p, magicNumber)));
        atb(applyToSelf(new ImpossiblePrecisionPower()));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}