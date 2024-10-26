import javax.swing.JPanel;

import dao.FoodDao;
import model.CookBook;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class CookBookItemPanel extends JPanel {
	
	private JLabel bookNameLabel;

	/**
	 * Create the panel.
	 */
	public CookBookItemPanel(int index,CookBook cookBook, RecipeSearcherFrame menuFrame) {
		setBorder(new LineBorder(Color.BLACK, 1, true));
		setLayout(null);
		
		setPreferredSize(new Dimension(378, 83));
		
		bookNameLabel = new JLabel("");
		bookNameLabel.setBounds(10, 24, 219, 18);
		add(bookNameLabel);
                //get the name of the dish
                bookNameLabel.setText(cookBook.getName());
		
		JButton btnRemove = new JButton("remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int result = JOptionPane.showConfirmDialog(menuFrame, "Are you sure to delete?","Tip",JOptionPane.YES_NO_OPTION);
				
				// if the user select yes
				if(result == 0) {
					// delete the information of this recipe in the database by using the method deleteCookBookByID 
					FoodDao.deleteCookBookByID(cookBook.getId());
					
					// refresh the recipe list
					menuFrame.getCookBookList(menuFrame,"");
				}
			}
		});
		btnRemove.setBounds(309, 20, 81, 27);
		add(btnRemove);
		
		JButton btnDetail = new JButton("detail");
		btnDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//call the window CookBookShow to show the detail
				new CookBookShow(cookBook).setVisible(true);
				
			}
		});
		btnDetail.setBounds(239, 20, 69, 27);
		add(btnDetail);
		
		

	}

}
