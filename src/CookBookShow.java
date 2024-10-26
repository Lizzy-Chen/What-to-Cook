
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.CookBook;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class CookBookShow extends JFrame {

	private JPanel contentPane;

	/**
	 * code of the button "detail"
         * show the cooking method of the dish generated when the user clicks detail
	 */
	public CookBookShow(CookBook cookBook) {
		setTitle("Detail");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
                //set the label "material"
		JLabel lblMaterial = new JLabel("Material:");
		lblMaterial.setBounds(31, 48, 72, 18);
		contentPane.add(lblMaterial);
		
                //set the label to show the material
		JLabel materialTxt = new JLabel("New label");
		materialTxt.setBounds(117, 48, 301, 18);
		contentPane.add(materialTxt);
		
                //set the label "step"
		JLabel lblNewLabel_1 = new JLabel("steps:");
		lblNewLabel_1.setBounds(31, 136, 72, 18);
		contentPane.add(lblNewLabel_1);
		
                //set the textarea to show the steps
		JTextArea stepsTxt = new JTextArea();
		stepsTxt.setLineWrap(true);
		stepsTxt.setBounds(117, 134, 301, 177);
		contentPane.add(stepsTxt);
		
                //set the scrollpane to slide the page
		JScrollPane scrollPane = new JScrollPane(stepsTxt);
		scrollPane.setBounds(117, 132, 301, 183);
		contentPane.add(scrollPane);
		
                //call the method in CookBook to get the material of the dish
		materialTxt.setText(cookBook.getMaterial());
                //call the method in CookBook to get the steps of the dish
		stepsTxt.setText(cookBook.getSteps()); 
                
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(117, 356, 113, 27);
		contentPane.add(btnNewButton);
		
		
	}
}
