package dev.arrokoth.phicreator.phi.level;

import dev.arrokoth.phicreator.phi.chart.Chart;
import dev.arrokoth.phicreator.phi.chart.Line;
import dev.arrokoth.phicreator.phi.chart.Note;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Arrokoth
 * @project PhiCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class Level {
    private final Map<Long, List<Line>> lines;
    private final Map<Long, List<Note>> notes;
    private final Chart chart;

    public Level(Chart chart) {
        this.lines = new HashMap<>();
        this.notes = new HashMap<>();
        this.chart = chart;

        for (Note note : chart.notes) {
            List<Note> notes1 = new ArrayList<>();
            if (this.notes.get(note.time) != null) {
                notes1 = this.notes.get(note.time);
            }
            notes1.add(note);
            this.notes.put(note.time, notes1);
        }
    }

    public Image getIllustration() {
        return chart.illustration;
    }

    public List<Note> getNoteOnTime(long time) {
        List<Note> out = new ArrayList<>();
        for (List<Note> value : this.notes.values()) {
//            System.out.println(value);
            for (Note note : value) {
                out.add(note);
            }
        }
        return out;
    }
}
