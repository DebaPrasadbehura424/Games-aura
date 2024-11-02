import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Leader extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;

    public Leader() {
        setTitle("Leader");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(new String[]{"Rank", "Name", "Score"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };

        table = new JTable(tableModel);

        // Set custom renderer to remove grid lines and add colors
        table.setShowGrid(false);
        table.setFillsViewportHeight(true);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setBackground(Color.LIGHT_GRAY);
        renderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(renderer); // Row numbers
        table.getColumnModel().getColumn(1).setCellRenderer(renderer); // Names
        table.getColumnModel().getColumn(2).setCellRenderer(renderer); // Scores

        // Add the table to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    public void updateLeaderBoard() {
        Link li = new Link();
        try {
            String all = "SELECT score, name FROM officebar ORDER BY score DESC LIMIT 10";
            ResultSet rs = li.statement.executeQuery(all);

            tableModel.setRowCount(0);

            int rowNumber = 1;
            // Fetch data and add it to the table model
            while (rs.next()) {
                String score = rs.getString("score");
                String name = rs.getString("name");
                tableModel.addRow(new Object[]{rowNumber++, name, score});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Leader leaderFrame = new Leader();
        leaderFrame.updateLeaderBoard(); // Call the method to update the leaderboard
    }
}
