package theLuckman.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;
import theLuckman.actions.ChangeGoldAction;


public class Jackpot extends AbstractLuckmanCard {

    public static final String ID = makeID(Jackpot.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 3;

    public Jackpot() {
        super(ID, COST, TYPE, RARITY, TARGET);
        FleetingField.fleeting.set(this, true);
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(0);
            initializeDescription();
        }
    }
}