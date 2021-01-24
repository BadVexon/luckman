package theLuckman.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;


public class GuardedBet extends AbstractLuckmanCard {

    public static final String ID = makeID(GuardedBet.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 1;

    public GuardedBet() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseBlock = 11;
        baseLuck = luck = 80;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(blck());
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(4);
            initializeDescription();
        }
    }
}