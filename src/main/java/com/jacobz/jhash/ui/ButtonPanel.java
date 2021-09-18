package com.jacobz.jhash.ui;

import com.jacobz.jhash.config.SizeConst;
import com.jacobz.jhash.service.HashService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

@Slf4j
public class ButtonPanel extends JPanel {
    private final MainFrame parent;
    private final MainContentPanel contentPanel;
    private final HashService hashService = new HashService();
    private final JButton btnCalc;
    private final JButton btnClose;
    private final JProgressBar progressBar;
    private ResourceBundle bundle;

    public ButtonPanel(MainFrame parent, MainContentPanel contentPanel,ResourceBundle bundle) {
        this.parent = parent;
        this.contentPanel = contentPanel;
        this.bundle = bundle;
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        progressBar = new JProgressBar();
        progressBar.setPreferredSize(new Dimension(580, 20));
        progressBar.setVisible(false);
        btnCalc = new JButton("计算Hash");
        btnCalc.setPreferredSize(SizeConst.BUTTON_SIZE);
        btnCalc.addActionListener(e -> {
            if (StringUtils.isEmpty(parent.getFilePath())) {
                UIManager.put("OptionPane.okButtonText", ButtonPanel.this.bundle.getString("OK"));
                JOptionPane.showMessageDialog(parent, ButtonPanel.this.bundle.getString("CHOOSE_FILE_PATH"), ButtonPanel.this.bundle.getString("ERROR"), JOptionPane.ERROR_MESSAGE);
                return;
            }
            log.info("hashing...");
            Set<String> types = contentPanel.getProcessTypes();
            Thread t = new CalcThread(types);
            progressBar.setVisible(true);
            progressBar.setIndeterminate(true);
            t.start();

        });
        btnClose = new JButton("关闭");
        btnClose.setPreferredSize(SizeConst.BUTTON_SIZE);
        btnClose.addActionListener(e -> parent.dispose());

        this.add(progressBar);
        this.add(btnCalc);
        this.add(btnClose);
        this.setBorder(new EmptyBorder(10, 0, 0, 0));
    }

    @AllArgsConstructor
    private class CalcThread extends Thread {
        private Set<String> processTypes;

        @Override
        public void run() {
            btnCalc.setEnabled(false);
            Map<String, String> rst;
            try {
                rst = hashService.calcHashValue(parent.getFilePath(), processTypes);

                contentPanel.updateData(rst);
            } catch (IOException e) {
                UIManager.put("OptionPane.okButtonText", ButtonPanel.this.bundle.getString("OK"));
                JOptionPane.showMessageDialog(parent, e.getMessage(), ButtonPanel.this.bundle.getString("ERROR"), JOptionPane.ERROR_MESSAGE);
                log.error(e.getMessage());
            } finally {
                btnCalc.setEnabled(true);
                progressBar.setIndeterminate(false);
                progressBar.setVisible(false);
            }
        }
    }

    public JButton getBtnCalc() {
        return btnCalc;
    }

    public JButton getBtnClose() {
        return btnClose;
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }
}
