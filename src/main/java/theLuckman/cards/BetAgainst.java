package theLuckman.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import theLuckman.CardIgnore;
import theLuckman.powers.BetAgainstPower;


public class BetAgainst extends AbstractLuckmanCard {

    public static final String ID = makeID(BetAgainst.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    private static final int COST = 1;

    public BetAgainst() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 5;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(applyToSelf(new BetAgainstPower(1)));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(applyToSelf(new IntangiblePlayerPower(p, 1)));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeLuck(5);
            initializeDescription();
        }
    }
}