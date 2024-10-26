
import javax.swing.JPanel;

import dao.FoodDao;
import model.CookBook;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookItemPanel extends JPanel {

    private JLabel bookNameLabel;

    /**
     * code of the button in the menu panel 
     * cookBook is the final dish in the arraylist cookBookTotal in TodayMenuFrame
     */
    public BookItemPanel(int index, CookBook cookBook, TodayMenuFrame menuFrame) {
        
        setLayout(null);

        setPreferredSize(new Dimension(378, 130));

        bookNameLabel = new JLabel("");
        bookNameLabel.setBounds(42, 24, 394, 18);
        add(bookNameLabel);
        
        //remove button
        JButton btnRemove = new JButton("remove");
        btnRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                menuFrame.cookBookTotal.remove(index);
                
                //use the method initCookBookList in TodayMenuFrame to refresh the today menu
                menuFrame.initCookBookList(menuFrame);

            }
        });
        btnRemove.setBounds(42, 82, 113, 27);
        add(btnRemove);
        
        //detail button
        JButton btnDetail = new JButton("detail");
        btnDetail.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                new CookBookShow(cookBook).setVisible(true);
                /*the cookbookshow window appear to show the cooking method of the dish 
                  generated when the user clicks detail
                 */
            }
        });
        btnDetail.setBounds(186, 82, 113, 27);
        add(btnDetail);

        bookNameLabel.setText(cookBook.getName());
        //call the method to get the name of the dish

    }

}
