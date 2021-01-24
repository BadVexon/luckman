package theLuckman.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theLuckman.CardIgnore;


public class Inferiority extends AbstractLuckmanCard {

    public static final String ID = makeID(Inferiority.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 1;

    public Inferiority() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 10;
        exhaust = true;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster q : monsterList()) {
            atb(applyToEnemy(q, new StrengthPower(q, -3)));
        }
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(applyToSelf(new StrengthPower(p, 3)));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            isInnate = true;
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}