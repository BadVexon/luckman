package theLuckman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.screens.DeathScreen;
import theLuckman.CardIgnore;


public class KAPOW extends AbstractLuckmanCard {

    public static final String ID = makeID(KAPOW.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 1;

    public KAPOW() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 1;
        baseDamage = 24;
        isMultiDamage = true;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(allDmg(AbstractGameAction.AttackEffect.FIRE));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractDungeon.player.isDead = true;
                AbstractDungeon.deathScreen = new DeathScreen(AbstractDungeon.getMonsters());
                isDone = true;
            }
        });
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(4);
            initializeDescription();
        }
    }
}