import javax.swing.JTextField;

public class TextFieldForMenu extends JTextField
{
	int storedQuantity = 0;
	

	public TextFieldForMenu(String string)
	{
		super(string);
		
		try
		{
			storedQuantity = Integer.parseInt(string);
		}
		catch(Exception e)
		{
			storedQuantity = -1;
		}
	}
	
	

	public int getQuantity()
	{
		
		return storedQuantity;
		
	}
}
