package dev.arrokoth.phigcreator.phi.chart;

import java.awt.*;
import java.util.List;

/**
 * @author Arrokoth
 * @project PhigCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class Chart {
    public final double bps;
    public final List<Note> notes;
    public final List<Line> lines;
    public final Image illustration;

    public Chart(double bps, List<Note> notes, List<Line> lines, Image illustration) {
        this.bps = bps;
        this.notes = notes;
        this.lines = lines;
        this.illustration = illustration;
    }
}
