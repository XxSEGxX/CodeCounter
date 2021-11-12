package me.seg.codecounter;

import javax.swing.SwingUtilities;

public class CodeCounter {
	public static void main(String args[]) {
		System.out.println("CodeCounter V1.0 Maded by SEG");
		System.out.println("https://github.com/XxSEGxX/");
		System.out.println("");
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Gui().setDir(String.join(" ", args)).setVisible(true);
			}
		});
	}
}
