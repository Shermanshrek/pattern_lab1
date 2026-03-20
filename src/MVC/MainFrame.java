package MVC;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {
    private final FunctionTableModel tableModel;
    private final JTable table;

    public MainFrame() {
        setTitle("График и таблица функции (MVC)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tableModel = new FunctionTableModel("y = x²");

        table = new JTable(tableModel);
        table.setRowHeight(25);

        class NumberFilter extends DocumentFilter {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string != null && string.matches("[0-9.-]*")) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text != null && text.matches("[0-9.-]*")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        }

        JTextField textField = new JTextField();
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new NumberFilter());
        DefaultCellEditor numberEditor = new DefaultCellEditor(textField);
        table.getColumnModel().getColumn(0).setCellEditor(numberEditor);

        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setPreferredSize(new Dimension(300, 200));

        ChartPanelView chartPanel = new ChartPanelView(tableModel);
        chartPanel.setPreferredSize(new Dimension(500, 400));

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
        add(chartPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
