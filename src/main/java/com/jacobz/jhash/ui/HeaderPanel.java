package com.jacobz.jhash.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.jacobz.jhash.config.SizeConst;

import lombok.Getter;

@Getter
public class HeaderPanel extends JPanel {
    JComboBox<String> hashTypes;
    JPlaceHolderTextField textFile;
    public HeaderPanel(MainFrame parent) {

        hashTypes = new JComboBox<>();
//        hashTypes.addItem("文本");
        hashTypes.setPreferredSize(new Dimension(SizeConst.LEFT_CONTROL_PREFERRED_WIDTH - 5, SizeConst.CONTROL_PREFERRED_HEIGHT));
        textFile = new JPlaceHolderTextField();
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
