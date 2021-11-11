import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class Client{
	private final String host = "127.0.0.1";
	private final int port = 8081;
	private Socket socket;
	private HashMap<String, Product> products;
	private ShoppingCart shoppingCart;
	Scanner sc = new Scanner(System.in);
	public static void main(String[] args){
		Client c = new Client();
		c.init();


	}
	void init(){
		try{
			socket = new Socket(host, port);
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			
			products = (HashMap<String, Product>) ois.readObject();
			shoppingCart = new ShoppingCart();

			printProducts();
			menu();
			ois.close();
			socket.close();
		}catch(Exception e){
			System.out.println(e);
		}

	}
	void printProducts(){
		ArrayList<Product> ps = new ArrayList<Product>(products.values());
		for(Product product : ps){
			System.out.println(product.toString());
		}
	}
	void addProduct(){
		System.out.println("id: ");
		String id = "";
		id=sc.nextLine();
		System.out.println("How many products?");
		int quantity = Integer.parseInt(sc.nextLine());
		Product p = null;
		for(Product product : products.values()){
			if(product.getId().equals(id)){
				p = product;
			}
		}
		shoppingCart.addProduct(p, quantity);
		menu();

	}
	void menu(){
		System.out.println("\tMenu");
		System.out.println("(a)Add product");
		System.out.println("(d)Delete product");
		System.out.println("(p)See products");
		System.out.println("(s)My shopping cart");
		System.out.println("(q)Exit");

		String option = sc.nextLine();
		switch(option){
			case "a":
				addProduct();
				break;
			case "q":
				System.out.println("Bye!");
				break;
			case "s":
				shoppingCart.printShoppingCart();
				menu();
				break;
			case "p":
				System.out.println("Products");
				printProducts();
				menu();
				break;
			default:
				menu();
		}

	}	
}
