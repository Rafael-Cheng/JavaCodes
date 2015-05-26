/**
 *A simple calculator with GUI
 *@author Rafael Cheng
 *Haven't finished yet
 *What to do next: available for long int and negative numbers.
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
	MyFrame ref = this; //reference of this class, used to invoke the lanunch method continuously.
	ActionEvent actevt; //a reference of ActionEvent, used to distinguish "."
	TextField tf = new TextField(15); //set the size of textfield.
	String s; //Array to store the name of each button.
	double num1, num1f, num1b, num2, num2f, num2b, result, mm = 1; 
	//the number used, f:integer part b:decimal part; mm multiplies itself every time than multiply number to get decimal part.
	int operator, bit; //the number operator stands; bit is about decimal point.
	boolean flag, jump; //two flags.

	MyFrame(String s) {
		super(s);
		Panel pn1 = new Panel();
		Panel pn2 = new Panel();
		Panel pn3 = new Panel();
		Panel pn4 = new Panel();
		final String[] name = {"7", "8", "9", "4", "5", "6", "1", "2", 
		"3", "0", ".", "=", "EC", "+", "-", "x", "/"}; //strs for buttons names.
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
		for(int i = 0; i < 12; i++) { //add button to panel and sign up action listener to the buttons.
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
	private class WindowMonitor extends WindowAdapter { //class of window adapter
		public void windowClosing(WindowEvent e) {
			setVisible(false);
			System.exit(0);
		}
	}
	class ButtonMonitor implements ActionListener { //class of action listener
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("EC")) {
				flag = jump = false; //clean up the data.
				bit = operator = 0;
				num1 = num1f = num1b = num2 = num1f = num1b = result = 0;
				tf.setText("0");
			}else {
				s = e.getActionCommand();
				actevt = e; //make sure the reference of the action event is available.
				jump = false;
				ref.launch();//Keep running
			}
		}
	}
	void launch() {//This try&catch is used to distinguish num&operator
	    try {
			if(!flag && actevt.getActionCommand().equals(".")) { //test decimal point of the 1st nummber.
				bit = 1;
				jump = true;
				System.out.println("line:86");
			}
			if(!flag && bit == 0) { //1st number's integer part.
			    num1f = (10 * num1f) + Integer.parseInt(s);
				num1 = num1f + num1b;
				tf.setText("" + num1);
				System.out.println("line:92");
			}
			if(!flag && bit == 1 && (!jump)) { //1st number's decimal part.
				mm *= 0.1;
				num1b = num1b + (mm * Integer.parseInt(s));
				num1 = num1f + num1b;
				tf.setText("" + num1);
				System.out.println("line:99");
			}
			if(flag && actevt.getActionCommand().equals(".")) { //test decimal point of the 2nd number.
				bit = 2;
				jump = true;
				System.out.println("line:104");
			}
			if(flag && bit == 0) { //2nd number's integer part.
				num2f = (10 * num2f) + Integer.parseInt(s);
				num2 = num2f + num2b;
				tf.setText("" + num2);
				System.out.println("line:110");
			}
			if(flag && bit == 2 && (!jump)) { //2nd number's decimal part.
				mm *= 0.1;
				num2b = num2b + (mm * Integer.parseInt(s));
				num2 = num2f + num2b;
				tf.setText("" +num2);
			}
		}catch (NumberFormatException e) { //when the input is not number or decimal point.
			System.out.println("line:119");
			if(s.equals("+")) {
				operator = 1;
				mm = 1;
				bit = 0;
				flag = true; //once an operator occured, alter the value of the othe number.
			}else if(s.equals("-")) {
				operator = 2;
				mm = 1;
				bit = 0;
				flag = true;
			}else if(s.equals("x") || s.equals("X")) {
				operator = 3;
				mm = 1;
				bit = 0;
				flag =true;
			}else if(s.equals("/")) {
				operator = 4;
				mm = 1;
				bit = 0;
				flag = true;
			}else if(s.equals("=")) {
				switch(operator) {
					case 1: num1 = result = num1 + num2;
					        num2 =  num2f = num2b = bit = 0;
							mm = 1;
					        tf.setText("" + result);
					        break;
					case 2: num1 = result = num1 - num2;
					        num2 = num2f = num2b = bit = 0;
							mm = 1;
					        tf.setText("" + result);
					        break;
					case 3: num1 = result = num1 * num2;
					        num2 = num2f = num2b = bit = 0;
							mm = 1;
					        tf.setText("" + result);
					        break;
					case 4: num1 = result = num1 / num2;
					        num2 = num2f = num2b = bit = 0;
							mm = 1;
					        tf.setText("" + result);
					        break;
					default:break;
				}
			}
		}
	}
}
