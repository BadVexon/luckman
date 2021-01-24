package theLuckman.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;
import theLuckman.powers.RiggedPower;


public class RiggedSystem extends AbstractLuckmanCard {

    public static final String ID = makeID(RiggedSystem.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 1;

    public RiggedSystem() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 5;
        baseMagicNumber = magicNumber = 1;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(applyToSelf(new RiggedPower(magicNumber)));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(applyToSelf(new RiggedPower(1)));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(0);
            initializeDescription();
        }
    }
}