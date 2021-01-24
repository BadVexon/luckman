package theLuckman.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;
import theLuckman.CardIgnore;


public class Dodge extends AbstractLuckmanCard {

    public static final String ID = makeID(Dodge.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    private static final int COST = 1;

    public Dodge() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 50;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(applyToSelf(new BufferPower(p, 1)));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeLuck(16);
            initializeDescription();
        }
    }
}