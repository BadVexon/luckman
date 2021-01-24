package theLuckman.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;


public class FoolproofGuard extends AbstractLuckmanCard {

    public static final String ID = makeID(FoolproofGuard.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 1;

    public FoolproofGuard() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 15;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        if (upgraded) {
            for (AbstractMonster q : monsterList()) {
                if (!q.isDead && !q.isDying) {
                    atb(new StunMonsterAction(q, p));
                }
            }
        } else {
            atb(new StunMonsterAction(m, p));
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.target = CardTarget.ALL_ENEMY;
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}