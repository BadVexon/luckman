package theLuckman.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AlwaysRetainField;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;
import theLuckman.actions.ReplayThisAction;


public class DuplicateTerrain extends AbstractLuckmanCard {

    public static final String ID = makeID(DuplicateTerrain.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 1;

    public DuplicateTerrain() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 10;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        AbstractCard c = AbstractDungeon.actionManager.cardsPlayedThisCombat.get(0);
        atb(new ReplayThisAction(AbstractDungeon.getRandomMonster(), c));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(new PlayTopCardAction(AbstractDungeon.getRandomMonster(), false));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            AlwaysRetainField.alwaysRetain.set(this, true);
            upgradeLuck(5);
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}