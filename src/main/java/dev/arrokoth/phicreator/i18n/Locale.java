package dev.arrokoth.phicreator.i18n;

import org.json.JSONObject;

/**
 * @author Arrokoth
 * @project PhiCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class Locale {
    protected JSONObject json;

    public Locale(JSONObject json) {
        this.json = json;
    }

    public String getString(String key) {
        if (!json.has(key)) {
            System.err.println("Could not find Localization: " + key);
        }
        return json.has(key) ? json.get(key) instanceof String ? json.getString(key) : key : key;
    }
}
