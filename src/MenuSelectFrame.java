
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dao.FoodDao;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class MenuSelectFrame extends JFrame {

    private JPanel contentPane;
    private JTextField dishNumberTxt;
    private JTextField repeatTimeTxt;
    private JList list;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        MenuSelectFrame frame = new MenuSelectFrame();
        frame.setVisible(true);
    }

    /**
     * Create the frame.
     */
    public MenuSelectFrame() {
        //main frame
        setTitle("Menu Selector"); //set the title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close & stop the program
        setBounds(100, 100, 675, 592); //set the size of the frame
        contentPane = new JPanel(); //add panel
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); //set the size of the panel
        setContentPane(contentPane);
        contentPane.setLayout(null);

        MenuSelectFrame frame = this;

        JPanel panel = new JPanel();
        panel.setBounds(14, 13, 629, 52);
        contentPane.add(panel);
        panel.setLayout(null);

        //menu selector button
        JLabel lblNewLabel = new JLabel("Menu Selector");
        lblNewLabel.setFont(new Font("Cambria", Font.BOLD, 20));
        lblNewLabel.setBounds(252, 13, 172, 26);
        panel.add(lblNewLabel);

        //label: number of dishes
        JLabel lblNumberOfDishes = new JLabel("Number of Dishes");
        lblNumberOfDishes.setFont(new Font("Cambria", Font.BOLD, 15));
        lblNumberOfDishes.setBounds(14, 100, 178, 18);
        contentPane.add(lblNumberOfDishes);

        //textfield: enter the number of dishes
        dishNumberTxt = new JTextField();
        dishNumberTxt.setText("1");
        dishNumberTxt.setBounds(195, 100, 86, 24);
        contentPane.add(dishNumberTxt);
        dishNumberTxt.setColumns(10);

        //lable: non repeat times
        JLabel lblNonRepeatTimes = new JLabel("Non repeat times");
        lblNonRepeatTimes.setFont(new Font("Cambria", Font.BOLD, 15));
        lblNonRepeatTimes.setBounds(14, 160, 178, 18);
        contentPane.add(lblNonRepeatTimes);

        //textfield: non repeat times
        repeatTimeTxt = new JTextField();
        repeatTimeTxt.setText("1");
        repeatTimeTxt.setColumns(10);
        repeatTimeTxt.setBounds(195, 160, 86, 24);
        contentPane.add(repeatTimeTxt);

        //label: materials wanted
        JLabel lblMaterialsWanted = new JLabel("Materials wanted");
        lblMaterialsWanted.setFont(new Font("Cambria", Font.BOLD, 15));
        lblMaterialsWanted.setBounds(14, 220, 178, 18);
        contentPane.add(lblMaterialsWanted);

        //list: material wanted
        //call the method: get the list of materials from the database
        Object[] item = FoodDao.getMaterials().toArray(); 
        list = new JList(item);
        //allows multiple choice
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        contentPane.add(list);

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(14, 250, 267, 257);
        contentPane.add(scrollPane);

        //label: materials avoided
        JLabel lblMaterialsAvoided = new JLabel("Materials avoided");
        lblMaterialsAvoided.setFont(new Font("Cambria", Font.BOLD, 15));
        lblMaterialsAvoided.setBounds(360, 100, 178, 18);
        contentPane.add(lblMaterialsAvoided);

        //list: material avoided
        JList list_1 = new JList(item);
        list_1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JScrollPane scrollPane_1 = new JScrollPane(list_1);
        scrollPane_1.setBounds(360, 130, 283, 257);
        contentPane.add(scrollPane_1);

        
        JButton btnNewButton = new JButton("Generate Menu");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int dishNumber = 0;
                int repeatTime = 0;

                // Check that the dishNumber is entered correctly
                try {
                    dishNumber = Integer.valueOf(dishNumberTxt.getText());
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(frame, "Please enter correct number of dishes");
                    return;
                }

                // Check that the repeatTime is entered correctly
                try {
                    repeatTime = Integer.valueOf(repeatTimeTxt.getText());
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(frame, "Please enter correct non repeat times");
                    return;
                }

                // get materials wanted 
                //get the index of the list's selected item
                int[] indices = list.getSelectedIndices();
                //get the collection of all items in the list
                ListModel<String> listModel = list.getModel();
                
                List<String> wantedList = new ArrayList<String>();
                //Iterate over the index of the selected item
                for (int index : indices) {
                    wantedList.add(listModel.getElementAt(index));
                }

                //get materials avoided
                //get the index of the list's selected item
                int[] avoided_indices = list_1.getSelectedIndices();
                //get the collection of all items in the list
                ListModel<String> avoided_listModel = list_1.getModel();
                
                List<String> avoidedList = new ArrayList<String>();
                //Iterate over the index of the selected item
                for (int index : avoided_indices) {
                    
                    if (wantedList.contains(avoided_listModel.getElementAt(index))) {
                        //error message if same material is selected in material wanted and material avoided
                        JOptionPane.showMessageDialog(frame, "Same material can't be selected in both material "
                                + "wanted and material avoided!");
                        return;
                    } else {
                        //get the materials avoided 
                        avoidedList.add(avoided_listModel.getElementAt(index));
                    }
                }

                // Close the current window
                dispose();

                // Open the today menu window
                new TodayMenuFrame(dishNumber, repeatTime, wantedList, avoidedList).setVisible(true);
            }
        });
        
        btnNewButton.setBounds(424, 410, 144, 27);
        contentPane.add(btnNewButton);
        
        //button Reset
        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dishNumberTxt.setText("1"); //set the textfield as 1
                repeatTimeTxt.setText("1");
                list.clearSelection(); //clear the list
                list_1.clearSelection();
            }
        });
        btnReset.setBounds(424, 450, 144, 27);
        contentPane.add(btnReset);

        //button Return
        JButton btnReturn = new JButton("Return");
        btnReturn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the current page
                dispose();
                // Open the menu selection interface
                new mainInterface().setVisible(true);
            }
        });
        btnReturn.setBounds(424, 490, 144, 27);
        contentPane.add(btnReturn);

    }
}


/*
Reference:
https://zhidao.baidu.com/question/84117550.html
https://jingyan.baidu.com/article/54b6b9c0b8a0cf2d583b47ef.html
 */
