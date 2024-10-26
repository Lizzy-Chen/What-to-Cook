
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.FoodDao;
import model.ChooseFood;
import model.CookBook;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JScrollPane;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

/**
 * the menu list window
 *
 */
public class RecipeSearcherFrame extends JFrame {

    private JPanel contentPane;
    public Box vBox;
    private JScrollPane scrollPane;
    public List<CookBook> cookBookTotal;
    private JTextField textField;
    
    public static void main(String[] args) {
        RecipeSearcherFrame frame = new RecipeSearcherFrame();
        frame.setVisible(true);
    }

    /**
     * Create the frame.
     */
    public RecipeSearcherFrame() {
        //set the title & size of the frame
        setTitle("Recipe Searcher");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 521, 450);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        RecipeSearcherFrame menuFrame = this;

        // Create a vertical container for the menu list
        vBox = Box.createVerticalBox();

        // Creating a scroll panel
        scrollPane = new JScrollPane(vBox);
        scrollPane.setBounds(31, 60, 448, 314);
        contentPane.add(scrollPane);

        textField = new JTextField();
        textField.setToolTipText("name");
        textField.setBounds(31, 22, 261, 28);
        contentPane.add(textField);
        textField.setColumns(10);

        //search button
        JButton btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String name = textField.getText();

                //when the user has entered name of the recipe
                if (name.length() != 0) {

                    String condition = " and name like '%" + name + "%'";

                    // call the method getCookBookList() to search for the dishes based on the condition
                    getCookBookList(menuFrame, condition);

                } else {
                    // call the method getCookBookList() to refresh the recipe list when nothing is entered
                    getCookBookList(menuFrame, "");
                }

            }
        });
        btnSearch.setBounds(302, 22, 80, 28);
        contentPane.add(btnSearch);

        //add button
        JButton btnNewButton = new JButton("Add");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //call the method AddCookBookFram() to add the recipe
                new AddCookBookFrame(menuFrame).setVisible(true);

            }
        });
        btnNewButton.setBounds(392, 22, 87, 28);
        contentPane.add(btnNewButton);

        //refresh the recipe list
        getCookBookList(menuFrame, "");

        //Return button
        JButton btnReturn = new JButton("Return");
        btnReturn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the current page
                dispose();
                // Open the menu selection interface
                new mainInterface().setVisible(true);
            }
        });
        //set the apperance of the button
        btnReturn.setBounds(392, 390, 80, 28);
        contentPane.add(btnReturn);
    }

    // method getCookBookList() to get the list of cookbook
    public void getCookBookList(RecipeSearcherFrame menuFrame, String condition) {

        // get the recipe list (cook book) based on conditions from the database by calling the method getCookBookByCondition
        cookBookTotal = FoodDao.getCookBookByCondition(condition);

        //when nothing is entered in the search box, pop up the error message
        if (condition.length() != 0 && cookBookTotal.size() == 0) {
            JOptionPane.showMessageDialog(menuFrame, "Unable to find this recipe,you can search again or add new recipe in the upper right corner");
            return;
        }

        // call the method initCookBookLis to show the recipes that have been gotten based on conditions on the frame
        initCookBookList(menuFrame);
    }

    public void initCookBookList(RecipeSearcherFrame menuFrame) {
        // Removes all elements from the container
        vBox.removeAll();

        // Add the dish to the vertical container by using a for loop
        for (int i = 0; i < cookBookTotal.size(); i++) {

            // call CookBookItemPanel
            CookBookItemPanel itemPanel = new CookBookItemPanel(i, cookBookTotal.get(i), menuFrame);

            // add Jpanel to the container
            vBox.add(itemPanel);
        }

        // reset the vertical container
        vBox.updateUI();
        vBox.invalidate();
        vBox.validate();
        vBox.repaint();

        // reset the scrollpane (since its height will change after sth is add/delete)
        scrollPane.updateUI();
        scrollPane.invalidate();
        scrollPane.validate();
        scrollPane.repaint();
    }
}
