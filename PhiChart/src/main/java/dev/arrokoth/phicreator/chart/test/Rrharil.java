package dev.arrokoth.phicreator.chart.test;

import dev.arrokoth.phicreator.chart.phi.chart.Chart;
import dev.arrokoth.phicreator.chart.phi.chart.loader.JSONChartLoader;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Arrokoth
 * @project PhiCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class Rrharil {
    public static Chart get() {
        try {
            return new JSONChartLoader(
                    new JSONObject(new String(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream("Rrhar'il/Chart_IN.json")).readAllBytes())),
                    ClassLoader.getSystemResource("Rrhar'il/illustration.jpg"),
                    ClassLoader.getSystemResource("Rrhar'il/music.ogg")).load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
