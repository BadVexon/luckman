package theLuckman.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;


public class HopefulSmack extends AbstractLuckmanCard {

    public static final String ID = makeID(HopefulSmack.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 1;

    public HopefulSmack() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage = 8;
        baseLuck = luck = 5;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(dmg(m, makeInfo(), AbstractGameAction.AttackEffect.SMASH));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(new StunMonsterAction(m, p));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(4);
            initializeDescription();
        }
    }
}