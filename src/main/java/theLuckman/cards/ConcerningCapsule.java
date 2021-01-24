package theLuckman.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;


public class ConcerningCapsule extends AbstractLuckmanCard {

    public static final String ID = makeID(ConcerningCapsule.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 1;

    public ConcerningCapsule() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 15;
        baseBlock = 4;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(blck());
        p.drawPile.getTopCard().freeToPlayOnce = true;
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(p, 1));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(2);
            upgradeLuck(5);
            initializeDescription();
        }
    }
}