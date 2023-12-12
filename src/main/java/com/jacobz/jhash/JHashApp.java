package com.jacobz.jhash;

import com.jacobz.jhash.ui.MainFrame;

import java.awt.*;

public class JHashApp {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MainFrame mf = new MainFrame();
                mf.setVisible(true);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }
}
