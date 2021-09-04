package com.jacobz.jhash.ui;

import com.jacobz.jhash.config.LabelConst;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Setter
@Getter
public class MainContentPanel extends JPanel {

    private HashResultPanel[] panels;
    private Set<String> processTypes;
    public MainContentPanel() {
        processTypes = new HashSet<>();
        panels = new HashResultPanel[6];
        this.setLayout(new GridLayout(panels.length, 1, 10, 10));
        for (int i = 0; i < panels.length; i++) {
            if (i == 0 || i == 1) {
                panels[i] = new HashResultPanel(LabelConst.labelMap.get(i), true, processTypes);
                processTypes.add(panels[i].getName());
            } else {
                panels[i] = new HashResultPanel(LabelConst.labelMap.get(i), false, processTypes);
            }
            this.add(panels[i]);
        }
    }


    public void updateData(Map<String, String> rst) {
        Set<String> keys = rst.keySet();
        for (HashResultPanel panel : panels) {
            panel.setValue("");
            for (String key : keys) {
                if (panel.getName().equalsIgnoreCase(key)) {
                    String val = rst.get(key);
                    panel.setValue(val);
                }
            }
        }
    }
}
