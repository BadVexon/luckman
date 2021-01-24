package theLuckman.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AlwaysRetainField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.actions.AngerBeyondBeliefAction;
import theLuckman.actions.MakeFreeUntilPlayedAction;


public class AngerBeyondBelief extends AbstractLuckmanCard {

    public static final String ID = makeID(AngerBeyondBelief.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 2;

    public AngerBeyondBelief() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 5;
        exhaust = true;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(new AngerBeyondBeliefAction());
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractCard c : p.drawPile.group) {
                    atb(new MakeFreeUntilPlayedAction(c.uuid));
                }
                isDone = true;
            }
        });
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            AlwaysRetainField.alwaysRetain.set(this, true);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}