package dev.arrokoth.phicreator.phi.editor;

import dev.arrokoth.phicreator.chart.phi.chart.Chart;
//import dev.arrokoth.phicreator.phi.chart.Chart;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Arrokoth
 * @project PhiCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class Project {
    public final File root;
    public final Chart chart;

    public Project(File root, double bps) {
        this.root = root;
        // TODO: Chart loading
        this.chart = new Chart();
//        try {
//            this.chart = new Chart(bps, new ArrayList<>(), new ArrayList<>(), ImageIO.read(ClassLoader.getSystemResource("textures/icon.png")));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
