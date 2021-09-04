package com.jacobz.jhash;

import com.jacobz.jhash.ui.MainFrame;

import javax.swing.*;
import java.awt.*;

public class JhashApp {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MainFrame mf = new MainFrame();
                SwingUtilities.updateComponentTreeUI(mf);
                mf.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
}
