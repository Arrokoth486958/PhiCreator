package dev.arrokoth.phicreator.player;

import dev.arrokoth.phicreator.chart.phi.chart.Chart;
import dev.arrokoth.phicreator.chart.test.Rrharil;

import java.awt.*;

/**
 * @author ${USER}
 * @project ${PROJECT_NAME}
 * @copyright Copyright © ${YEAR} ${USER} All Rights Reserved.
 */
public class Main {
    public static void main(String[] args) {
        Chart chart = Rrharil.get();
//        System.out.println(chart);
//        System.out.println(System.getProperty("sun.java2d.opengl"));
//        System.setProperty("sun.java2d.opengl", "true");
//        System.setProperty("sun.java2d.d3d", "true");
        Player.run(chart, new Dimension(1200, 700));
    }
}