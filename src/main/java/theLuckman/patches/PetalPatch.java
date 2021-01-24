package theLuckman.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.sun.org.apache.bcel.internal.generic.LUSHR;
import theLuckman.LuckmanMod;

import java.util.ArrayList;

public class PetalPatch {
    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "getRewardCards"
    )
    public static class AddCardReward {
        public static ArrayList<AbstractCard> Postfix(ArrayList<AbstractCard> __result) {
            if (LuckmanMod.bonusBig) {
                int blah = __result.size();
                __result.clear();
                for (int i = 0; i < blah; i++) {
                    __result.add(CardLibrary.getAnyColorCard(AbstractCard.CardRarity.RARE));
                }
                LuckmanMod.bonusBig = false;
            }

            return __result;
        }
    }
}