package theLuckman.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawPower;
import theLuckman.CardIgnore;
import theLuckman.actions.ReplayThisAction;


public class ImportantSundae extends AbstractLuckmanCard {

    public static final String ID = makeID(ImportantSundae.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    private static final int COST = 1;

    public ImportantSundae() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 10;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(applyToSelf(new DrawPower(p, 1)));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(new ReplayThisAction(m, this));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeLuck(10);
            initializeDescription();
        }
    }
}