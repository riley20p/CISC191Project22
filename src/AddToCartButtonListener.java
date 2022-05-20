import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class AddToCartButtonListener implements ActionListener
{

	private ResturantView view; 
	private AddToCartButton button;
	
	public AddToCartButtonListener(ResturantView view, AddToCartButton button)
	{
		this.view = view;
		this.button = button;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		view.updateCart(button.getItem(), button.getQuantity());
		view.refresh();
	}
	
	
}
