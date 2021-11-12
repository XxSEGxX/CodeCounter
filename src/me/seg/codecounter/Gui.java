package me.seg.codecounter;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.BoundedRangeModel;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

public class Gui extends JFrame {

	private static final long serialVersionUID = -1280980752131988516L;
	
	private ButtonGroup group;
	private CustomTextField directoryField;
	private CustomTextField extensionsField;
	private JButton openButton;
	private JButton actionButton;
	private JLabel filesExtension;
	private JLabel extensionsInfo;
	private JLabel output;
	private JRadioButton radioAll;
	private JRadioButton radioCustom;
	private JScrollPane scrollPane;
	private JSeparator separatorOne;
	private JSeparator separatorTwo;
	private JSeparator separatorThree;
	private JTextArea details;
	
	private Utils utils;

	public Gui() {
		group = new ButtonGroup();
		directoryField = new CustomTextField();
		extensionsField = new CustomTextField();
		openButton = new JButton();
		actionButton = new JButton();
		filesExtension = new JLabel();
		extensionsInfo = new JLabel();
		output = new JLabel();
		radioAll = new JRadioButton();
		radioCustom = new JRadioButton();
		scrollPane = new JScrollPane();
		separatorOne = new JSeparator();
		separatorTwo = new JSeparator();
		separatorThree = new JSeparator();
		details = new JTextArea();

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setIconImage(new ImageIcon(getClass().getResource("icon")).getImage());
		
		setTitle("CodeCounter V1.0 - SEG");
		
		directoryField.setPlaceholder("Folder directory");

		openButton.setText("Open");
		openButton.addActionListener(this::openFiles);

		radioAll.setText("All extensions");
		radioAll.setSelected(true);
		radioAll.addActionListener(this::onRadioChange);
		group.add(radioAll);

		filesExtension.setFont(new Font("Tahoma", 1, 12));
		filesExtension.setText("Files extension:");

		radioCustom.setText("Custom extensions");
		radioCustom.addActionListener(this::onRadioChange);
		group.add(radioCustom);

		extensionsField.setPlaceholder("java;yml");
		
		extensionsInfo.setText("(Separate extensions with ';')");

		details.setEditable(false);
		details.setColumns(20);
		details.setRows(5);
		scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			BoundedRangeModel brm = scrollPane.getVerticalScrollBar().getModel();
			boolean wasAtBottom = true;
			
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				if (!brm.getValueIsAdjusting()) {
					if (wasAtBottom) {
						brm.setValue(brm.getMaximum());
					}
				} else {
					wasAtBottom = (brm.getValue() >= brm.getMaximum()-brm.getExtent() * 1.4);
				}
			}
		});
		scrollPane.setViewportView(details);

		output.setFont(new Font("Tahoma", 1, 24));
		output.setHorizontalAlignment(SwingConstants.CENTER);
		output.setText("The files contain 9999 lines");

		actionButton.setFont(new Font("Tahoma", 1, 11));
		actionButton.setText("Start");
		actionButton.addActionListener(this::execute);
		
		utils = new Utils(actionButton, directoryField, extensionsField, details, output);
		
		GroupLayout layout = new GroupLayout(getContentPane());
		
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
			.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(
				layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(
						layout.createParallelGroup(
							GroupLayout.Alignment.LEADING)
							.addComponent(separatorThree)
							.addGroup(
								layout.createSequentialGroup()
									.addComponent(
										directoryField,
										GroupLayout.PREFERRED_SIZE,
										1,
										Short.MAX_VALUE)
								.addPreferredGap(
									LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(
									openButton))
					.addGroup(
						layout.createSequentialGroup()
							.addComponent(
								radioAll)
							.addGap(18, 18,
								18)
							.addComponent(
								radioCustom)
							.addPreferredGap(
								LayoutStyle.ComponentPlacement.UNRELATED)
							.addGroup(
								layout.createParallelGroup(
									GroupLayout.Alignment.LEADING)
									.addGroup(
										layout.createSequentialGroup()
										.addComponent(
											extensionsInfo)
										.addGap(0,
											0,
											Short.MAX_VALUE))
									.addComponent(
											extensionsField)))
						.addComponent(separatorOne)
						.addComponent(separatorTwo)
						.addComponent(scrollPane)
						.addGroup(
							layout.createSequentialGroup()
								.addComponent(
									filesExtension)
								.addGap(0,
									0,
									Short.MAX_VALUE))
						.addComponent(
							output,
							GroupLayout.DEFAULT_SIZE,
							GroupLayout.DEFAULT_SIZE,
							Short.MAX_VALUE)
						.addComponent(
							actionButton,
							GroupLayout.Alignment.TRAILING,
							GroupLayout.DEFAULT_SIZE,
							GroupLayout.DEFAULT_SIZE,
							Short.MAX_VALUE))
				.addContainerGap()));
		
		layout.setVerticalGroup(layout
			.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(
				layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(
						layout.createParallelGroup(
							GroupLayout.Alignment.BASELINE)
							.addComponent(
								directoryField,
								GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
							.addComponent(openButton))
				.addPreferredGap(
					LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(separatorOne,
					GroupLayout.PREFERRED_SIZE,
					10,
					GroupLayout.PREFERRED_SIZE)
				.addGap(8, 8, 8)
				.addComponent(filesExtension)
				.addGap(1, 1, 1)
				.addGroup(
					layout.createParallelGroup(
						GroupLayout.Alignment.BASELINE)
						.addComponent(radioAll)
						.addComponent(radioCustom)
						.addComponent(
							extensionsField,
							GroupLayout.PREFERRED_SIZE,
							GroupLayout.DEFAULT_SIZE,
							GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(
					LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(extensionsInfo)
				.addPreferredGap(
					LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(separatorTwo,
					GroupLayout.PREFERRED_SIZE,
					10,
					GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(
					LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(actionButton)
				.addPreferredGap(
					LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(separatorThree,
					GroupLayout.PREFERRED_SIZE,
					10,
					GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(
					LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(scrollPane)
				.addPreferredGap(
					LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(output).addContainerGap()));
		
		pack();
		
		output.setText("");
		extensionsField.setVisible(false);
		extensionsInfo.setVisible(false);
		setLocationRelativeTo(null);
	}
	
	public Gui setDir(String dir) {
		directoryField.setText(dir);
		utils.execute(false);
		return this;
	}
	
	private void onRadioChange(ActionEvent e) {
		JRadioButton source = (JRadioButton) e.getSource();
		boolean show = source.getText().equals("Custom extensions");
		extensionsField.setVisible(show);
		extensionsInfo.setVisible(show);
	}

	private void openFiles(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int option = fc.showOpenDialog(null);
		if (option == 0) {
			directoryField.setText(fc.getSelectedFile().getAbsolutePath());
		}
	}
	
	private void execute(ActionEvent e) {
		utils.execute(radioCustom.isSelected());
	}
}
