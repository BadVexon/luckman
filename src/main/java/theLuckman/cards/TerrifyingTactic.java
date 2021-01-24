package theLuckman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;


public class TerrifyingTactic extends AbstractLuckmanCard {

    public static final String ID = makeID(TerrifyingTactic.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 2;

    public TerrifyingTactic() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 10;
        baseBlock = 12;
        baseMagicNumber = 1;
        baseDamage = 12;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(dmg(m, makeInfo(), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        atb(applyToEnemy(m, autoVuln(m, magicNumber)));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(blck());
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(3);
            upgradeMagicNumber(1);
            upgradeDamage(3);
            initializeDescription();
        }
    }
}