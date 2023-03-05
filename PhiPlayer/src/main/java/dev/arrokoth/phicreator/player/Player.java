package dev.arrokoth.phicreator.player;

import dev.arrokoth.phicreator.chart.phi.chart.Chart;

import java.awt.*;

/**
 * @author Arrokoth
 * @project PhiCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class Player {
    public static PlayerClient CLIENT_INSTANCE = null;

    public static void run(Chart chart, Dimension size) {
        if (CLIENT_INSTANCE != null) {
            return;
        }
        CLIENT_INSTANCE = new PlayerClient(chart, size);
        CLIENT_INSTANCE.play();
    }
}
