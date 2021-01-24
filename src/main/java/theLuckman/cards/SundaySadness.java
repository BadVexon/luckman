package theLuckman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.unique.DoubleYourBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import theLuckman.CardIgnore;
import theLuckman.util.EvilEye;


public class SundaySadness extends AbstractLuckmanCard {

    public static final String ID = makeID(SundaySadness.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 2;

    public SundaySadness() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 10;
        baseMagicNumber = magicNumber = 1;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            atb(new ChannelAction(new EvilEye()));
        }
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractOrb q : AbstractDungeon.player.orbs) {
                    if (q instanceof EvilEye) {
                        atb(new GainBlockAction(p, p, 3));
                    }
                }
                this.isDone = true;
            }
        });
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(new DoubleYourBlockAction(p));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}