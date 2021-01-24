package theLuckman.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theLuckman.CardIgnore;
import theLuckman.actions.ReplayThisAction;


public class HorrifyingShuffle extends AbstractLuckmanCard {

    public static final String ID = makeID(HorrifyingShuffle.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF_AND_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 1;

    public HorrifyingShuffle() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 10;
        baseMagicNumber = magicNumber = 2;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(applyToEnemy(m, autoVuln(m, magicNumber)));
        atb(applyToSelf(new DexterityPower(p, this.magicNumber)));// 39
        atb(applyToSelf(new LoseDexterityPower(p, this.magicNumber)));// 41
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(new ReplayThisAction(m, this));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}