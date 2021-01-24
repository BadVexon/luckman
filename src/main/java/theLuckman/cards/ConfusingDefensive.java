package theLuckman.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;
import theLuckman.util.EvilEye;


public class ConfusingDefensive extends AbstractLuckmanCard {

    public static final String ID = makeID(ConfusingDefensive.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 1;

    public ConfusingDefensive() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseBlock = 6;
        baseMagicNumber = 1;
        baseLuck = luck = 10;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(blck());
        atb(new DrawCardAction(p, magicNumber));
        atb(new ChannelAction(new EvilEye()));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(new ChannelAction(new EvilEye()));
        atb(new ChannelAction(new EvilEye()));
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