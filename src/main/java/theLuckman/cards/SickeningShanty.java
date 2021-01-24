package theLuckman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class SickeningShanty extends AbstractLuckmanCard {

    public static final String ID = makeID(SickeningShanty.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 3;

    public SickeningShanty() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage = 9;
        baseBlock = 9;
        baseLuck = luck = 50;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(blck());
        atb(dmg(m, makeInfo(), AbstractGameAction.AttackEffect.SMASH));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(3));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(2);
            upgradeBlock(2);
            initializeDescription();
        }
    }
}