package MVC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {
    private FunctionTableModel tableModel;
    private JTable table;
    private GraphPanel graphPanel;

    public MainFrame() {
        setTitle("График и таблица функции (MVC)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tableModel = new FunctionTableModel();

        table = new JTable(tableModel);
        table.setRowHeight(25);
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setPreferredSize(new Dimension(300, 200));

        graphPanel = new GraphPanel(tableModel);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Добавить");
        JButton deleteButton = new JButton("Удалить");

        addButton.addActionListener((ActionEvent e) -> {
            tableModel.addPoint(0.0); // добавляем точку с x=0
        });

        deleteButton.addActionListener((ActionEvent e) -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                tableModel.removePoint(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Выберите строку для удаления");
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(tableScroll, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.WEST);
        add(graphPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
