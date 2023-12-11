package com.danielks.UserCrud.view;

import com.danielks.UserCrud.entity.User;
import com.danielks.UserCrud.service.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainScreen extends JFrame {

    private final UserService userService;
    private Object[][] data;
    private JTable table;
    private String[] columns = {"ID", "Nome", "Telefone"};
    public MainScreen(UserService userService) {
        this.userService = userService;
        setTitle("Users List");
        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        refreshTableData();

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
            newUser.setName(name);
            newUser.setPhone(phone);

            userService.save(newUser);
            refreshTableData();

            table.setModel(new DefaultTableModel(data, columns));
        });

        panel.add(inputPanel, BorderLayout.SOUTH);

        getContentPane().add(panel);
        setVisible(true);

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    User selectedUser = getUserFromRow(selectedRow);
                    dispose();
                    new UserInfo(selectedUser, userService);
                }
            }
        });
    }
    private void refreshTableData() {
        List<User> userList = userService.getAllUsers();
        data = convertToObjects(userList);
     }

    private Object[][] convertToObjects(List<User> userList) {
        Object[][] newData = new Object[userList.size()][5];
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            newData[i][0] = user.getId();
            newData[i][1] = user.getName();
            newData[i][2] = user.getPhone();
        }
        return newData;
    }

    private User getUserFromRow(int row) {
        long userId = (long) data[row][0];
        return userService.getUserById(userId);
    }
}