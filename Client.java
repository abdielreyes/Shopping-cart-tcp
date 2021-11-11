import java.io.ObjectInputStream;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class Client{
	private final String host = "127.0.0.1";
	private final int port = 8081;
	private Socket socket;
	private ArrayList<Product> products;
	
	public static void main(String[] args){
		Client c = new Client();
		c.init();


	}
	void init(){
		try{
			socket = new Socket(host, port);
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			
			products = (ArrayList<Product>) ois.readObject();
			printProducts();

			ois.close();
			socket.close();
		}catch(Exception e){
			System.out.println(e);
		}

	}
	void printProducts(){
		for(Product product : products){
			System.out.println(product.toString());
		}
	}
	void menu(){
		System.out.println("\tMenu");
		System.out.println("(a)Add product");
		System.out.println("(d)Delete product");
		System.out.println("");
		System.out.println("(s)My shopping cart");
		System.out.println("(q)Exit");
		System.out.println("");

		Scanner sc = new Scanner(System.in);
		String option = sc.nextLine();
		switch(option){
			case "a":
				System.out.println("Add");
				break;
			case "q":
				System.out.println("Bye!");
				break;
			default:
				menu();
		}

	}	
}
