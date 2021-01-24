package theLuckman.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;
import theLuckman.actions.ChangeGoldAction;


public class ImNotFoolish extends AbstractLuckmanCard {

    public static final String ID = makeID(ImNotFoolish.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 1;

    public ImNotFoolish() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = 30;
        FleetingField.fleeting.set(this, true);
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(new ChangeGoldAction(magicNumber));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(20);
            initializeDescription();
        }
    }
}