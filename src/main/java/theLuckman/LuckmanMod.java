package theLuckman;

import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import javassist.CtClass;
import javassist.Modifier;
import javassist.NotFoundException;
import org.clapper.util.classutil.*;
import theLuckman.cards.BasaltTrooper;
import theLuckman.cards.variables.LuckVariable;
import theLuckman.cards.variables.MagicNumber2;
import theLuckman.relics.LuckyCoin;
import theLuckman.relics.SuperLuckyCoin;
import theLuckman.relics.UnluckyCoin;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings({"ConstantConditions", "unused", "WeakerAccess"})
@SpireInitializer
public class LuckmanMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber {
    public static final String LUCKMAN_CHAR_SHOULDER_PNG = "luckmanmodResources/images/char/luckmanChar/shoulder.png";
    public static final String LUCKMAN_CHAR_SHOULDER_2_PNG = "luckmanmodResources/images/char/luckmanChar/shoulder2.png";
    public static final String LUCKMAN_CHAR_CORPSE_PNG = "luckmanmodResources/images/char/luckmanChar/corpse.png";
    private static final String ATTACK_S_PNG = "luckmanmodResources/images/512/canvas_attack_s.png";
    private static final String SKILL_S_PNG = "luckmanmodResources/images/512/canvas_skill_s.png";
    private static final String POWER_S_PNG = "luckmanmodResources/images/512/canvas_power_s.png";
    private static final String ORB_PNG = "luckmanmodResources/images/512/card_default_gray_orb.png";
    private static final String SMALL_ORB_PNG = "luckmanmodResources/images/512/card_small_orb.png";
    private static final String ATTACK_PNG = "luckmanmodResources/images/1024/canvas_attack.png";
    private static final String SKILL_PNG = "luckmanmodResources/images/1024/canvas_skill.png";
    private static final String POWER_PNG = "luckmanmodResources/images/1024/canvas_power.png";
    private static final String BIG_ARTIST_CARD_ENERGY_ORB = "luckmanmodResources/images/1024/card_default_gray_orb.png";
    private static final String CHARACTER_BUTTON_PNG = "luckmanmodResources/images/charSelect/LuckmanCharacterButton.png";
    private static final String CHARACTER_BG_PNG = "luckmanmodResources/images/charSelect/LuckmanCharacterBG.png";
    private static String modID;

    public static Color LUCKMAN_RED = new Color(0.560F, 0F, 0F, 1F);

    public static boolean bonusBig;

    public LuckmanMod() {

        BaseMod.subscribe(this);

        modID = "luckmanmod";

        BaseMod.addColor(TheLuckman.Enums.COLOR_RED, LUCKMAN_RED, LUCKMAN_RED, LUCKMAN_RED,
                LUCKMAN_RED, LUCKMAN_RED, LUCKMAN_RED, LUCKMAN_RED,
                ATTACK_S_PNG, SKILL_S_PNG, POWER_S_PNG, ORB_PNG,
                ATTACK_PNG, SKILL_PNG, POWER_PNG,
                BIG_ARTIST_CARD_ENERGY_ORB, SMALL_ORB_PNG);

    }

    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/images/cards/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/images/relics/" + resourcePath;
    }

    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/images/relics/outline/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/images/powers/" + resourcePath;
    }

    public static String getModID() {
        return modID;
    }

    @SuppressWarnings("unused")
    public static void initialize() {
        LuckmanMod luckmanMod = new LuckmanMod();
    }

    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new TheLuckman("the Luckman", TheLuckman.Enums.THE_LUCKMAN),
                CHARACTER_BUTTON_PNG, CHARACTER_BG_PNG, TheLuckman.Enums.THE_LUCKMAN);
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelicToCustomPool(new LuckyCoin(), TheLuckman.Enums.COLOR_RED);
        BaseMod.addRelicToCustomPool(new UnluckyCoin(), TheLuckman.Enums.COLOR_RED);
        BaseMod.addRelicToCustomPool(new SuperLuckyCoin(), TheLuckman.Enums.COLOR_RED);
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addDynamicVariable(new MagicNumber2());
        BaseMod.addDynamicVariable(new LuckVariable());

        try {
            autoAddCards();
        } catch (URISyntaxException | IllegalAccessException | InstantiationException | NotFoundException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        BaseMod.addCard(new BasaltTrooper(0));
    }

    private static void autoAddCards()
            throws URISyntaxException, IllegalAccessException, InstantiationException, NotFoundException, ClassNotFoundException {
        ClassFinder finder = new ClassFinder();
        URL url = LuckmanMod.class.getProtectionDomain().getCodeSource().getLocation();
        finder.add(new File(url.toURI()));

        ClassFilter filter =
                new AndClassFilter(
                        new NotClassFilter(new InterfaceOnlyClassFilter()),
                        new NotClassFilter(new AbstractClassFilter()),
                        new ClassModifiersClassFilter(Modifier.PUBLIC),
                        new CardFilter()
                );
        Collection<ClassInfo> foundClasses = new ArrayList<>();
        finder.findClasses(foundClasses, filter);

        for (ClassInfo classInfo : foundClasses) {
            CtClass cls = Loader.getClassPool().get(classInfo.getClassName());
            if (cls.hasAnnotation(CardIgnore.class)) {
                continue;
            }
            boolean isCard = false;
            CtClass superCls = cls;
            while (superCls != null) {
                superCls = superCls.getSuperclass();
                if (superCls == null) {
                    break;
                }
                if (superCls.getName().equals(AbstractCard.class.getName())) {
                    isCard = true;
                    break;
                }
            }
            if (!isCard) {
                continue;
            }
            System.out.println(classInfo.getClassName());
            AbstractCard card = (AbstractCard) Loader.getClassPool().getClassLoader().loadClass(cls.getName()).newInstance();
            BaseMod.addCard(card);
            if (cls.hasAnnotation(CardNoSeen.class)) {
                UnlockTracker.hardUnlockOverride(card.cardID);
            } else {
                UnlockTracker.unlockCard(card.cardID);
            }
        }
    }


    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, getModID() + "Resources/localization/eng/LuckmanMod-Card-Strings.json");

        BaseMod.loadCustomStringsFile(PowerStrings.class, getModID() + "Resources/localization/eng/LuckmanMod-Power-Strings.json");

        BaseMod.loadCustomStringsFile(RelicStrings.class, getModID() + "Resources/localization/eng/LuckmanMod-Relic-Strings.json");

        BaseMod.loadCustomStringsFile(CharacterStrings.class, getModID() + "Resources/localization/eng/LuckmanMod-Character-Strings.json");
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(getModID() + "Resources/localization/eng/LuckmanMod-Keyword-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }
}
