package me.seg.codecounter;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CustomTextField extends JTextField {

	private String placeholderText;
	private boolean showPlaceholder;
	private boolean isListenerSet;
	private Color wrongColor;
	private Color defaultColor;
	
	public CustomTextField(String text, String placeholderText, Color wrongColor) {
		super(text);
		this.placeholderText = placeholderText;
		this.wrongColor = wrongColor;
		defaultColor = getBackground();
		setListener();
		isListenerSet = true;
		showPlaceholder();
	}
	
	public CustomTextField() {
		super();
		this.placeholderText = "";
		wrongColor = new Color(255,0,0,100);
	}
	
	public void setWrongColor(Color wrongColor) {
		this.wrongColor = wrongColor;
	}
	
	public void setWrong() {
		setBackground(wrongColor);
	}
	
	@Override
	public String getText() {
		if (showPlaceholder) {
			return "";
		}
		return super.getText();
	}
	
	@Override
	public void setText(String text) {
		hidePlaceholder();
		super.setText(text);
	}
	
	private void setFine() {
		setBackground(defaultColor);
	}
	
	public void setPlaceholder(String placeholderText) {
		this.placeholderText = placeholderText;
		if (!isListenerSet) {
			setListener();
		}
		showPlaceholder();
	}
	
	private void hidePlaceholder() {
		if (!showPlaceholder) {
			return;
		}
		super.setText("");
		setForeground(Color.BLACK);
		showPlaceholder = false;
	}
	
	
	private void showPlaceholder() {
		if (!super.getText().equals("")) {
			return;
		}
		showPlaceholder = true;
		setForeground(new Color(169, 169, 169));
		super.setText(placeholderText);
	}
	
	private void setListener() {
		this.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				hidePlaceholder();
				setFine();
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				showPlaceholder();
			}
		});
	}
	
}
