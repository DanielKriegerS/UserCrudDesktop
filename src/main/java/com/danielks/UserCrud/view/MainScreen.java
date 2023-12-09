package com.danielks.UserCrud.view;

import com.danielks.UserCrud.entity.User;
import com.danielks.UserCrud.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class MainScreen extends JFrame {

    private final UserService userService;
    public MainScreen(UserService userService) {
        this.userService = userService;
        setTitle("Users List");
        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        List<String[]> tasks = Arrays.asList(
                new String[]{"1", "Task 1", "Description 1", "2023-01-01", "Pending"},
                new String[]{"2", "Task 2", "Description 2", "2023-01-05", "In Progress"},
                new String[]{"3", "Task 3", "Description 3", "2023-01-10", "Completed"}
        );

        String[] columns = {"ID", "Title", "Description", "Date Registered", "Situation"};
        Object[][] data = convertToObjects(tasks);

        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel nameLabel = new JLabel("NOME");
        constraints.gridx = 1;
        constraints.gridy = 0;
        inputPanel.add(nameLabel, constraints);

        JTextField nameField = new JTextField(20);
        constraints.gridy = 1;
        inputPanel.add(nameField, constraints);

        JLabel phoneLabel = new JLabel("TELEFONE");
        constraints.gridx = 2;
        constraints.gridy = 0;
        inputPanel.add(phoneLabel, constraints);

        JTextField phoneField = new JTextField(20);
        constraints.gridy = 1;
        inputPanel.add(phoneField, constraints);

        JButton inserirButton = new JButton("Insert");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        inputPanel.add(inserirButton, constraints);

        inserirButton.addActionListener(e -> {
            String name = nameField.getText();
            String phone = phoneField.getText();

            User newUser = new User();
            newUser.setNome(name);
            newUser.setTelefone(phone);

            userService.save(newUser);
        });

        panel.add(inputPanel, BorderLayout.SOUTH);

        getContentPane().add(panel);
        setVisible(true);

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String[] userDetails = (String[]) data[selectedRow];
                    dispose();
                    new UserInfo(userDetails, userService);
                }
            }
        });
    }

    private Object[][] convertToObjects(List<String[]> tasks) {
        Object[][] data = new Object[tasks.size()][];
        for (int i = 0; i < tasks.size(); i++) {
            data[i] = tasks.get(i);
        }
        return data;
    }

}