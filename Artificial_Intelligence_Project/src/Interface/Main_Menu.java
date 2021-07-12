package Interface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class Main_Menu {

	public JFrame f;

	
	/*
	 * Constructor
	 */
	public Main_Menu() {

		create_frame();
		add_components();
		
		//Finalizing JFrame
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		f.setLocation(dim.width / 2 - f.getSize().width / 2, dim.height / 2 - f.getSize().height / 2);
		f.setVisible(true);
	}
	
	private void create_frame() {
		f = new JFrame();
		f.setSize(750, 500);
		//f.setResizable(false);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		f.setTitle("Final Dungeon");
		
		
	}

	private void add_components() {
		JPanel panel = new JPanel();
		JScrollPane scrollPane = new JScrollPane(panel);
		
		//creating label
		JLabel label = new JLabel("Start a new game");
		generic_component_design(label);
		panel.add(label);
		
		//adding buttons
		create_buttons(panel);
		
		//final adjustments
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createEmptyBorder(0,15,15,15));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		f.getContentPane().add(scrollPane);
		
	}
	
	private void create_buttons(JPanel p) {
		/*JButton b1 = new JButton("level 1");
		JButton b2 = new JButton("level 2");
		JButton b3 = new JButton("level 3");
		JButton b4 = new JButton("level 4");
		JButton b5 = new JButton("level 5");
		JButton b6 = new JButton("level 6");
		JButton b7 = new JButton("level 7");
		generic_component_design(b1);
		generic_component_design(b2);
		generic_component_design(b3);
		generic_component_design(b4);
		generic_component_design(b5);
		generic_component_design(b6);
		generic_component_design(b7);
		
		p.add(Box.createRigidArea(new Dimension(0, 10)));
		p.add(b1);
		p.add(Box.createRigidArea(new Dimension(0, 10)));
		p.add(b2);
		p.add(Box.createRigidArea(new Dimension(0, 10)));
		p.add(b3);
		p.add(Box.createRigidArea(new Dimension(0, 10)));
		p.add(b4);
		p.add(Box.createRigidArea(new Dimension(0, 10)));
		p.add(b5);
		p.add(Box.createRigidArea(new Dimension(0, 10)));
		p.add(b6);
		p.add(Box.createRigidArea(new Dimension(0, 10)));
		p.add(b7);
		p.add(Box.createRigidArea(new Dimension(0, 10)));*/
		
		for (int i = 1; i <= 3; i++) {
			JButton b1 = new JButton("level "+i);
			generic_component_design(b1);
			p.add(Box.createRigidArea(new Dimension(0, 10)));
			p.add(b1);
			int j = i;
			b1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					new Stage_Interface(j);
				}
			});
		}
	}
	
	private void generic_component_design(JComponent c) {
		c.setAlignmentX(Component.CENTER_ALIGNMENT);
		c.setMaximumSize(new Dimension(100, 50));
		c.setFont(c.getFont().deriveFont(15.0f));
		
		if(c instanceof JButton) {
			c.setBackground(Color.LIGHT_GRAY);
		}
		
		if(c instanceof JLabel) {
			c.setMaximumSize(new Dimension(c.getFontMetrics(c.getFont()).stringWidth(((JLabel) c).getText()), 50));
		}
	}
	
	
	/*
	 * Main
	 */

	public static void main(String[] args) {
		
		//new Stage_Interface(7);
		new Main_Menu();
	}
}
