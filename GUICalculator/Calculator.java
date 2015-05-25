import java.awt.*;
import java.awt.event.*;

public class Calculator {
	public static void main(String[] args) {
		new MyFrame("Cauculator").launch();
	}
}

class MyFrame extends Frame {
	TextField tf = new TextField(15);
	MyFrame(String s) {
		super(s);
		Panel pn1 = new Panel();
		Panel pn2 = new Panel();
		Panel pn3 = new Panel();
		Panel pn4 = new Panel();
		final String[] name = {"0", "1", "2", "3", "4", "5", "6", "7", 
		"8", "9", "=", ".", "EC", "+", "-", "x", "/"};
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
	private class ButtonMonitor implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("EC")) {
				tf.setText("0");
			}else{
			    tf.setText(e.getActionCommand());
			}
		}
	}
}
