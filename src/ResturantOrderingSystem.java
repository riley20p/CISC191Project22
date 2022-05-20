
public class testing
{
	public static void main(String[] args)
	{
		Menu menu = new Menu();
		
		Customer joe = new Customer("John", "Smith", 1000);
		
		Item test = new Item("Hello", 5);
				
		Cart cart = new Cart();
		
		cart.addToCart(test, 1);
		System.out.println(cart.getCart().size());
		cart.removeFromCart(test);
		System.out.println("5");
		System.out.println(cart.getCart().size());
		System.out.println(cart.displaySubtotal());
		
	
		
		ResturantView theView = new ResturantView(menu, joe);
	}
}
