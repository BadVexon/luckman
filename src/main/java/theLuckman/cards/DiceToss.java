package theLuckman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;
import theLuckman.actions.ReplayThisAction;


public class DiceToss extends AbstractLuckmanCard {

    public static final String ID = makeID(DiceToss.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 0;

    public DiceToss() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 20;
        baseDamage = 3;
        baseBlock = 3;
        isMultiDamage = true;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(allDmg(AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        atb(blck());
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(new ReplayThisAction(m, this));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeLuck(13);
            initializeDescription();
        }
    }
}