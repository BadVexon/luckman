package theLuckman.actions;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.screens.select.GridCardSelectScreen;
import theLuckman.LuckmanMod;
import theLuckman.TheLuckman;
import theLuckman.patches.CenterGridCardSelectScreen;
import theLuckman.powers.TrueFormPower;
import theLuckman.util.DummyMonster;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static theLuckman.LuckmanMod.makeCardPath;

public class ChooseIntentAction extends AbstractGameAction {
    private DummyMonster dum;
    private boolean pickCard = false;

    public ChooseIntentAction(DummyMonster d) {
        this.dum = d;
        duration = Settings.ACTION_DUR_XFAST;
        actionType = ActionType.WAIT;
        target = AbstractDungeon.player;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_XFAST) {
            pickCard = true;
            CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            group.addToTop(new IntentChoiceCard("Aggressive", makeCardPath("ATTACK.png"), "Intend to Attack."));
            group.addToTop(new IntentChoiceCard("Aggressive", makeCardPath("ATTACK_BUFF.png"), "Intend to use a Buff and Attack."));
            group.addToTop(new IntentChoiceCard("Strategic", makeCardPath("ATTACK_DEBUFF.png"), "Intend to inflict a Negative Effect and Attack."));
            group.addToTop(new IntentChoiceCard("Aggressive", makeCardPath("ATTACK_BLOCK.png"), "Intend to Block and Attack."));
            group.addToTop(new IntentChoiceCard("Strategic", makeCardPath("BUFF.png"), "Intend to use a Buff."));
            group.addToTop(new IntentChoiceCard("Strategic", makeCardPath("DEBUFF.png"), "Intend to inflict a Negative Effect."));
            group.addToTop(new IntentChoiceCard("Strategic", makeCardPath("MEGA_DEBUFF.png"), "Intend to inflict a powerful Negative Effect."));
            group.addToTop(new IntentChoiceCard("Defensive", makeCardPath("BLOCK.png"), "Intend to Block."));
            group.addToTop(new IntentChoiceCard("Defensive", makeCardPath("BLOCK_DEBUFF.png"), "Intend to Block and inflict a Negative Effect."));
            group.addToTop(new IntentChoiceCard("Defensive", makeCardPath("BLOCK_BUFF.png"), "Intend to Block and use a Buff."));
            group.addToTop(new IntentChoiceCard("Cowardly", makeCardPath("ESCAPE.png"), "Intend to Escape."));
            group.addToTop(new IntentChoiceCard("Magical", makeCardPath("MAGIC.png"), "Intend to use mysterious Magic."));
            group.addToTop(new IntentChoiceCard("Sleeping", makeCardPath("SLEEP.png"), "Sleep."));
            group.addToTop(new IntentChoiceCard("Stunned", makeCardPath("STUN.png"), "Stunned."));
            group.addToTop(new IntentChoiceCard("Unknown", makeCardPath("UNKNOWN.png"), "Intend unknown (not attacking)."));

            CenterGridCardSelectScreen.centerGridSelect = true;
            AbstractDungeon.gridSelectScreen.open(group, 1, "Choose an Intent.", false, false, false, false);
            try {
                Method m = GridCardSelectScreen.class.getDeclaredMethod("updateCardPositionsAndHoverLogic");
                m.setAccessible(true);
                m.invoke(AbstractDungeon.gridSelectScreen);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignore) {
            }
        } else {
            if (pickCard && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                pickCard = false;
                CenterGridCardSelectScreen.centerGridSelect = false;

                for (AbstractCard cardChoice : AbstractDungeon.gridSelectScreen.selectedCards) {
                    switch (cardChoice.rawDescription) {
                        case "Intend to Attack.": {
                            dum.setMove((byte) 0, AbstractMonster.Intent.ATTACK, 25);
                            break;
                        }
                        case "Intend to use a Buff and Attack.": {
                            dum.setMove((byte) 0, AbstractMonster.Intent.ATTACK_BUFF, 15);
                            break;
                        }
                        case "Intend to inflict a Negative Effect and Attack.": {
                            dum.setMove((byte) 0, AbstractMonster.Intent.ATTACK_DEBUFF, 20);
                            break;
                        }
                        case "Intend to Block and Attack.": {
                            dum.setMove((byte) 0, AbstractMonster.Intent.ATTACK_DEFEND, 15);
                            break;
                        }
                        case "Intend to use a Buff.": {
                            dum.setMove((byte) 0, AbstractMonster.Intent.BUFF);
                            break;
                        }
                        case "Intend to inflict a Negative Effect.": {
                            dum.setMove((byte) 0, AbstractMonster.Intent.DEBUFF);
                            break;
                        }
                        case "Intend to inflict a powerful Negative Effect.": {
                            dum.setMove((byte) 0, AbstractMonster.Intent.STRONG_DEBUFF);
                            break;
                        }
                        case "Intend to Block.": {
                            dum.setMove((byte) 0, AbstractMonster.Intent.DEFEND);
                            break;
                        }
                        case "Intend to Block and inflict a Negative Effect.": {
                            dum.setMove((byte) 0, AbstractMonster.Intent.DEFEND_DEBUFF);
                            break;
                        }
                        case "Intend to Block and use a Buff.": {
                            dum.setMove((byte) 0, AbstractMonster.Intent.DEFEND_BUFF);
                            break;
                        }
                        case "Intend to Escape.": {
                            dum.setMove((byte) 0, AbstractMonster.Intent.ESCAPE);
                            break;
                        }
                        case "Intend to use mysterious Magic.": {
                            dum.setMove((byte) 0, AbstractMonster.Intent.MAGIC);
                            break;
                        }
                        case "Sleep.": {
                            dum.setMove((byte) 0, AbstractMonster.Intent.SLEEP);
                            break;
                        }
                        case "Stunned.": {
                            dum.setMove((byte) 0, AbstractMonster.Intent.STUN);
                            break;
                        }
                        case "Intend unknown (not attacking).": {
                            dum.setMove((byte) 0, AbstractMonster.Intent.UNKNOWN);
                            break;
                        }
                    }
                }

                dum.createIntent();
                if (AbstractDungeon.player.hasPower(TrueFormPower.POWER_ID)) {
                    AbstractDungeon.player.getPower(TrueFormPower.POWER_ID).updateDescription();
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                CenterGridCardSelectScreen.centerGridSelect = false;
                isDone = true;
            }
        }
        tickDuration();
    }

    private static class IntentChoiceCard extends CustomCard {
        private static final int COST = -2;
        public String CPY;

        IntentChoiceCard(String name, String IMG, String description) {
            super(makeID(name), name, IMG, COST, description, CardType.POWER, TheLuckman.Enums.COLOR_RED, CardRarity.SPECIAL, CardTarget.NONE);
            CPY = IMG;
        }

        private static String makeID(String id) {
            return LuckmanMod.makeID("Intending" + id);
        }

        @Override
        public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        }

        @Override
        public void upgrade() {
        }
    }
}