package theLuckman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.actions.ReplayThisAction;


public class ForeverHall extends AbstractLuckmanCard {

    public static final String ID = makeID(ForeverHall.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 0;

    public ForeverHall() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 15;
        baseDamage = 3;
        baseBlock = 3;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(blck());
        atb(dmg(AbstractDungeon.getRandomMonster(), makeInfo(), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        for (AbstractCard c : p.discardPile.group) {
            if (c instanceof ForeverHall && !this.purgeOnUse) {
                atb(new ReplayThisAction(m, c));
            }
        }
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(1);
            upgradeBlock(1);
            initializeDescription();
        }
    }
}