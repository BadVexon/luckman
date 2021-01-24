package theLuckman.cards;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Defend extends AbstractLuckmanCard {

    public static final String ID = makeID(Defend.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 1;

    public Defend() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseBlock = 5;
        baseLuck = luck = 5;
        this.tags.add(BaseModCardTags.BASIC_DEFEND);
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(blck());
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(1));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(3);
            initializeDescription();
        }
    }

}