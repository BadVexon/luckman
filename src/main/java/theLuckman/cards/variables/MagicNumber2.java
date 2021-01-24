package theLuckman.cards.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theLuckman.cards.AbstractLuckmanCard;

public class MagicNumber2 extends DynamicVariable {
    @Override
    public String key() {
        return "lM2";
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof AbstractLuckmanCard) {
            return ((AbstractLuckmanCard) card).baseMagicNumber2;
        }
        return -1;
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof AbstractLuckmanCard) {
            return ((AbstractLuckmanCard) card).magicNumber2;
        }
        return -1;
    }

    @Override
    public boolean isModified(AbstractCard card) {
        if (card instanceof AbstractLuckmanCard) {
            return ((AbstractLuckmanCard) card).isMagicNumber2Modified;
        }
        return false;
    }

    @Override
    public void setIsModified(AbstractCard card, boolean v) {
        if (card instanceof AbstractLuckmanCard) {
            ((AbstractLuckmanCard) card).isMagicNumber2Modified = true;
        }
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        if (card instanceof AbstractLuckmanCard) {
            return ((AbstractLuckmanCard) card).upgradedMagicNumber2;
        }
        return false;
    }
}