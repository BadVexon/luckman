package theLuckman.cards;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;
import theLuckman.actions.ReplayThisAction;
import theLuckman.util.EvilEye;


public class ConflictedConfusion extends AbstractLuckmanCard {

    public static final String ID = makeID(ConflictedConfusion.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 1;

    public ConflictedConfusion() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 20;
        baseBlock = 6;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(blck());
        atb(new ChannelAction(new EvilEye()));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(new ReplayThisAction(m, this));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeLuck(13);
            initializeDescription();
        }
    }
}