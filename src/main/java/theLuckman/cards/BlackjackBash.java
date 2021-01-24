package theLuckman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theLuckman.CardIgnore;
import theLuckman.actions.DemoralizeAction;


public class BlackjackBash extends AbstractLuckmanCard {

    public static final String ID = makeID(BlackjackBash.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 2;

    public BlackjackBash() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage = 6;
        baseMagicNumber = magicNumber = 2;
        baseLuck = luck = 10;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(dmg(m, makeInfo(), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        atb(applyToEnemy(m, new VulnerablePower(m, magicNumber, false)));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(new DemoralizeAction(m, p, false));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(2);
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}