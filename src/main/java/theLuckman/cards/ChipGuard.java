package theLuckman.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ChipGuard extends AbstractLuckmanCard {

    public static final String ID = makeID(ChipGuard.class.getSimpleName());
    private static final AbstractCard.CardRarity RARITY = CardRarity.COMMON;
    private static final AbstractCard.CardTarget TARGET = CardTarget.SELF;
    private static final AbstractCard.CardType TYPE = CardType.SKILL;
    private static final int COST = 1;

    public ChipGuard() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseBlock = 7;
        baseLuck = luck = 10;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(blck());
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(blck());
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(3);
            initializeDescription();
        }
    }
}