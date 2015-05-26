/**
 *A simple calculator with GUI
 *@author Rafael Cheng
 *Haven't finished yet
 *What to do next is make sure that it can calculate continuously.
 *last change 2015.5.26
 */
import java.awt.*;
import java.awt.event.*;

public class Calculator {
	public static void main(String[] args) {
		new MyFrame("Cauculator").launch();
	}
}

class MyFrame extends Frame {
	MyFrame ref = this;
	TextField tf = new TextField(15);
	String s;
	int num1, num2, result, operator;
	boolean flag = false;
	MyFrame(String s) {
		super(s);
		Panel pn1 = new Panel();
		Panel pn2 = new Panel();
		Panel pn3 = new Panel();
		Panel pn4 = new Panel();
		final String[] name = {"0", "1", "2", "3", "4", "5", "6", "7", 
		"8", "9", "=", ".", "EC", "+", "-", "x", "/"};//strs for buttons
		Button[] button = new Button[name.length];
		for(int i = 0; i < button.length; i++) {
			button[i] = new Button("" + name[i]);
		}
		pn2.setLayout(new GridLayout(1, 2));
		pn3.setLayout(new GridLayout(4, 3));
		pn4.setLayout(new GridLayout(5, 1));
		this.add(tf, BorderLayout.NORTH);
		this.add(pn2);
		pn2.add(pn3);
		pn2.add(pn4);
		for(int i = 0; i < 12; i++) {
			pn3.add(button[i]);
			button[i].addActionListener(new ButtonMonitor());
		}
		for(int i = 12; i < button.length; i++) {
			pn4.add(button[i]);
			button[i].addActionListener(new ButtonMonitor());
		}
		tf.setText("0");
		this.addWindowListener(new WindowMonitor());
		setSize(300, 300);
		setVisible(true);
	}
	private class WindowMonitor extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			setVisible(false);
			System.exit(0);
		}
	}
	class ButtonMonitor implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("EC")) {
				flag = false;
				num1 = num2 = result = 0;
				tf.setText("0");
			}else{
				s = e.getActionCommand();
				ref.launch();//Keep running
			}
		}
	}
	void launch() {//This try&catch is used to distinguish num&operator 
		try {
			if(!flag) {
			    num1 = (10 * num1) + Integer.parseInt(s);
				tf.setText("" + num1);
			}else {
				num2 = (10 * num2) + Integer.parseInt(s);
				tf.setText("" + num2);
			}
		}catch (Exception e) {
			if(s.equals("+")) {
				operator = 1;
				flag = true; //once an operator occured, alter the value of the othe number.
			}else if(s.equals("-")) {
				operator = 2;
				flag = true;
			}else if(s.equals("x") || s.equals("X")) {
				operator = 3;
				flag =true;
			}else if(s.equals("\\")) {
				operator = 4;
				flag = true;
			}else if(s.equals("=")) {
				switch(operator) {
					case 1: result = num1 + num2;
					        tf.setText("" + result);
					        break;
					case 2: result = num1 - num2;
					        tf.setText("" + result);
					        break;
					case 3: result = num1 * num2;
					        tf.setText("" + result);
					        break;
					case 4: result = num1 / num2;
					        tf.setText("" + result);
					        break;
					default:break;
				}
			}
		}
	}
}
