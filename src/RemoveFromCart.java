import javax.swing.JButton;
public class RemoveFromCart extends JButton
{
	Item storedItem;
	
	public RemoveFromCart(Item item)
	{
		storedItem = item;
	}
	
	public Item getItem()
	{
		return storedItem;
		
	}
	
	
}