package ru.vsu.cs;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.TextEvent;
import java.util.Arrays;

public class GUI extends JFrame {
    private JButton inputButton;
    private JPanel pane;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JButton saveAsButton;
    private JTextField outputFileField;
    private JTextField errorField;

    public GUI() {
        super("GUI");
        setContentPane(pane);
        setVisible(true);
        //outputFileField.setVisible(false);
        //errorField.setVisible(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);


        inputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textArea1.getText().contains(".")) {
                    try {
                        int[][] intArr = FileIO.readIntMatrixFromFile(textArea1.getText());
                        int[][] outputData = Evaluate.getFriendlyCount(intArr);
                        for (int i = 0; i < outputData.length; i++) {
                            textArea2.append(Arrays.toString(outputData[i]).replace("[", "").replace("]", "").replace(",", "") + "\n");
                        }
                    } catch (Exception ex) {
                        errorField.setVisible(true);
                        errorField.setText("Ошибка при чтении файла");
                    }

                } else {
                    String data[] = textArea1.getText().split("\n");
                    int[][] intArr = ArrayConverter.convertToIntArray(data);
                    int[][] outputData = Evaluate.getFriendlyCount(intArr);
                    for (int i = 0; i < outputData.length; i++) {
                        textArea2.append((Arrays.toString(outputData[i]).replace("[", "").replace("]", "").replace(",", "") + "\n"));
                    }
                    inputButton.setEnabled(false);
                }


            }
        });
        saveAsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputFileField.setVisible(true);
                if (textArea2.getText() != "") {
                    System.out.println(textArea2.getText());
                    try {
                        FileIO.writeStringToFile(outputFileField.getText(), textArea2.getText());
                    } catch (Exception ex) {
                        errorField.setVisible(true);
                        errorField.setText("Ошибка при записи файла");
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        GUI v = new GUI();
        v.setTitle("Дружелюбная программа");
    }
}
