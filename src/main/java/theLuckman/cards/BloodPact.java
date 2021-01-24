package theLuckman.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import theLuckman.CardIgnore;
import theLuckman.powers.BloodPactPower;


public class BloodPact extends AbstractLuckmanCard {

    public static final String ID = makeID(BloodPact.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    private static final int COST = 2;

    public BloodPact() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 3;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(applyToSelf(new BloodPactPower(1)));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(applyToSelf(new ArtifactPower(p, 100000)));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(1);
            upgradeLuck(2);
            initializeDescription();
        }
    }
}