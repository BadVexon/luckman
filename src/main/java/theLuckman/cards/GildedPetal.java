package theLuckman.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;
import theLuckman.LuckmanMod;


public class GildedPetal extends AbstractLuckmanCard {

    public static final String ID = makeID(GildedPetal.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 3;

    public GildedPetal() {
        super(ID, COST, TYPE, RARITY, TARGET);
        isEthereal = true;
        FleetingField.fleeting.set(this, true);
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                LuckmanMod.bonusBig = true;
                isDone = true;
            }
        });
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            FleetingField.fleeting.set(this, false);
            exhaust = true;
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}