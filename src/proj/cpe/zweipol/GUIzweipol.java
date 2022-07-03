package proj.cpe.zweipol;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;

import lib.awt.FensterAnimated;

public class GUIzweipol extends FensterAnimated{
	
	/** Serielle ID */
	private static final long serialVersionUID = 1L;
	/** Head Line Height */
	private int hlh = 30;
	/** Hintergrundfarbe */
	private Color backgroundColor = new Color(57, 53, 53);
	/** Hauptfarbe */
	private Color mainColor = new Color(112, 112, 112);
	
	// Rechter Eingabebereich
	/** Panel fuer Eingabe */
	private Panel inputPanel;
	/** Panel fuer Diagramme */
	private Panel diagramPanel;
	
	/** Textfeld fuer Name */
	private TextField nameField;
	/** Textfeld fuer komplexen Wert */
	private TextField valueField;
	
	/** Button fuer weitere Eingabe */
	private Button plus;
	
	public GUIzweipol() {
		this.setTitle("Zweipol");
		this.setSize(1000,600+hlh);
		this.setResizable(false);
		this.setLayout(null);
		
		// Objekte fuer Bereiche bereitstellen
		prepareInputPanel();
		
		this.setVisible(true);
	}
	
	public void prepareInputPanel() {
		inputPanel = new Panel();
		diagramPanel = new Panel();
		inputPanel.setBounds(0,0,300,this.getHeight());
		diagramPanel.setBounds(300,0,700,this.getHeight());
		inputPanel.setBackground(backgroundColor);
		diagramPanel.setBackground(mainColor);
		inputPanel.setLayout(null);
		diagramPanel.setLayout(null);
		this.add(inputPanel);
		this.add(diagramPanel);
		
		nameField = new TextField(1);
		valueField = new TextField(10);
		nameField.setBackground(mainColor);
		valueField.setBackground(mainColor);
		inputPanel.add(nameField);
		inputPanel.add(diagramPanel);
		
		
		plus = new Button("+");
		plus.setBounds(20, hlh+10, 30, 30);
		plus.setBackground(mainColor);
		inputPanel.add(plus);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Erstellt ein leeres Armband mit der alten Groesse
		if (e.getActionCommand().equals("New")) {
			
		}
		
		System.out.println(e.getActionCommand().toString());
	}
	
	@Override
	public void paint(Graphics g) {

	}
	
	public static void main(String[] args) {
		new GUIzweipol();
	}

}
