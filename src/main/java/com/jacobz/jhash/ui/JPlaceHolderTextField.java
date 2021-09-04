package com.jacobz.jhash.ui;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Setter
@Getter
public class JPlaceHolderTextField extends JTextField {

    private String placeHolder;
    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

        if (getText().isEmpty() && !(FocusManager.getCurrentKeyboardFocusManager().getFocusOwner() == this)) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(Color.GRAY);
            g2.setFont(getFont().deriveFont(Font.ITALIC));
            g2.drawString(placeHolder, 5, 20);
            g2.dispose();
        }

    }
}
