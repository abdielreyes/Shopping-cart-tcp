import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Client{
	private final String host = "localhost";
	private final int port = 3017;
	private Socket clientSocket;
	
	public static void main(String[] args){
		Client c = new Client();
		c.init();


	}
	void init(){
		try{
			clientSocket = new Socket(host, port);
			
		}catch(Exception e){
			System.out.println(e);
		}

	}
	void getAllProducts(){
		try {
			ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());

			inputStream.close();
		} catch (Exception e) {
			System.out.println(e);
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
