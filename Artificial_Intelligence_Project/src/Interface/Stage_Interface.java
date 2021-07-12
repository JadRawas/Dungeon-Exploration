package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import Final_Dungeon.Enemy;
import Final_Dungeon.Player;
import Final_Dungeon.Skill;
import Final_Dungeon.Stage;
import artificial_intelligence.SearchingTechniques;
import artificial_intelligence.multitree;

public class Stage_Interface extends JFrame {

	/*
	 * Attributes
	 */

	Stage stage;
	int stage_num;
	String log = "test";

	// Quick access
	private int enemies_num;
	private int skills_num;
	private Player player;
	private Skill[] skills;
	private Enemy[] enemies;

	// JComponents
	private JTextField[] enemies_hp_field;
	private JTextField[] skills_field;
	private JLabel player_hp_label;
	private JLabel[] player_pos_label;
	private String log_string = "";
	JTextArea AI_panel;
	JScrollPane scroll;

	/*
	 * Constructor
	 */

	public Stage_Interface(int lvl) {
		stage_num = lvl;
		initialize_game(lvl);
		create_frame();
		create_main_panel();

		// finalizing frame
		this.pack();
		this.setVisible(true);
	}

	/*
	 * Interface related functions
	 */

	private void create_frame() {

		this.setSize(1000, 500);
		// f.setResizable(false);
		// this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setTitle("Final Dungeon");

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
	}

