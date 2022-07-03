package lib.awt;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class TextField extends FensterAnimated{
	
	private String output;
	private int x = 200;
	private int y = 200;
	private int curserTime = 500;
	private int curserCounter = 0;
	private boolean curser = true;
	
	public TextField(){
		output="";
		this.setVisible(true);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		String temp="";
		char c = e.getKeyChar();
		
		temp+=""+c;	
		
		
		switch(e.getKeyCode()) {
		case KeyEvent.VK_BACK_SPACE: if(output.length() > 0) output = output.substring(0, output.length()-1); break;
		case KeyEvent.VK_SPACE: temp=" "; break;
		case KeyEvent.VK_ENTER: temp=";"; break;
		}
		
		output+=temp;
		this.repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		FontMetrics m = g.getFontMetrics();
		curserCounter += 20;
		if(curserCounter>curserTime) {
			curserCounter = 0;
			curser = !curser;
		}
		
		String outRow[] = output.split(";");
		for(int i = 0; i < outRow.length; i++) {
			g.drawString(outRow[i], x, y+15*i);
		}
		
		if(curser) {
			g.drawLine(x+m.stringWidth(outRow[outRow.length-1]), y+(outRow.length-2)*15, x+m.stringWidth(outRow[outRow.length-1]), y+(outRow.length-1)*15);
		}
		
	}
	
	public static void main(String args[]) {
		new TextField();
	}

}
