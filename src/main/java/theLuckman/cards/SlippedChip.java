package theLuckman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;


public class SlippedChip extends AbstractLuckmanCard {

    public static final String ID = makeID(SlippedChip.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 1;

    public SlippedChip() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 20;
        baseDamage = 9;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(dmg(m, makeInfo(), AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(1));
        atb(new DrawCardAction(p, 1));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(3);
            initializeDescription();
        }
    }
}