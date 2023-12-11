package com.danielks.UserCrud.view;

import com.danielks.UserCrud.Defins;
import com.danielks.UserCrud.entity.User;
import com.danielks.UserCrud.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInfo extends JFrame {

    private final UserService userService;

    public UserInfo(User user, UserService userService) {
        this.userService = userService;
        setTitle("User Details");
        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        String[][] rowData = {
                {"ID", user.getId().toString()},
                {"Name", user.getName()},
                {"Phone", user.getPhone()}
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
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newName = JOptionPane.showInputDialog(null, "Enter new name:", user.getName());
                String newPhone = JOptionPane.showInputDialog(null, "Enter new phone:", user.getPhone());

                if (newName != null && newPhone != null) {
                    user.setName(newName);
                    user.setPhone(newPhone);

                    userService.update(user); // Atualiza o usuário no banco de dados

                    // Atualiza os dados exibidos na tabela da UserInfo
                    rowData[1][1] = user.getName();
                    rowData[2][1] = user.getPhone();

                    JOptionPane.showMessageDialog(null, "User updated successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter valid information.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPanel.add(editButton, constraints);

        constraints.gridx = 1;
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this user?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean deleted = userService.deleteUser(user.getId());
                    if (deleted) {
                        dispose();
                        JOptionPane.showMessageDialog(null, "User deleted successfully.");
                        new MainScreen(userService);
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to delete user.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
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