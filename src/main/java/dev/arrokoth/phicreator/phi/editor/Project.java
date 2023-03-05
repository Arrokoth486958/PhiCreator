package dev.arrokoth.phicreator.phi.editor;

//import dev.arrokoth.phicreator.phi.chart.Chart;

import java.io.File;

/**
 * @author Arrokoth
 * @project PhiCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class Project {
    public final File root;

    public Project(File root, double bps) {
        this.root = root;
        // TODO: Chart loading
//        try {
//            this.chart = new Chart(bps, new ArrayList<>(), new ArrayList<>(), ImageIO.read(ClassLoader.getSystemResource("textures/icon.png")));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
