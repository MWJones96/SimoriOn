package simori;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

class Buttons extends JButton implements ActionListener{
    
    private int x;
    private int y;
    private int number;
    
	@Override
	public void actionPerformed(ActionEvent e){

	}
        
        public void setCoords(int x, int y){
            this.x = x;
            this.y = y;
        }
        public void setNumber(int number){
            this.number = number;
        }
        public int getCoordsX(){
            return this.x;
        }
        public int getCoordsY(){
            return this.y;
        }
        public int getNumber(){
            return this.number;
        }
}