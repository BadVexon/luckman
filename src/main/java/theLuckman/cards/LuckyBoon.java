package theLuckman.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;
import theLuckman.powers.LuckPower;


public class LuckyBoon extends AbstractLuckmanCard {

    public static final String ID = makeID(LuckyBoon.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    private static final int COST = 1;

    public LuckyBoon() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 5;
        baseMagicNumber = magicNumber = 5;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(applyToSelf(new LuckPower(magicNumber)));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(applyToSelf(new LuckPower(magicNumber)));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(5);
            initializeDescription();
        }
    }
}