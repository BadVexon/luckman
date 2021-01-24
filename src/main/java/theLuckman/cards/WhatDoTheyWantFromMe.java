package theLuckman.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theLuckman.CardIgnore;
import theLuckman.powers.WhatDoTheyWantPower;


public class WhatDoTheyWantFromMe extends AbstractLuckmanCard {

    public static final String ID = makeID(WhatDoTheyWantFromMe.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    private static final int COST = 3;

    public WhatDoTheyWantFromMe() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseLuck = luck = 5;
    }

    public void act(AbstractPlayer p, AbstractMonster m) {
        atb(applyToSelf(new WhatDoTheyWantPower(5)));
    }

    public void lock(AbstractPlayer p, AbstractMonster m) {
        atb(applyToSelf(new WhatDoTheyWantPower(5)));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(2);
            initializeDescription();
        }
    }
}