package theLuckman.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;
import theLuckman.actions.ReplayThisAction;


public class Helicopterrace extends AbstractLuckmanCard {

    public static final String ID = makeID(Helicopterrace.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 1;

    public Helicopterrace() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 15;
        exhaust = true;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();// 35
        c.setCostForTurn(0);// 36
        atb(new MakeTempCardInHandAction(c, true));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(new ReplayThisAction(m, this));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(0);
            upgradeLuck(5);
            initializeDescription();
        }
    }
}