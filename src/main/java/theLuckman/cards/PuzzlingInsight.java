package theLuckman.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;
import theLuckman.util.EvilEye;


public class PuzzlingInsight extends AbstractLuckmanCard {

    public static final String ID = makeID(PuzzlingInsight.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 1;

    public PuzzlingInsight() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = 2;
        baseLuck = luck = 10;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(p, magicNumber));
        for (int i = 0; i < magicNumber; i++) {
            atb(new ChannelAction(new EvilEye()));
        }
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(p, 1));
        atb(new ChannelAction(new EvilEye()));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}