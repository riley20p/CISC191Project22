import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveFromCartListener implements ActionListener
{
	private ResturantView view; 
	private RemoveFromCart button;
	
	public RemoveFromCartListener( ResturantView view, RemoveFromCart button)
	{
		this.view = view;
		this.button = button;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		view.updateSubtotal();
		
		view.removeItem(button.getItem());
		
		view.refresh();
	}
}
