package dev.arrokoth.phigcreator.config;

import dev.arrokoth.phigcreator.phi.editor.Project;
import dev.arrokoth.phigcreator.util.Utils;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collection;

/**
 * @author Arrokoth
 * @project PhigCreator
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

    public Project getProject() {
        if (json.has("latest_project") && !json.isNull("latest_project") && new File(json.getString("latest_project")).exists()) {
            // TODO: bps
            return new Project(new File(json.getString("latest_project")), 120);
        } else {
            return null;
        }
    }

    public void setProject(Project project) {
        System.out.println(project);
        if (project != null) {
            json.put("latest_project", project.root.getAbsolutePath());
        } else {
            json.put("latest_project", JSONObject.NULL);
        }
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
