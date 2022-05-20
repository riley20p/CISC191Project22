import javax.swing.JButton;

public class AddToCartButton extends JButton
{
	Item storedItem;
	int storedQuantity;
	
	public AddToCartButton(Item item, int quantity)
	{
		storedItem = item;
		storedQuantity = quantity;
	}
	
	public Item getItem()
	{
		return storedItem;
		
	}
	
	public int getQuantity()
	{
		return storedQuantity;
		
	}
	
}
	