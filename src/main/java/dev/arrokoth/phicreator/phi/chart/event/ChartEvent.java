package dev.arrokoth.phicreator.phi.chart.event;

import dev.arrokoth.phicreator.phi.level.Level;

/**
 * @author Arrokoth
 * @project PhiCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public abstract class ChartEvent {
    public abstract void handle(Level level);
}
