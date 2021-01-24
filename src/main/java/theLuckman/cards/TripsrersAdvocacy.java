package theLuckman.cards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.HeartBuffEffect;
import theLuckman.CardIgnore;
import theLuckman.actions.NiftyMovesAction;


public class TripsrersAdvocacy extends AbstractLuckmanCard {

    public static final String ID = makeID(TripsrersAdvocacy.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = -1;

    public TripsrersAdvocacy() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 10;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new HeartBuffEffect(p.hb.cX, p.hb.cY)));
        if (this.energyOnUse < EnergyPanel.totalCount) {
            this.energyOnUse = EnergyPanel.totalCount;
        }
        atb(new NiftyMovesAction(p, upgraded, freeToPlayOnce, energyOnUse));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(1));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}