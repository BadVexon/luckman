package theLuckman.patches;

import com.evacipated.cardcrawl.mod.stslib.StSLib;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theLuckman.powers.FadingFormPower;

@SpirePatch(
    cls = "com.megacrit.cardcrawl.actions.utility.UseCardAction",
    method = "<ctor>",
    paramtypes = {"com.megacrit.cardcrawl.cards.AbstractCard", "com.megacrit.cardcrawl.core.AbstractCreature"}
)
public class FadingPatches {
    public FadingPatches() {
    }// 17

    public static void Prefix(UseCardAction __instance, @ByRef AbstractCard[] card, AbstractCreature target) {
        if (AbstractDungeon.player.hasPower(FadingFormPower.POWER_ID)) {// 21
            card[0].purgeOnUse = true;// 22
            AbstractCard c = StSLib.getMasterDeckEquivalent(card[0]);// 23
            if (c != null) {// 24
                AbstractDungeon.player.masterDeck.removeCard(c);// 25
            }
        }

    }// 28
}
