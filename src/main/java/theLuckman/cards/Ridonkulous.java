package theLuckman.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;
import theLuckman.actions.ReplayThisAction;
import theLuckman.powers.RidonkulousPower;


public class Ridonkulous extends AbstractLuckmanCard {

    public static final String ID = makeID(Ridonkulous.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    private static final int COST = 2;

    public Ridonkulous() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 5;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(applyToSelf(new RidonkulousPower(1)));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(new ReplayThisAction(m, this));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            isInnate = true;
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}