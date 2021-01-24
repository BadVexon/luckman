package theLuckman.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;


public class CrushingDebt extends AbstractLuckmanCard {

    public static final String ID = makeID(CrushingDebt.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 1;

    public CrushingDebt() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage = 6;
        baseMagicNumber = magicNumber = 1;
        baseMagicNumber2 = magicNumber2 = 3;
        baseLuck = luck = 15;
        isMultiDamage = true;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(allDmg(AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        atb(new DrawCardAction(p, magicNumber));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(new AddTemporaryHPAction(p, p, magicNumber2));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            upgradeDamage(2);
            upgradeMagicNumber(1);
            upgradeMagicNumber2(1);
            initializeDescription();
        }
    }
}