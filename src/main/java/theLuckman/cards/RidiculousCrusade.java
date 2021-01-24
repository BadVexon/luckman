package theLuckman.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;
import theLuckman.powers.RidiculousCrusadePower;


public class RidiculousCrusade extends AbstractLuckmanCard {

    public static final String ID = makeID(RidiculousCrusade.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    private static final int COST = 1;

    public RidiculousCrusade() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 10;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(applyToSelf(new RidiculousCrusadePower(2)));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(new AddTemporaryHPAction(p, p, 5));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(0);
            initializeDescription();
        }
    }
}