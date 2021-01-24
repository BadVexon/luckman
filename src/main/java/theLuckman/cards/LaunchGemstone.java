package theLuckman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.actions.MakeFreeUntilPlayedAction;


public class LaunchGemstone extends AbstractLuckmanCard {

    public static final String ID = makeID(LaunchGemstone.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 2;

    public LaunchGemstone() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 30;
        baseDamage = 18;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(dmg(m, makeInfo(), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(new MakeFreeUntilPlayedAction(this.uuid));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(4);
            upgradeLuck(3);
            initializeDescription();
        }
    }
}