package theLuckman.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;
import theLuckman.actions.ChooseIntentAction;
import theLuckman.actions.ExhaustConditionalCardsAction;
import theLuckman.actions.KillEnemyAction;
import theLuckman.powers.TrueFormPower;


public class TrueForm extends AbstractLuckmanCard {

    public static final String ID = makeID(TrueForm.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    private static final int COST = 3;

    public TrueForm() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 1;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(new ExhaustConditionalCardsAction(c -> true));
        atb(applyToSelf(new TrueFormPower()));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster q : monsterList()) {
            AbstractDungeon.actionManager.addToTop(new KillEnemyAction(q));
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(2);
            initializeDescription();
        }
    }
}