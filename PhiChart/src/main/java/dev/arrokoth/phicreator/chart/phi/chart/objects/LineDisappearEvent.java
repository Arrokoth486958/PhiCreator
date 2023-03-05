package dev.arrokoth.phicreator.chart.phi.chart.objects;

/**
 * @author Arrokoth
 * @project PhiCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class LineDisappearEvent implements LineEvent {
    public final double startTime;
    public final double endTime;
    public final double start;
    public final double end;
    public final double start2;
    public final double end2;

    public LineDisappearEvent(double startTime, double endTime, double start, double end, double start2, double end2) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.start = start;
        this.end = end;
        this.start2 = start2;
        this.end2 = end2;
    }

    // TODO: 2023/2/27 Handle event 
    @Override
    public void handle(JudgeLine line) {
    }
}
