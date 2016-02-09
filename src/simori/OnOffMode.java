package simori;

public class OnOffMode implements Mode 
{
    public OnOffMode() 
    {
    }

    @Override
    public void processMatrixButton(GridButton button) 
    {
        // TODO Auto-generated method stub
        System.out.println("Matrix button processed in OnOffMode");
        System.out.println("X: " + button.getCoordsX() + "; Y: "+ button.getCoordsY());
    }

}
