package theLuckman.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;


public class UnspeakableCommune extends AbstractLuckmanCard {

    public static final String ID = makeID(UnspeakableCommune.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    private static final int COST = 0;

    public UnspeakableCommune() {
        super(ID, COST, TYPE, RARITY, TARGET);
        FleetingField.fleeting.set(this, true);
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        if (upgraded) {
            for (AbstractMonster q : monsterList()) {
                atb(applyToEnemy(q, autoWeak(m, 150000)));
                atb(applyToEnemy(q, autoVuln(m, 150000)));
            }
        } else {
            atb(applyToEnemy(m, autoWeak(m, 150000)));
            atb(applyToEnemy(m, autoVuln(m, 150000)));
        }
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