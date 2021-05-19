package com.robots.gui.panels;

import com.robots.gui.windows.MainWindow;

import javax.swing.*;
import java.util.List;

public class choseAlgorithms{
    public static List<String> choseLogic(MainWindow frame){
        JList<String> list = new JList<>(new String[] {"BFS", "DFS"});
        list.setSelectionMode(
                ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        int[] select = {0, 1};
        list.setSelectedIndices(select);
        JOptionPane.showMessageDialog(frame, list, "Select algorithms", JOptionPane.PLAIN_MESSAGE);
        return list.getSelectedValuesList();
    }
}
