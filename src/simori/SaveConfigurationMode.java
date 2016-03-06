package simori;

public class SaveConfigurationMode implements Mode
{

	public SaveConfigurationMode(){
		// Turn Off Clockhand
		{
			if (SimoriOn.getClockHand() != null) {
				SimoriOn.getClockHand().running.set(false);
			}
		}
	}
	@Override
	public void processMatrixButton(GridButton button) {
		// TODO Auto-generated method stub
		System.out.println("Matrix button processed in Save Configuration Mode");

		// Highlight row and column of the button
		button.getGUI().highlightColumnAndRow(button.getCoordsX(),
				button.getCoordsY());

		char[][] alph = {{'a', 'b', 'c', 'd', 'e'}, {'f', 'g', 'h', 'i', 'j'},{ 'k', 'l', 'm', 'n', 'o'}, {'p'
			,'q', 'r', 's', 't'},{ 'u', 'v', 'w', 'x', 'y'}, {'z'}};

		int x = button.getCoordsX();
		int y = button.getCoordsY();

		if (x == 15 && y != 0){
			SimoriOn.getInstance().getGui().writeToLCD(alph[(15 - y) / 3][4] + "");
		}
		else if (y == 0 && x > 2){
			SimoriOn.getInstance().getGui().writeToLCD(alph[5][0] + "");
		}
		else {
			SimoriOn.getInstance().getGui().writeToLCD(alph[(15 - y) / 3][x / 3] + "");
		}

	}

	public void processOKButton(){

	}

}
