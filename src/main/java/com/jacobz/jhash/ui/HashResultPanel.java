package com.jacobz.jhash.ui;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

import static com.jacobz.jhash.config.SizeConst.*;

@Getter
@Setter
public class HashResultPanel extends JPanel {
    private final JTextField textField;
    private final JCheckBox checkBox;
    private String value;
    public HashResultPanel(String labelStr, boolean activated, Set<String> processTypes) {
        checkBox = new JCheckBox(labelStr);
        checkBox.setPreferredSize(new Dimension(LEFT_CONTROL_PREFERRED_WIDTH, CONTROL_PREFERRED_HEIGHT));

        checkBox.setSelected(activated);

        checkBox.addChangeListener(e -> {
            JCheckBox chk = (JCheckBox) e.getSource();

            HashResultPanel.this.setActivated(chk.isSelected());
            if (chk.isSelected()) {
                processTypes.add(chk.getText());
            } else {
                processTypes.remove(chk.getText());
            }
        });
        textField = new JTextField();
        textField.setEditable(false);
        this.setLayout(new BorderLayout());
        this.add(checkBox, BorderLayout.WEST);
        this.add(textField);
        this.setPreferredSize(new Dimension(GLOBAL_PREFERRED_WIDTH, CONTROL_PREFERRED_HEIGHT));
    }

    @Deprecated
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        if (textField != null)
            textField.setText(value);
        this.value = value;
    }

    @Deprecated
    public boolean getActivated() {
        return checkBox.isSelected();
    }

    public void setActivated(boolean status) {
    }

    public String getName() {
        return this.checkBox.getText();
    }
}
