package ru.vsu.cs;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class GUI extends JFrame {
    private JButton inputButton;
    private JPanel pane;
    private JTextArea textArea1;
    private JTextArea textArea2;

    public GUI() {
        super("GUI");
        setContentPane(pane);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 1000);

        inputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String data[] = textArea1.getText().split("\n");
                int[][] intArr = ArrayConverter.convertToIntArray(data);
                int[][] outputData = Evaluate.getFriendlyCount(intArr);
                for (int i = 0; i < outputData.length; i++) {
                    textArea2.append(Arrays.toString(outputData[i]).replace("[", "").replace("]", "").replace(",", "") + "\n");
                }

            }
        });
    }

    public static void main(String[] args) {
        GUI v = new GUI();
        v.setSize(400, 400);
        v.setTitle("Дружелюбная программа");
    }
}
