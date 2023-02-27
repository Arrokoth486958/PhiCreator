package dev.arrokoth.phicreator.config;

import dev.arrokoth.phicreator.phi.editor.Project;
import dev.arrokoth.phicreator.util.Utils;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * @author Arrokoth
 * @project PhiCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class Configuration {
    public static final File FILE = new File(Utils.CURRENT_DIRECTORY, "config.json");
    public static final Configuration CONFIG = create();

    public static Configuration create() {
        try {
            assert FILE != null;
            if (FILE.exists()) {
                return new Configuration(new JSONObject(new String(Utils.readStream(Files.newInputStream(new File(Utils.CURRENT_DIRECTORY, "config.json").toPath())))));
            }
            return new Configuration(new JSONObject());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final JSONObject json;
    public Configuration(JSONObject json) {
        this.json = json;
    }

    public Dimension getWindowSize() {
        if (!json.has("window_size")) {
            json.put("window_size", new JSONObject().put("width", 1600).put("height", 900));
        }
        return new Dimension(json.getJSONObject("window_size").getInt("width"), json.getJSONObject("window_size").getInt("height"));
    }

    public boolean isWindowMaximized() {
        if (!json.has("window_maximized")) {
            json.put("window_maximized", true);
        }
        return json.getBoolean("window_maximized");
    }

    public Project getProject() {
        if (json.has("latest_project") && !json.isNull("latest_project") && new File(json.getString("latest_project")).exists()) {
            // TODO: bps
            return new Project(new File(json.getString("latest_project")), 120);
        } else {
            json.put("latest_project", JSONObject.NULL);
            return null;
        }
    }

    public void setProject(Project project) {
        if (project != null) {
            json.put("latest_project", project.root.getAbsolutePath());
        } else {
            json.put("latest_project", JSONObject.NULL);
        }
    }

    public void saveWindowState(JFrame frame) {
        json.put("window_size", new JSONObject().put("width", frame.getWidth()).put("height", frame.getHeight()));
        json.put("window_maximized", frame.getExtendedState() == JFrame.MAXIMIZED_BOTH);
    }

    public void save() {
        try {
            if (!FILE.exists()) {
                FILE.createNewFile();
            }
            new FileOutputStream(FILE).write(json.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
