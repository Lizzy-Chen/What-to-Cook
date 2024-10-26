
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

/**
 * the menu list window
 *
 */
public class TodayMenuFrame extends JFrame {

    private JPanel contentPane;
    public Box vBox;
    private JScrollPane scrollPane;
    public List<CookBook> cookBookTotal;

    /**
     * Create the frame.
     */
    public TodayMenuFrame(int dishNumber, int repeatTime, List<String> wantedList, List<String> avoidedList) {
        //set the title and the panel
        setTitle("Today Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 645, 467);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        TodayMenuFrame menuFrame = this;

        JLabel lblTodayMenu = new JLabel("Today Menu");
        lblTodayMenu.setFont(new Font("Cambria", Font.PLAIN, 20));
        lblTodayMenu.setBounds(45, 53, 134, 20);
        contentPane.add(lblTodayMenu);

        // Create a vertical container for the menu list
        vBox = Box.createVerticalBox();

        // Create a scroll panel
        scrollPane = new JScrollPane(vBox);
        scrollPane.setBounds(31, 104, 415, 270);
        contentPane.add(scrollPane);
        
        //button return
        JButton btnReturn = new JButton("Return");
        btnReturn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the current page
                dispose();

                // Open the menu selection interface
                new MenuSelectFrame().setVisible(true);
            }
        });
        btnReturn.setBounds(480, 104, 113, 27);
        contentPane.add(btnReturn);
        
        //button confirm
        JButton btnConfirm = new JButton("Confirm");
        btnConfirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (cookBookTotal.size() == 0) {
                    JOptionPane.showMessageDialog(menuFrame, "Today Menu is Empty");
                    //show message if the menu is empty
                    return;
                }

                // Stores the menu list to the database
                for (CookBook cookBook : cookBookTotal) {
                    ChooseFood chooseFood = new ChooseFood();
                    chooseFood.setBook_id(cookBook.getId());//call the method to get the id

                    FoodDao.insertChooseFood(chooseFood);//call the method to insert the list
                }

                // message to show that the menu is saved successfully
                JOptionPane.showMessageDialog(menuFrame, "Confirm Success!");

                // Return to the menu selector
                dispose();
                new MenuSelectFrame().setVisible(true);

            }
        });
        btnConfirm.setBounds(480, 182, 113, 27);
        contentPane.add(btnConfirm);

        // Get qualified dishes
        getCookBookList(dishNumber, repeatTime, wantedList, avoidedList, menuFrame);
    }

    public void getCookBookList(int dishNumber, int repeatTime, List<String> wantedList, 
            List<String> avoidedList, TodayMenuFrame menuFrame) {

        cookBookTotal = new ArrayList<CookBook>(); 
        //create a arraylist to store the final menu
        
        //build a conditional string
        StringBuilder builder = new StringBuilder();
            
        if (wantedList.size() > 0) {
            builder.append(" and (");
        }

        // Stitching the required ingredients
        for (int i = 0; i < wantedList.size(); i++) {
            if (i == 0) {
                builder.append(" material like '%" + wantedList.get(i) + "%'");
            } else {
                builder.append(" or material like '%" + wantedList.get(i) + "%'");
            }

        }

        if (wantedList.size() > 0) {
            builder.append(" )");
        }

        // Stitch the materials avoided
        for (String avoided : avoidedList) {
            builder.append(" and material not like '%" + avoided + "%'");
        }

        // Call the method to get the information of the dish based on conditions 
        List<CookBook> cookBooks = FoodDao.getCookBookByCondition(builder.toString());

        // Call the method to get the previous menu selection information
        List<ChooseFood> chooseFoods = FoodDao.getLastChooseFood(repeatTime); 
        

        /*
        compare the dishes choosen based on material wanted & avoided (cookBooks) 
        with the ones that are in non repeated time (chooseFoods)
        if the one in cookBooks equals to the one in chooseFoods, delete the dish
        */
        for (ChooseFood chooseFood : chooseFoods) {
            for (CookBook cookBook : cookBooks) {
                if (chooseFood.getName().equals(cookBook.getName())) {
                    cookBooks.remove(cookBook);
                    break;
                }
            }
        }

        // From the existing dishes, generate the menu at random while the number of dishes equals to dishNumber
        //Generate random numbers in the range
        Random rand = new Random();
        //Iterate through the dish that meets the criteria
        for (int i = 0; i < cookBooks.size(); i++) {
            
            if (i < dishNumber) {
                //Generates random number within the range of the number of items
                int randNum = rand.nextInt(cookBooks.size());
                /*find if the dishes that get randomly is already in the final menu
                generate the random number again if it is
                */
                while (cookBookTotal.contains(cookBooks.get(randNum))) {
                    randNum = rand.nextInt(cookBooks.size());
                }
                //add the random dish into the menu
                cookBookTotal.add(cookBooks.get(randNum));
            }
        }
        
        //call the method initCookBookList to show the final menu
        initCookBookList(menuFrame);
    }

    public void initCookBookList(TodayMenuFrame menuFrame) {
        // Removes all elements from the container
        vBox.removeAll();

        // Add the dish to the vertical container to show it
        for (int i = 0; i < cookBookTotal.size(); i++) {
            
            //callBookItemPanel to show the dish
            BookItemPanel itemPanel = new BookItemPanel(i, cookBookTotal.get(i), menuFrame);

            vBox.add(itemPanel);
        }

        // Redraw the bBox
        vBox.updateUI();
        vBox.invalidate();
        vBox.validate();
        vBox.repaint();

        // Redraw the sliding panel
        scrollPane.updateUI();
        scrollPane.invalidate();
        scrollPane.validate();
        scrollPane.repaint();
    }
}
