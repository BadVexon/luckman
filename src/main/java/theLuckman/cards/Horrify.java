package theLuckman.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import theLuckman.util.EvilEye;

public class Horrify extends AbstractLuckmanCard {

    public static final String ID = makeID(Horrify.class.getSimpleName());
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.BASIC;
    private static final AbstractCard.CardTarget TARGET = CardTarget.SELF;
    private static final AbstractCard.CardType TYPE = CardType.SKILL;
    private static final int COST = 1;

    public Horrify() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = 3;
        baseLuck = luck = 10;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(p, magicNumber));
        atb(new ChannelAction(new EvilEye()));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : monsterList()) {
            if (!mo.isDying && !mo.isDead) {
                atb(applyToEnemy(mo, new PoisonPower(mo, p, 3)));
            }
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
            initializeDescription();
        }
    }

}