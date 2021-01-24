package theLuckman.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;


public class ContagiousPain extends AbstractLuckmanCard {

    public static final String ID = makeID(ContagiousPain.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 1;

    public ContagiousPain() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 5;
        baseDamage = 15;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(new DamageAction(p, new DamageInfo(p, 5, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        atb(dmg(m, makeInfo(), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(new AddTemporaryHPAction(p, p, 5));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(5);
            initializeDescription();
        }
    }
}