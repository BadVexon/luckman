package theLuckman.cards.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theLuckman.cards.AbstractLuckmanCard;

public class LuckVariable extends DynamicVariable {
    @Override
    public String key() {
        return "lLuck";
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof AbstractLuckmanCard) {
            return ((AbstractLuckmanCard) card).baseLuck;
        }
        return -1;
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof AbstractLuckmanCard) {
            return ((AbstractLuckmanCard) card).luck;
        }
        return -1;
    }

    @Override
    public boolean isModified(AbstractCard card) {
        if (card instanceof AbstractLuckmanCard) {
            return ((AbstractLuckmanCard) card).isLuckModified;
        }
        return false;
    }

    @Override
    public void setIsModified(AbstractCard card, boolean v) {
        if (card instanceof AbstractLuckmanCard) {
            ((AbstractLuckmanCard) card).isLuckModified = v;
        }
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        if (card instanceof AbstractLuckmanCard) {
            return ((AbstractLuckmanCard) card).upgradedLuck;
        }
        return false;
    }
}