package com.jacobz.jhash.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;

import com.jacobz.jhash.util.ResourceManager;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class MainFrame extends JFrame {

    private String filePath;
    private MainContentPanel panelMain;
    private HeaderPanel panelNorth;
    private ButtonPanel panelSouth;
    ResourceBundle rBundle;

    public MainFrame() {
        rBundle = ResourceManager.getBundle(Locale.US);
        initMenu();
        initControls();
        initControlsI18N();
        // Font font = new Font("Sans", Font.PLAIN, 12);
        // initGlobalFont(font);

        this.setTitle("JFileHashCalculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        setSize(800, 400);
        setLocationRelativeTo(null);
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        this.repaint();
        this.pack();

    }

    private void initMenu() {
        JMenuBar bar = new JMenuBar();
        JMenu fileMenu = new JMenu();
        JMenu langMenu = new JMenu();

        JRadioButtonMenuItem itemEN = new JRadioButtonMenuItem("English");
        itemEN.setSelected(true);
        itemEN.addActionListener(e -> {
            if (itemEN.isSelected()) {
                rBundle = ResourceManager.getBundle(Locale.US);
                initMenuI18N(fileMenu, langMenu);
                initControlsI18N();
            }
        });
        JRadioButtonMenuItem itemHans = new JRadioButtonMenuItem("简体中文");
        itemHans.addActionListener(e -> {
            if (itemHans.isSelected()) {
                rBundle = ResourceManager.getBundle(Locale.CHINA);
                initMenuI18N(fileMenu, langMenu);
                initControlsI18N();
            }
        });
        JRadioButtonMenuItem itemHant = new JRadioButtonMenuItem("正體中文");
        itemHant.addActionListener(e -> {
            if (itemHant.isSelected()) {
                rBundle = ResourceManager.getBundle(Locale.TAIWAN);
                initMenuI18N(fileMenu, langMenu);
                initControlsI18N();
            }
        });
        ButtonGroup bg = new ButtonGroup();
        bg.add(itemEN);
        bg.add(itemHans);
        bg.add(itemHant);
        langMenu.add(itemEN);
        langMenu.add(itemHans);
        langMenu.add(itemHant);
        fileMenu.add(langMenu);
        bar.add(fileMenu);
        initMenuI18N(fileMenu, langMenu);
        this.setJMenuBar(bar);
    }

    private void initMenuI18N(JMenu fileMenu, JMenu langMenu) {
        fileMenu.setText(rBundle.getString("FILE"));
        langMenu.setText(rBundle.getString("LANGUAGE"));
    }

    private void initControls() {
        // north
        panelNorth = new HeaderPanel(this);

        // north end
        // main
        panelMain = new MainContentPanel();
        // main end

        // south
        panelSouth = new ButtonPanel(this, panelMain, rBundle);
        // south end

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 15));
        contentPane.add(panelNorth, BorderLayout.NORTH);
        contentPane.add(panelMain, BorderLayout.CENTER);
        contentPane.add(panelSouth, BorderLayout.SOUTH);
        this.add(contentPane);
    }

    private void initControlsI18N() {
        panelNorth.getTextFile().setPlaceHolder(rBundle.getString("CHOOSE_FILE"));
        JComboBox<String> comboBox = panelNorth.getHashTypes();
        comboBox.removeAllItems();
        comboBox.addItem(rBundle.getString("FILE"));
        JButton btnCalc = panelSouth.getBtnCalc();
        btnCalc.setText(rBundle.getString("CALC"));

        panelSouth.setBundle(rBundle);
        panelSouth.getBtnClose().setText(rBundle.getString("CLOSE"));
    }
}
