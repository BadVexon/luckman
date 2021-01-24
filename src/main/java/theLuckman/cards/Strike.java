package theLuckman.cards;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.GoldenSlashEffect;

public class Strike extends AbstractLuckmanCard {

    public static final String ID = makeID(Strike.class.getSimpleName());
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.BASIC;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    private static final int COST = 1;

    public Strike() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage = 6;
        baseLuck = luck = 5;
        this.tags.add(AbstractCard.CardTags.STRIKE);
        this.tags.add(BaseModCardTags.BASIC_STRIKE);
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new GoldenSlashEffect(m.hb.cX, m.hb.cY, true)));
        atb(dmg(m, makeInfo(), AbstractGameAction.AttackEffect.NONE));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(1));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(3);
            initializeDescription();
        }
    }

}