package theLuckman.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import theLuckman.powers.LoseLuckPower;
import theLuckman.powers.LuckPower;

public class CasinoFlex extends AbstractLuckmanCard {

    public static final String ID = makeID(CasinoFlex.class.getSimpleName());
    private static final AbstractCard.CardRarity RARITY = CardRarity.COMMON;
    private static final AbstractCard.CardTarget TARGET = CardTarget.SELF;
    private static final AbstractCard.CardType TYPE = CardType.SKILL;
    private static final int COST = 0;

    public CasinoFlex() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = 5;
        baseLuck = luck = 5;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(applyToSelf(new LuckPower(magicNumber)));
        atb(applyToSelf(new LoseLuckPower(magicNumber)));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(applyToSelf(new ArtifactPower(p, 1)));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(5);
            initializeDescription();
        }
    }

}