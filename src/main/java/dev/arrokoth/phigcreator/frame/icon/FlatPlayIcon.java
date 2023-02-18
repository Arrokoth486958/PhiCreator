package dev.arrokoth.phigcreator.frame.icon;

import com.formdev.flatlaf.icons.FlatAbstractIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;

import javax.swing.*;
import java.awt.*;

/**
 * @author Arrokoth
 * @project PhigCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class FlatPlayIcon
    extends FlatAbstractIcon {
    public FlatPlayIcon() {
        super( 16, 16, UIManager.getColor( "Objects.Green" ) );
    }

    @Override
    protected void paintIcon( Component c, Graphics2D g ) {
        g.fill(FlatUIUtils.createPath(
                2, 2,
                14, 8,
                2, 14
        ));
    }
}
