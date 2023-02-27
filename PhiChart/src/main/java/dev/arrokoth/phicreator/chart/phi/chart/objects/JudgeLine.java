package dev.arrokoth.phicreator.chart.phi.chart.objects;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Arrokoth
 * @project PhiCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class JudgeLine {
    // TODO: 2023/2/27 Debug
    protected final List<LineEvent> events;
    protected final List<Note> notes;

    public JudgeLine() {
        this.events = new ArrayList<>();
        this.notes = new ArrayList<>();
    }
}
