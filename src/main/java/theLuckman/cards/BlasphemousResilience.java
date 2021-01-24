package theLuckman.cards;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import theLuckman.CardIgnore;
import theLuckman.powers.LoseLuckAfterPlayPower;
import theLuckman.powers.LuckPower;
import theLuckman.util.EvilEye;


public class BlasphemousResilience extends AbstractLuckmanCard {

    public static final String ID = makeID(BlasphemousResilience.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 1;

    public BlasphemousResilience() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 5;
        baseMagicNumber = magicNumber = 1;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(new ChannelAction(new EvilEye()));
        atb(applyToSelf(new LuckPower(10)));
        atb(applyToSelf(new LoseLuckAfterPlayPower(10, magicNumber)));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(applyToSelf(new ArtifactPower(p, 1)));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}