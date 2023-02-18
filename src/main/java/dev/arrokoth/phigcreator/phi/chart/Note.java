package dev.arrokoth.phigcreator.phi.chart;

/**
 * @author Arrokoth
 * @project PhigCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class Note {
    public final int id;
    public final long visibleTime;
    public final long time;

    public Note(int id, long visibleTime, long time) {
        this.id = id;
        this.visibleTime = visibleTime;
        this.time = time;
    }
}
