package com.jacobz.jhash.ui;

import com.jacobz.jhash.util.FontUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.InputStream;
import java.util.Enumeration;

@Slf4j
@Getter
@Setter
public class MainFrame extends JFrame {

    private String filePath;
    private MainContentPanel panelMain;

    public MainFrame() {

        initControls();
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//            InputStream is = this.getClass().getResourceAsStream("/wqy.ttc");
//            font = FontUtil.loadFont(is);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        Font font= new Font("Sans", Font.PLAIN, 12);
        initGlobalFont(font);

        this.setTitle("JFileHashCalculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        setSize(800, 400);
        setLocationRelativeTo(null);
        this.pack();

    }

    private void initControls() {
        // north
        HeaderPanel panelNorth = new HeaderPanel(this);
        // north end

        // main
        panelMain = new MainContentPanel();
        // main end

        // south
        ButtonPanel panelSouth = new ButtonPanel(this, panelMain);
        // south end
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 15));
        contentPane.add(panelNorth, BorderLayout.NORTH);
        contentPane.add(panelMain, BorderLayout.CENTER);
        contentPane.add(panelSouth, BorderLayout.SOUTH);
        this.add(contentPane);
    }

    private void initGlobalFont(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
    }
}
