package theLuckman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;


public class AsylumDevotees extends AbstractLuckmanCard {

    public static final String ID = makeID(AsylumDevotees.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF_AND_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 1;

    public AsylumDevotees() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 66;
        baseDamage = 8;
        baseBlock = 8;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(dmg(m, makeInfo(), AbstractGameAction.AttackEffect.SMASH));
        atb(blck());
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