package com.danielks.UserCrud.view;

import com.danielks.UserCrud.Defins;
import com.danielks.UserCrud.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInfo extends JFrame {

    private final UserService userService;
    public UserInfo(String[] taskDetails, UserService userService) {
        this.userService = userService;
        setTitle("Task Details");
        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        String[][] rowData = {
                {"ID", taskDetails[0]},
                {"Title", taskDetails[1]},
                {"Description", taskDetails[2]},
                {"Date Registered", taskDetails[3]},
                {"Situation", taskDetails[4]}
        };

        String[] columnNames = {"Detail", "Value"};

        JTable table = new JTable(rowData, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(350, 200));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridBagLayout());

        Defins constraints = new Defins(0, 0).setPadding(5);

        JButton editButton = new JButton("Edit");
        buttonPanel.add(editButton, constraints);

        constraints.gridx = 1;
        JButton deleteButton = new JButton("Delete");
        buttonPanel.add(deleteButton, constraints);

        constraints.gridx = 2;
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainScreen(userService);
            }
        });
        buttonPanel.add(backButton, constraints);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(panel);
        setVisible(true);
    }
}