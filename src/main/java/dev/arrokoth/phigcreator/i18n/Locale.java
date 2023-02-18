package dev.arrokoth.phigcreator.i18n;

import org.json.JSONObject;

/**
 * @author Arrokoth
 * @project PhigCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class Locale {
    protected JSONObject json;

    public Locale(JSONObject json) {
        this.json = json;
    }

    public String getString(String key) {
        return json.has(key) ? json.get(key) instanceof String ? json.getString(key) : key : key;
    }
}
