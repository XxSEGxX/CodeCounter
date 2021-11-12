package me.seg.codecounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class ExecutionThread extends Thread {

	private int lines;
	private Utils utils;
	private File dir;
	private boolean customExtensions;
	private String[] extensions;
	private JTextArea textArea;
	private JLabel output;
	private JButton button;
	
	public ExecutionThread(Utils utils) {
		lines = 0;
		this.utils = utils;
		this.dir = utils.getDir();
		this.customExtensions = utils.hasCustomExtensions();
		this.extensions = utils.getExtensions().split("[;]");
		textArea = utils.getDetails();
		this.output = utils.getLabel();
		this.button = utils.getButton();
	}
	
	@Override
	public void run() {
		addText("Initial dir "+dir);
		readFolder(dir);
		utils.stop();
		button.setText("Start");
		output.setText("The files contain "+lines+" lines");
	}
	
	private void readFolder(File folder) {
		addText("Opening folder " + removeMainDir(folder.getAbsolutePath()));
		File[] files = folder.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				readFolder(file);
			} else {
				addText("Reading file " + removeMainDir(file.getAbsolutePath()));
				int fileLines = readFile(file);
				addText(fileLines + " lines has been added");
				lines += fileLines;
				addText(lines + " lines in total");
			}
		}
	}
	
	private int readFile(File file) {
		if (!hasValidExtension(file)) {
			return 0;
		}
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException ignored) {}
		
		int fileLines = 0;
		while (scanner.hasNextLine()) {
			scanner.nextLine();
			fileLines++;
		}
		scanner.close();
		return fileLines;
	}
	
	private String removeMainDir(String dir) {
		return dir.replace(this.dir.getAbsolutePath(), "");
	}
	
	private boolean hasValidExtension(File file) {
		if (!customExtensions) {
			return true;
		}
		String name = file.getName();
		for (String extension : extensions) {
			if (name.endsWith("."+extension)) {
				return true;
			}
		}
		return false;
	}
	int test = 0;
	private void addText(String line) {
		textArea.setText(textArea.getText()+line+"\n");
	}
	
}
