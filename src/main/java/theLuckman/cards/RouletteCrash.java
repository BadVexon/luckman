package theLuckman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RouletteCrash extends AbstractLuckmanCard {

    public static final String ID = makeID(RouletteCrash.class.getSimpleName());
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.BASIC;
    private static final AbstractCard.CardTarget TARGET = CardTarget.ALL;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    private static final int COST = 2;

    public RouletteCrash() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage = 8;
        isMultiDamage = true;
        baseLuck = luck = 10;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(allDmg(AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(allDmg(AbstractGameAction.AttackEffect.FIRE));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(1);
            initializeDescription();
        }
    }

}