package theLuckman.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;


public class ConsumeTheDiscovery extends AbstractLuckmanCard {

    public static final String ID = makeID(ConsumeTheDiscovery.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 1;

    public ConsumeTheDiscovery() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 10;
        baseBlock = 5;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        int i = p.currentBlock;
        atb(new RemoveAllBlockAction(p, p));
        atb(new AddTemporaryHPAction(p, p, i));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(blck());
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(0);
            initializeDescription();
        }
    }
}