package me.seg.codecounter;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Utils {

	private Thread process;
	private JButton button;
	private CustomTextField dirField;
	private CustomTextField extensionsField;
	private JTextArea detailsTextArea;
	private JLabel output;
	private boolean customExtensions;
	
	public Utils(JButton button, CustomTextField dirField, CustomTextField extensionsField, JTextArea detailsTextArea, JLabel output) {
		this.button = button;
		this.dirField = dirField;
		this.extensionsField = extensionsField;
		this.detailsTextArea = detailsTextArea;
		this.output = output;
	}
	
	public boolean isExtensionsFine() {
		String extensions = extensionsField.getText();
		return !extensions.contains(";;") &&
				!extensions.trim().isEmpty() &&
				extensions.charAt(0) != ';' &&
				extensions.charAt(extensions.length()-1) != ';';
	}
	
	public boolean isDirFine() {
		String dir = dirField.getText();
		File dirFolder = new File(dir);
		return dirFolder.isDirectory() && dirFolder.exists();
	}
	
	public void start() {
		detailsTextArea.setText("");
		process = new ExecutionThread(this);
		process.start();
	}
	
	public boolean stop() {
		if (process == null) {
			return false;
		}
		process.interrupt();
		process = null;
		return true;
	}
	
	public void execute(boolean customExtensions) {
		boolean useReturn = false;
		if (!isDirFine()) {
			useReturn = true;
			dirField.setWrong();
		}
		if (customExtensions) {
			if (!isExtensionsFine()) {
				useReturn = true;
				extensionsField.setWrong();
			}
		}
		if (useReturn) {
			return;
		}
		switch (button.getText()) {
		case "Start":
			this.customExtensions = customExtensions;
			start();
			button.setText("Stop");
			return;
		case "Stop":
			if (stop()) {
				button.setText("Start");
			}
			return;
		}
	}
	
	public File getDir() {
		String dir = dirField.getText();
		File folder = new File(dir);
		return folder;
	}
	
	public boolean hasCustomExtensions() {
		return customExtensions;
	}
	
	public String getExtensions() {
		return extensionsField.getText();
	}
	
	public JTextArea getDetails() {
		return detailsTextArea;
	}
	
	public JLabel getLabel() {
		return output;
	}
	
	public JButton getButton() {
		return button;
	}
}
