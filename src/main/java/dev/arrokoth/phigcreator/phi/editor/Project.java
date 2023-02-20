package dev.arrokoth.phigcreator.phi.editor;

import dev.arrokoth.phigcreator.phi.chart.Chart;
import dev.arrokoth.phigcreator.util.Utils;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Arrokoth
 * @project PhigCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class Project {
    public final File root;
    public final Chart chart;

    public Project(File root, double bps) {
        this.root = root;
        // TODO: Chart loading
        try {
            this.chart = new Chart(bps, new ArrayList<>(), new ArrayList<>(), ImageIO.read(ClassLoader.getSystemResource("textures/icon.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
