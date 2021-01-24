package theLuckman.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class AngerBeyondBeliefAction extends AbstractGameAction {
    private AbstractPlayer p;

    public AngerBeyondBeliefAction() {
        this.p = AbstractDungeon.player;// 17
        this.duration = Settings.ACTION_DUR_FAST;// 18
        this.actionType = ActionType.CARD_MANIPULATION;// 19
    }// 20

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {// 24
            if (this.p.hand.isEmpty()) {// 25
                this.isDone = true;// 26
            } else if (this.p.hand.size() == 1) {// 28
                AbstractCard c = this.p.hand.getTopCard();// 29
                int amt = this.p.drawPile.size();
                p.drawPile.clear();
                AbstractDungeon.actionManager.addToTop(new MakeTempCardInDrawPileAction(c.makeStatEquivalentCopy(), amt, false, true));

                AbstractDungeon.player.hand.refreshHandLayout();// 34
                this.isDone = true;// 35
            } else {
                AbstractDungeon.handCardSelectScreen.open("ENRAGE!", 1, false);// 38
                this.tickDuration();// 39
            }
        } else {
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {// 46
                int amt = this.p.drawPile.size();
                p.drawPile.clear();
                for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {// 47 51
                    AbstractDungeon.actionManager.addToTop(new MakeTempCardInDrawPileAction(c.makeStatEquivalentCopy(), amt, false, true));
                    this.p.hand.addToTop(c);
                }

                AbstractDungeon.player.hand.refreshHandLayout();// 53
                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;// 54
                AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();

                this.isDone = true;
            }

            this.tickDuration();// 57
        }
    }// 27 36 40 58
}
