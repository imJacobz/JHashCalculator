package com.jacobz.jhash.ui;

import com.jacobz.jhash.config.SizeConst;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.File;

@Slf4j
public class HeaderPanel extends JPanel {
    public HeaderPanel(MainFrame parent) {

        JComboBox<String> hashTypes = new JComboBox<>();
        hashTypes.addItem("文件");
//        hashTypes.addItem("文本");
        hashTypes.setPreferredSize(new Dimension(SizeConst.LEFT_CONTROL_PREFERRED_WIDTH - 5, SizeConst.CONTROL_PREFERRED_HEIGHT));
        JPlaceHolderTextField textFile = new JPlaceHolderTextField();
        textFile.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changeVal();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changeVal();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changeVal();
            }

            private void changeVal() {
                parent.setFilePath(textFile.getText());
            }
        });
        textFile.setPlaceHolder("选择文件");
        JButton btnChooseFile = new JButton("...");
        btnChooseFile.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if (jfc.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
                File file = jfc.getSelectedFile();
                parent.setFilePath(file.getAbsolutePath());
                textFile.setText(file.getAbsolutePath());
            }
        });
        this.setLayout(new BorderLayout(5, 5));
        this.setBorder(new EmptyBorder(0, 0, 10, 0));
        this.setPreferredSize(new Dimension(SizeConst.GLOBAL_PREFERRED_WIDTH, SizeConst.HEADER_PREFERRED_HEIGHT));
        this.add(hashTypes, BorderLayout.WEST);
        this.add(btnChooseFile, BorderLayout.EAST);
        this.add(textFile);
    }
}
