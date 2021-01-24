package theLuckman.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class DemoralizeAction extends AbstractGameAction
{
    private boolean tog;
    public DemoralizeAction(AbstractCreature target, AbstractCreature source, boolean vulnFalseWeakTrue)
    {
        setValues(target, source);
        actionType = ActionType.WAIT;
        duration = Settings.ACTION_DUR_XFAST;
        tog = vulnFalseWeakTrue;
    }

    @Override
    public void update()
    {
        if (duration == Settings.ACTION_DUR_XFAST) {
            if (tog)
            {
                AbstractPower weak = target.getPower(WeakPower.POWER_ID);
                if (weak != null) {
                    weak.stackPower(weak.amount);
                    weak.flash();
                }
            }
            else
            {
                AbstractPower vuln = target.getPower(VulnerablePower.POWER_ID);
                if (vuln != null) {
                    vuln.stackPower(vuln.amount);
                    vuln.flash();
                }
            }
        }
        tickDuration();
    }
}