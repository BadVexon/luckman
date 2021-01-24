package theLuckman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theLuckman.CardIgnore;
import theLuckman.powers.LoseLuckPower;
import theLuckman.powers.LuckPower;


public class HatlessOne extends AbstractLuckmanCard {

    public static final String ID = makeID(HatlessOne.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 2;

    public HatlessOne() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 5;
        baseDamage = 8;
        baseMagicNumber = magicNumber = 5;
        baseMagicNumber2 = magicNumber2 = 2;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(dmg(m, makeInfo(), AbstractGameAction.AttackEffect.SMASH));
        atb(applyToSelf(new LuckPower(magicNumber)));
        atb(applyToSelf(new LoseLuckPower(magicNumber)));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(applyToSelf(new StrengthPower(p, magicNumber2)));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(4);
            upgradeMagicNumber(5);
            upgradeMagicNumber2(1);
            initializeDescription();
        }
    }
}