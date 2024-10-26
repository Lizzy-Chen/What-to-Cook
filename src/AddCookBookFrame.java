import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;

import dao.FoodDao;
import model.CookBook;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class AddCookBookFrame extends JFrame {

	private JPanel contentPane;
	private JTextField nameTxt;
	private JTextArea stepArea;
	private JList list;
	
	/**
	 * Create the frame.
	 */
	public AddCookBookFrame(RecipeSearcherFrame menuFrame) {
		setTitle("Recipe Searcher");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		AddCookBookFrame frame = this;

		//set the label name
                JLabel lblName = new JLabel("Name:");
		lblName.setBounds(27, 44, 54, 15);
		contentPane.add(lblName);

                //set the textfield to enter the name of the dish
                nameTxt = new JTextField();
		nameTxt.setBounds(91, 41, 169, 21);
		contentPane.add(nameTxt);
		nameTxt.setColumns(10);

		//set the label materials
                JLabel lblMaterials = new JLabel("Materials:");
		lblMaterials.setBounds(27, 88, 68, 15);
		contentPane.add(lblMaterials);

		//set the label steps
                JLabel lblSteps = new JLabel("Steps:");
		lblSteps.setBounds(27, 240, 68, 15);
		contentPane.add(lblSteps);

		//set the textarea to enter the step of the dish
                stepArea = new JTextArea();
		stepArea.setBounds(91, 240, 169, 95);
		contentPane.add(stepArea);

		//set the jlist for the user to choose the materials
                list = new JList();
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(91, 86, 169, 130);
		contentPane.add(scrollPane);

		//get all the materials from the database food_material 
		List<String> materials = FoodDao.getMaterials();
		String[] strings = new String[materials.size()];
		
		// Initialize the list of materials
		list.setListData(materials.toArray(strings));
		
		//save button
                JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String name = nameTxt.getText();
				String steps = stepArea.getText();

				//check if the textfield of name is empty, pop up the message if it is
                                if (name.length() == 0) {
					JOptionPane.showMessageDialog(frame, "please enter Name");
					return;
				}

				// get the index of all the materials that are selected
				int[] indices = list.getSelectedIndices();

				//if no material is selected, pop up the error message
                                if (indices.length == 0) {
                                        
					JOptionPane.showMessageDialog(frame, "please choose Materials");
					return;
				}

				//check if the textfield of step is empty, pop up the message if it is
                                if (steps.length() == 0) {

					JOptionPane.showMessageDialog(frame, "please enter Steps");
					return;
				}

				// store the name of the selected material in ListModel
				ListModel<String> listModel = list.getModel();
				
				StringBuilder builder = new StringBuilder();
				
				// store those names of material into the StringBuilder while each name is separated by a comma
				for (int index : indices) {
					builder.append(listModel.getElementAt(index)+",");
				}

				// add the information of the new recipe into the database cookbook by calling the method insertCookBook()
				FoodDao.insertCookBook(new CookBook(name, builder.toString().substring(0,builder.length()-1), steps));
			
				// refresh the recipe list
				menuFrame.getCookBookList(menuFrame,"");
				
                                //pop up the message to show that the new recipe is stored
				JOptionPane.showMessageDialog(frame, "New recipe is saved successfully", "Tip", JOptionPane.INFORMATION_MESSAGE);
				
				// close the window
				dispose();
			}
		});
		btnSave.setBounds(320, 268, 93, 23);
		contentPane.add(btnSave);

		//return button
                JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// close the window
				dispose();

			}
		});
		btnReturn.setBounds(320, 312, 93, 23);
		contentPane.add(btnReturn);

	}
}
