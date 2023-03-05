package dev.arrokoth.phicreator.chart.phi.chart.objects;

import java.util.Map;

/**
 * @author Arrokoth
 * @project PhiCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class JudgeLine {
    // TODO: 2023/2/27 Debug
    public final int bpm;
    public final int numOfNotesAbove;
    public final int numOfNotesBelow;
    public final Map<Double, Note> notesAbove;
    public final Map<Double, Note> notesBelow;
    public final Map<Double, LineSpeedEvent> speedEvents;
    public final Map<Double, LineMoveEvent> moveEvents;
    public final Map<Double, LineRotateEvent> rotateEvents;
    public final Map<Double, LineDisappearEvent> disappearEvents;

    public JudgeLine(int bpm, int numOfNotesAbove, int numOfNotesBelow, Map<Double, Note> notesAbove, Map<Double, Note> notesBelow, Map<Double, LineSpeedEvent> speedEvents, Map<Double, LineRotateEvent> rotateEvents, Map<Double, LineMoveEvent> moveEvents, Map<Double, LineDisappearEvent> disappearEventLine) {
        this.bpm = bpm;
        this.numOfNotesAbove = numOfNotesAbove;
        this.numOfNotesBelow = numOfNotesBelow;
        this.notesAbove = notesAbove;
        this.notesBelow = notesBelow;
        this.speedEvents = speedEvents;
        this.rotateEvents = rotateEvents;
        this.moveEvents = moveEvents;
        this.disappearEvents = disappearEventLine;
    }

    @Override
    public String toString() {
        return "JudgeLine{" +
                "bpm=" + bpm +
                ", numOfNotesAbove=" + numOfNotesAbove +
                ", numOfNotesBelow=" + numOfNotesBelow +
                ", notesAbove=" + notesAbove +
                ", notesBelow=" + notesBelow +
                ", speedEvents=" + speedEvents +
                ", rotateEvents=" + rotateEvents +
                ", disappearEvents=" + disappearEvents +
                '}';
    }
}
