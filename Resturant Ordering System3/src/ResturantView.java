import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ResturantView extends JFrame
{
	//ResturantView has a Menu
	private Menu menu;
	//ResturantView has a Customer
	private Customer user;
	
	//ResturantView GUI is divided into 3 panels, left, middle and right
	
	//All the stuff below is the left panel
	////ResturantView has a left panel.
	private JPanel leftPanel;
	//ResturantView has a title label for the left panel
	private JLabel menuLabel;
	//ResturantView has a label for all the menu items
	private JLabel menuItemsLabel;

	
	
	private JPanel middlePanel;
	private JLabel bankBalanceLabel;
	private JButton add20DollarsButton;
	private JButton viewRecentOrder;
	private JButton reOrder;
	private JButton refundRecentOrder;
	
	private JPanel rightPanel;
	private JLabel cartTitle;
	private JLabel itemsLabel;
	private JPanel cartItemsPanel; 
	private JLabel subtotal;
	private JButton checkout;

	private Border lineBorder;
	
	public ResturantView(Menu passedMenu, Customer passedUser)
	{
		menu = passedMenu;
		user = passedUser;

		// stuff for the left panel
		
		//initiates the left panel
		leftPanel = new JPanel();
		//left panel has a border layout
		leftPanel.setLayout(new BorderLayout());
		
		
		lineBorder = BorderFactory.createLineBorder(Color.DARK_GRAY);
		leftPanel.setBorder(lineBorder);
		
		//creates label for the panel
		menuLabel = new JLabel("Menu");
		menuLabel.setFont(new Font("Arial", Font.BOLD, 30));
		
		//centers label to middle of this panel.
		menuLabel.setHorizontalAlignment(JLabel.CENTER);
		
		
		
		leftPanel.add(menuLabel, BorderLayout.NORTH);
		JPanel menuItemsPanel = new JPanel();
		menuItemsPanel.setLayout(new GridLayout(0,2));
		
		for(int i=0; i < menu.getNumberOfItemsInMenu(); i++)
		{
			
			

			menuItemsLabel = new JLabel(menu.returnMenu()[i].getItemName() + " $" + menu.returnMenu()[i].getPrice());

			menuItemsLabel.setHorizontalAlignment(JLabel.CENTER);
			
			AddToCartButton button = new AddToCartButton(menu.returnMenu()[i], 1);
			button.addActionListener(new AddToCartButtonListener( this, button));
			
			
			menuItemsPanel.add(menuItemsLabel);
			menuItemsPanel.add(button);
			button.setText("Add To Cart");
			
			
		
			
		}
		leftPanel.add(menuItemsPanel, BorderLayout.CENTER);
		
		
		
		middlePanel = new JPanel();
		middlePanel.setBorder(lineBorder);
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));

		
		
		bankBalanceLabel = new JLabel("Current Balance: $"+ user.getBalance());
		bankBalanceLabel.setHorizontalAlignment(JLabel.CENTER);
		add20DollarsButton = new JButton("Add $20");
		add20DollarsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		refundRecentOrder = new JButton("Refund Recent Order");

		reOrder = new JButton("Reorder Recent Order");
		
		
		add20DollarsButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				user.add20Dollars();
				updateBalance();
			}
		});
		
		middlePanel.add(bankBalanceLabel);
		middlePanel.add(add20DollarsButton);
		middlePanel.add(refundRecentOrder);
		middlePanel.add(reOrder);

		// stuff for the right panel
		rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		rightPanel.setBorder(lineBorder);
		
		
		cartTitle = new JLabel("Cart");
		cartTitle.setFont(new Font("Arial", Font.BOLD, 30));
		cartTitle.setHorizontalAlignment(JLabel.CENTER);
		rightPanel.add(cartTitle, BorderLayout.NORTH);
		
		cartItemsPanel = new JPanel();
		cartItemsPanel.setLayout(new GridLayout(0,2));
		
		rightPanel.add(cartItemsPanel, BorderLayout.CENTER);

		JPanel rightPanelEnd = new JPanel();
		rightPanelEnd.setLayout(new GridLayout(1,1));
		
		subtotal = new JLabel("subtotal: $" + user.showCartSubtotal());
		rightPanelEnd.add(subtotal);
		
		checkout = new JButton("Checkout");
		rightPanelEnd.add(checkout);
		
		checkout.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					user.placeOrder();
					clearCart();
					updateBalance();
					updateSubtotal();
					JOptionPane.showMessageDialog(null, "Order Placed!");
				}
				catch (NotEnoughMoney e1)
				{
					JOptionPane.showMessageDialog(null, "You do not have enought money");
				}
				catch (CartIsEmpty e1)
				{
					JOptionPane.showMessageDialog(null, "You have nothing in your cart, please add stuff.");
				}
			}
		});
		
		rightPanel.add(rightPanelEnd,BorderLayout.SOUTH);
		
		
		this.setLayout(new GridLayout(1,3));
		this.add(leftPanel);
		this.add(middlePanel);
		this.add(rightPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Resturant App");
		this.setSize(1500, 1000);
		this.setVisible(true);

	}

	public void updateCart(Item item, int quantityOfItems)
	{

		Item tempItem = new Item(item);
		user.addToCart(tempItem, quantityOfItems);
		itemsLabel = new JLabel(user.returnCartArray()
				.get(user.getNumberOfItemsInCart() - 1).getItemName());
		itemsLabel.setHorizontalAlignment(JLabel.CENTER);
		cartItemsPanel.add(itemsLabel);
		RemoveFromCart button = new RemoveFromCart(tempItem);
		button.setText("Click to remove");
		button.addActionListener(
				new RemoveFromCartListener(this, button));

		cartItemsPanel.add(button);
		
		updateSubtotal();
		
		JOptionPane.showMessageDialog(null, "You have added " + item.getItemName() + " to your cart");

	}
	
	public void removeItem(Item item) {
		
		user.removeFromCart(item);
		rightPanel.remove(cartItemsPanel);
		cartItemsPanel = new JPanel();
		cartItemsPanel.setLayout(new GridLayout(0,2));
		
		for (int i=0; i < user.returnCartArray().size(); i++)
		{
			itemsLabel = new JLabel(user.returnCartArray().get(user.getNumberOfItemsInCart()-1).getItemName());
			itemsLabel.setHorizontalAlignment(JLabel.CENTER);
			cartItemsPanel.add(itemsLabel);
			
			
			RemoveFromCart button = new RemoveFromCart(user.returnCartArray().get(i));
			button.setText("Click to remove");
			button.addActionListener(new RemoveFromCartListener(this, button)); 
		
			
			cartItemsPanel.add(button);
			
			
		}
		rightPanel.add(cartItemsPanel);
		updateSubtotal();
	}
	
	public void clearCart()
	{

		rightPanel.remove(cartItemsPanel);
		cartItemsPanel = new JPanel();
		cartItemsPanel.setLayout(new GridLayout(0,2));
		rightPanel.add(cartItemsPanel);
		refresh();
	}
	
	public void refresh()
	{
		this.getContentPane().invalidate();
		this.getContentPane().validate();
	}

	public void updateBalance()
	{
		bankBalanceLabel.setText("Current Balance: $" + user.getBalance());
		
	}
	
	public void updateSubtotal()
	{
		subtotal.setText("subtotal: $" + user.showCartSubtotal());
		System.out.print(user.showCartSubtotal());
	}
	
}


