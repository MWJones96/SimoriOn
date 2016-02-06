package simori;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class BasicGrid extends JFrame{
	//Jpanel
	JPanel panel = new JPanel();
	
	JButton L1  = new  JButton("L1");
	JButton L2 = new  JButton("L2");
	JButton L3 = new  JButton("L3");
	JButton L4  = new  JButton("L4");
	
	JButton R1  = new  JButton("R1");
	JButton R2 = new  JButton("R2");
	JButton R3 = new  JButton("R3");
	JButton R4  = new  JButton("R4");
	
	Buttons buttons[] = new Buttons[256]; 
	
	public static void main(String[] args) {
		new BasicGrid();
	}
	
	public BasicGrid(){
		//JFrame properties 
		setTitle("Basic Grid");
		setSize(500,500);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel.setLayout (new GridLayout(16,16));
		L1.setBounds(0,50,50,50);
		L2.setBounds(0,150,50,50);
		L3.setBounds(0,250,50,50);
		L4.setBounds(0,350,50,50);
		
		R1.setBounds(450,50,50,50);
		R2.setBounds(450,150,50,50);
		R3.setBounds(450,250,50,50);
		R4.setBounds(450,350,50,50);
		
		

		
		
		for(int i = 0; i<256; i++){
			buttons[i] = new Buttons();
			panel.add(buttons[i]);
		}
		add(L1); add(L2); add(L3); add(L4);
		add(R1); add(R2); add(R3); add(R4);
		add(panel);
		
	
		setVisible(true);
		
		
	}
}