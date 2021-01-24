package theLuckman.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;


public class ThickeningFear extends AbstractLuckmanCard {

    public static final String ID = makeID(ThickeningFear.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 1;

    public ThickeningFear() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 5;
        baseBlock = 6;
        exhaust = true;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(blck());
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(new StunMonsterAction(AbstractDungeon.getRandomMonster(), p, 2));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(3);
            initializeDescription();
        }
    }
}