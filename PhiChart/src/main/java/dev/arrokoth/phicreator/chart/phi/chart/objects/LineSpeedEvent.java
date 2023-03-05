package dev.arrokoth.phicreator.chart.phi.chart.objects;

/**
 * @author Arrokoth
 * @project PhiCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class LineSpeedEvent implements LineEvent {
    public final double startTime;
    public final double endTime;
    public final double floorPosition;
    public final double value;

    public LineSpeedEvent(double startTime, double endTime, double floorPosition, double value) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.floorPosition = floorPosition;
        this.value = value;
    }


    // TODO: 2023/2/27 Handle event 
    @Override
    public void handle(JudgeLine line) {
    }
}
