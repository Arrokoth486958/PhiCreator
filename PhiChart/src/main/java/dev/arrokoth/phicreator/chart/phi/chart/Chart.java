package dev.arrokoth.phicreator.chart.phi.chart;

import dev.arrokoth.phicreator.chart.phi.chart.objects.JudgeLine;

import java.net.URL;
import java.util.List;

/**
 * @author Arrokoth
 * @project PhiCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class Chart {
    protected final int formatVersion;
    protected final int numOfNotes;
    protected final double offset;
    protected final List<JudgeLine> lines;
    protected final URL illustration;
    protected final URL music;

    public Chart(int formatVersion, int numOfNotes, double offset, List<JudgeLine> lines, URL illustration, URL music) {
        this.formatVersion = formatVersion;
        this.numOfNotes = numOfNotes;
        this.offset = offset;
        this.lines = lines;
        this.illustration = illustration;
        this.music = music;
    }

    public List<JudgeLine> getLines() {
        return lines;
    }

    public URL getIllustration() {
        return illustration;
    }

    public URL getMusic() {
        return music;
    }

    @Override
    public String toString() {
        return "Chart{" +
                "formatVersion=" + formatVersion +
                ", numOfNotes=" + numOfNotes +
                ", offset=" + offset +
                ", lines=" + lines +
                '}';
    }
}
