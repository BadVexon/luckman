package theLuckman.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;
import theLuckman.actions.ShardAction;


public class Shard extends AbstractLuckmanCard {

    public static final String ID = makeID(Shard.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF_AND_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 1;

    public Shard() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 5;
        baseMagicNumber = magicNumber = 5;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(new ShardAction(magicNumber, m));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(new StunMonsterAction(m, p));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
            upgradeLuck(2);
            initializeDescription();
        }
    }
}