package theLuckman.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import theLuckman.CardIgnore;


public class BigBet extends AbstractLuckmanCard {

    public static final String ID = makeID(BigBet.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    private static final int COST = 1;

    public BigBet() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 10;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(applyToSelf(new ArtifactPower(p, 2)));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(applyToSelf(new ArtifactPower(p, 2)));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.isInnate = true;
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}