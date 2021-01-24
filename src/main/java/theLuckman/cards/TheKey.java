package theLuckman.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class TheKey extends AbstractLuckmanCard {

    public static final String ID = makeID(TheKey.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 3;

    public TheKey() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 10;
        exhaust = true;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractCard c : AbstractDungeon.player.hand.group) {
                    if (c instanceof Impossibility) {
                        ((Impossibility) c).impossible = false;
                        c.rawDescription = c.rawDescription.replaceAll("Locked. NL ", "");
                        c.initializeDescription();
                        c.superFlash();
                    }
                }
                isDone = true;
            }
        });
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster q : monsterList()) {
            atb(new StunMonsterAction(q, p));
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(0);
            initializeDescription();
        }
    }
}