	private void create_main_panel() {

		JPanel panel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		panel.setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();

		JPanel player = new JPanel();
		JPanel enemies = new JPanel();
		JPanel log = new JPanel();
		JPanel stage = new JPanel();

		create_player_panel(player);
		create_enemy_panel(enemies);
		create_log_panel(log);

		player.setPreferredSize(new Dimension(450, 215));
		player.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
		log.setPreferredSize(new Dimension(450, 215));
		log.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		enemies.setPreferredSize(new Dimension(450, 430));
		// BorderFactory.createMatteBorder(top, left, bottom, right, color)

		gbc.gridx = 1;
		gbc.gridy = 1;
		panel.add(player, gbc);
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridheight = 2;
		panel.add(enemies, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridheight = 1;
		panel.add(log, gbc);
		// panel.add(stage);

		this.getContentPane().add(panel);

	}

	public void create_player_panel(JPanel base) {

		// panel/panel settings
		base.setLayout(new BorderLayout());

		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		JPanel grid = new JPanel(new GridLayout(skills_num, 1));

		JPanel[] rows = new JPanel[skills_num];

		JScrollPane scrollPane = new JScrollPane(p);
		scrollPane.setPreferredSize(new Dimension(400, 200));
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		skills_field = new JTextField[skills_num];

		// skills
		grid.setPreferredSize(new Dimension(400, skills_num * 40));
		for (int i = 0; i < skills_num; i++) {
			rows[i] = new JPanel(new GridBagLayout());

			// skills
			JButton s = new JButton(skills[i].name);
			set_font(s, 20);
			s.setPreferredSize(new Dimension(200, 40));
			s.setBackground(Color.LIGHT_GRAY);
			useSkill(s, i);

			// info
			JButton info = new JButton("?");
			set_font(info, 20);
			info.setPreferredSize(new Dimension(50, 40));
			info.setBackground(Color.LIGHT_GRAY);
			getSkillInfo(info, i);

			// info
			skills_field[i] = new JTextField("" + skills[i].getCount());
			set_font(skills_field[i], 20);
			skills_field[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
			skills_field[i].setPreferredSize(new Dimension(50, 40));
			skills_field[i].setBackground(Color.LIGHT_GRAY);
			skills_field[i].setEditable(false);

			// adding comps
			gbc.gridy = 0;
			rows[i].add(info);
			gbc.gridy = 1;
			rows[i].add(s);
			gbc.gridy = 2;
			rows[i].add(skills_field[i]);

			grid.add(rows[i]);

		}
		// Labels
		player_hp_label = new JLabel("hp: " + player.hp);
		set_font(player_hp_label, 20);

		// Adding to components
		gbc.gridy = 0;
		p.add(new JLabel("PLAYER"), gbc);
		add_empty_space(p);
		gbc.gridy = 1;
		p.add(player_hp_label, gbc);
		add_empty_space(p);
		gbc.gridy = 2;
		p.add(grid, gbc);

		base.add(scrollPane, BorderLayout.NORTH);

	}

	public void create_enemy_panel(JPanel base) {

		// will be used later

		// panel/panel settings
		base.setLayout(new BorderLayout());

		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		JPanel grid = new JPanel(new GridLayout(enemies_num, 1));

		JPanel[] rows = new JPanel[enemies_num];

		JScrollPane scrollPane = new JScrollPane(p);
		scrollPane.setPreferredSize(new Dimension(450, 450));
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		enemies_hp_field = new JTextField[enemies_num];
		player_pos_label = new JLabel[enemies_num];

		// enemies
		grid.setPreferredSize(new Dimension(320, enemies_num * 40));
		for (int i = 0; i < enemies_num; i++) {
			rows[i] = new JPanel(new GridBagLayout());

			JTextField enemy_name = new JTextField(enemies[i].name + " ");
			set_font(enemy_name, 20);
			enemy_name.setPreferredSize(new Dimension(150, 40));
			enemy_name.setEditable(false);
			enemy_name.setBackground(Color.LIGHT_GRAY);

			// current pos
			player_pos_label[i] = new JLabel();
			player_pos_label[i].setPreferredSize(new Dimension(20, 40));

			// HP
			enemies_hp_field[i] = new JTextField("hp:" + enemies[i].gethp());
			set_font(enemies_hp_field[i], 20);
			enemies_hp_field[i].setPreferredSize(new Dimension(60, 40));
			enemies_hp_field[i].setEditable(false);
			enemies_hp_field[i].setBackground(Color.LIGHT_GRAY);

			// INFO
			JButton info = new JButton("?");
			set_font(info, 20);
			info.setPreferredSize(new Dimension(50, 40));
			info.setBackground(Color.LIGHT_GRAY);
			getEnemyInfo(info, i);

			JLabel num = new JLabel();
			num.setText(i + 1 + "");
			num.setPreferredSize(new Dimension(25, 40));
			set_font(num, 20);

			// adding to gridbag
			gbc.gridy = 0;
			rows[i].add(num);
			gbc.gridy = 1;
			rows[i].add(info);
			gbc.gridy = 2;
			rows[i].add(enemy_name);
			gbc.gridy = 2;
			rows[i].add(enemies_hp_field[i]);
			gbc.gridy = 3;
			rows[i].add(player_pos_label[i]);

			grid.add(rows[i]);

		}
		player_pos_label[0].setText("<html><h2 style='color: red'>&#8592;</h2></html>");

		// Adding to components
		gbc.gridy = 0;
		p.add(new JLabel("ENEMIES"), gbc);
		add_empty_space(p);
		gbc.gridy = 1;
		p.add(grid, gbc);

		base.add(scrollPane, BorderLayout.NORTH);

	}

	public void create_log_panel(JPanel base) {
		base.setLayout(new BoxLayout(base, BoxLayout.Y_AXIS));

		// upper panel
		JPanel button_panel = new JPanel(new FlowLayout());
		JButton AI_button = new JButton("Show");

		button_panel.add(new JLabel("AI"));
		button_panel.add(AI_button);

		// button listener
		JFrame j = this;
		AI_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!scroll.isVisible()) {
					scroll.setVisible(true);
					((JButton)e.getSource()).setText("Hide");
				} else {
					scroll.setVisible(false);
					((JButton)e.getSource()).setText("Show");
				}

				j.invalidate();
				j.validate();
				j.repaint();
			}
		});

		// AI_panel
		Stage ai_stage = new Stage(stage.enemies, stage.player);
		multitree tree = new multitree(ai_stage);
		SearchingTechniques S = new SearchingTechniques();
		S.Depth(tree);
		AI_panel = new JTextArea(S.soln);
		set_font(AI_panel, 15);

		// Scroll panel
		scroll = new JScrollPane(AI_panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVisible(false);

		base.add(button_panel);
		base.add(scroll);

	}

	/*
	 * Game related functions
	 */

	public void initialize_game(int lvl) {
		stage = Stage.generateStage(lvl);

		player = stage.player;
		skills = player.skills;
		enemies = stage.enemies;
		enemies_num = enemies.length;
		skills_num = skills.length;
	}

	/*
	 * Other Design Functions
	 */

	public void set_font(JComponent c, int size) {
		c.setFont(new Font(Font.DIALOG, Font.PLAIN, size));
	}

	public void add_empty_space(JPanel p) {
		p.add(Box.createRigidArea(new Dimension(0, 10)));
	}

	/*
	 * Click Listeners
	 */

	public void useSkill(JButton b, int i) {
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// apply action
				int status = stage.playerAction(i + 1);

				// update skill count
				skills_field[i].setText(skills[i].getCount());

				// update player hp
				player_hp_label.setText("hp: " + player.hp + "");

				// update enemy hp
				for (int j = 0; j < enemies.length; j++) {
					enemies_hp_field[j].setText("hp:" + enemies[j].gethp());
				}

				// update current pos
				for (int j = 0; j < enemies.length; j++) {
					player_pos_label[j].setText("");
				}
				if (stage.getCurrPos() >= 0)
					player_pos_label[stage.getCurrPos()].setText("<html><h2 style='color: red'>&#8592;</h2></html>");

				// if player won
				if (status == 1) {
					create_dialog_with_s("Congratulations!");
				}

				// if player lost
				else if (status == -1) {
					create_dialog_with_s("Game Over");
					select_new_stage(stage_num);
				}
			}
		});
	}

	public void getSkillInfo(JButton b, int i) {
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog info = new JDialog();
				info.setSize(550, 200);
				info.setTitle("Info");
				info.setResizable(false);
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				info.setLocation(dim.width / 2 - info.getSize().width / 2, dim.height / 2 - info.getSize().height / 2);

				JPanel panel = new JPanel();
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

				JPanel attributes = new JPanel(new FlowLayout());
				attributes.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
				JLabel sname = new JLabel("skill: " + skills[i].name);
				JLabel dmg = new JLabel("dmg: " + skills[i].getDmg());
				JLabel rem = new JLabel("Remaining: " + skills[i].getCount());
				set_font(sname, 20);
				set_font(dmg, 20);
				set_font(rem, 20);
				attributes.add(sname);
				attributes.add(Box.createRigidArea(new Dimension(100, 0)));
				attributes.add(dmg);
				attributes.add(Box.createRigidArea(new Dimension(100, 0)));
				attributes.add(rem);

				JTextArea desc = new JTextArea(skills[i].desc);
				desc.setOpaque(false);
				desc.setLineWrap(true);
				desc.setEditable(false);
				set_font(desc, 20);
				panel.add(attributes);
				panel.add(desc);

				info.add(panel);
				info.setModal(true);
				// info.pack();
				info.setVisible(true);

			}
		});

	}

	public void getEnemyInfo(JButton b, int i) {
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog info = new JDialog();
				info.setSize(508, 200);
				info.setTitle("Info");
				info.setResizable(false);
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				info.setLocation(dim.width / 2 - info.getSize().width / 2, dim.height / 2 - info.getSize().height / 2);

				JPanel panel = new JPanel();
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

				JPanel attributes = new JPanel(new FlowLayout());
				attributes.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
				JLabel sname = new JLabel("enemy: " + enemies[i].name);
				JLabel dmg = new JLabel("dmg: " + enemies[i].damage);
				JLabel rem = new JLabel("hp: " + enemies[i].gethp());
				set_font(sname, 20);
				set_font(dmg, 20);
				set_font(rem, 20);
				attributes.add(sname);
				attributes.add(Box.createRigidArea(new Dimension(100, 0)));
				attributes.add(dmg);
				attributes.add(Box.createRigidArea(new Dimension(100, 0)));
				attributes.add(rem);

				JTextArea desc = new JTextArea(enemies[i].description);
				desc.setOpaque(false);
				desc.setLineWrap(true);
				desc.setEditable(false);
				set_font(desc, 20);
				panel.add(attributes);
				panel.add(desc);

				info.add(panel);
				info.setModal(true);
				// info.pack();
				info.setVisible(true);

			}
		});

	}

	/*
	 * Other
	 */

	public void create_dialog_with_s(String s) {
		JDialog info = new JDialog();
		info.setSize(250, 150);
		info.setTitle("Info");
		info.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		info.setLocation(dim.width / 2 - info.getSize().width / 2, dim.height / 2 - info.getSize().height / 2);

		JTextField text = new JTextField(s);
		set_font(text, 30);
		text.setEditable(false);
		text.setHorizontalAlignment(SwingConstants.CENTER);

		info.add(text);
		info.setModal(true);
		// info.pack();
		info.setVisible(true);
	}

	public void select_new_stage(int lvl) {
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		new Stage_Interface(lvl);

	}

}
