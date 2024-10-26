
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.FoodDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import model.ChooseFood;

public class MenuRecorderFrame extends JFrame {

    private JPanel contentPane;
    private DefaultTableModel model;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        MenuRecorderFrame frame = new MenuRecorderFrame();
        frame.setVisible(true);
    }

    /**
     * Create the frame.
     */
    public MenuRecorderFrame() {
        setTitle("Menu Recorder");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 520, 446);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        MenuRecorderFrame frame = this;

        model = new DefaultTableModel(new Object[][]{}, new String[]{"Name", "Date"});

        table = new JTable();
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(15, 28, 488, 350);
        contentPane.add(scrollPane);

        // refresh the list
        refreshList("");

        //button Return
        JButton btnReturn = new JButton("Return");
        btnReturn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the current page
                dispose();
                // Open the main interface
                new mainInterface().setVisible(true);
            }
        });
        btnReturn.setBounds(410, 387, 80, 28);
        contentPane.add(btnReturn);
    }

    // refresh the list
    public void refreshList(String condition) {
        // clear the information in the list
        model.setRowCount(0);

        //call the method getAllChooseFood to get all the record from the database choosefood and store in the list
        List<ChooseFood> chooseFoods = FoodDao.getAllChooseFood();

        for (ChooseFood food : chooseFoods) {

            // add the menu record into the table
            model.insertRow(table.getRowCount(), new Object[]{food.getName(), food.getTime()});

        }
    }

}
