package theLuckman.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;
import theLuckman.powers.CoinflipPower;


public class CoinflipForm extends AbstractLuckmanCard {

    public static final String ID = makeID(CoinflipForm.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    private static final int COST = 3;

    public CoinflipForm() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = 50;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(applyToSelf(new CoinflipPower(magicNumber)));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {

    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(16);
            initializeDescription();
        }
    }
}