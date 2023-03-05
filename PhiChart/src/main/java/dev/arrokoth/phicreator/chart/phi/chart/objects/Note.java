package dev.arrokoth.phicreator.chart.phi.chart.objects;

/**
 * @author Arrokoth
 * @project PhiCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class Note {
    protected final int type;
    protected final double time;
    protected final double positionX;
    protected final double holdTime;
    protected final double speed;
    protected final double floorPosition;

    // TODO: 2023/2/27 Note 
    public Note(int type, double time, double positionX, double holdTime, double speed, double floorPosition) {
        this.type = type;
        this.time = time;
        this.positionX = positionX;
        this.holdTime = holdTime;
        this.speed = speed;
        this.floorPosition = floorPosition;
    }
}
