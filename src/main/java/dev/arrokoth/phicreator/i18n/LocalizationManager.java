package dev.arrokoth.phicreator.i18n;

import dev.arrokoth.phicreator.util.Utils;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

/**
 * @author Arrokoth
 * @project PhiCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class LocalizationManager {
    public static final Locale EN_US = new Locale(new JSONObject(new String(Utils.getResource("i18n/en_us.json"), StandardCharsets.UTF_8)));
    public static final Locale ZH_CN = new Locale(new JSONObject(new String(Utils.getResource("i18n/zh_cn.json"), StandardCharsets.UTF_8)));
    private static Locale LOCALE = EN_US;

    static {
        setLocale(java.util.Locale.getDefault().getLanguage().toLowerCase() + "_" + java.util.Locale.getDefault().getCountry().toLowerCase());
    }

    public static String getString(String key) {
        return LOCALE.getString(key);
    }

    public static void setLocale(String name) {
        switch (name.toLowerCase()) {
            case "en_us":
                LOCALE = EN_US;
                break;
            case "zh_cn":
                LOCALE = ZH_CN;
                break;
        }
    }
}
