package theLuckman.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.SmokeBombEffect;
import theLuckman.CardIgnore;


public class FleetingFear extends AbstractLuckmanCard {

    public static final String ID = makeID(FleetingFear.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 0;

    public FleetingFear() {
        super(ID, COST, TYPE, RARITY, TARGET);
        FleetingField.fleeting.set(this, true);
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractDungeon.getCurrRoom().smoked = !upgraded;// 33
                atb(new VFXAction(new SmokeBombEffect(p.hb.cX, p.hb.cY)));// 34
                AbstractDungeon.player.hideHealthBar();// 35
                AbstractDungeon.player.isEscaping = true;// 36
                AbstractDungeon.player.flipHorizontal = !AbstractDungeon.player.flipHorizontal;// 37
                AbstractDungeon.overlayMenu.endTurnButton.disable();// 38
                AbstractDungeon.player.escapeTimer = 2.5F;
                isDone = true;
            }
        });
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}