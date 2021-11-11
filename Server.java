import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;



public class Server{
	
	ServerSocket serverSocket;
	Socket socket;
	ObjectInputStream InputStream;
	ObjectOutputStream OutputStream;
	public static void main(String[] args){
		Server s = new Server();
		
		// s.createProduct();
	}
	public Server(){
		
	}
	void init(){
		try{
			serverSocket = new ServerSocket(3017);
			System.out.println("Waiting for connections");
			socket = serverSocket.accept();
			System.out.println("Connection from"+serverSocket);

	
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

		}catch(Exception e){
			System.out.println(e);
		}
	}

	public void createProduct(){
		String name;
		String description;
		double price;
		int stock;
		Scanner sc = new Scanner(System.in);
		System.out.println("Name: ");
		name = sc.nextLine();
		System.out.println("Description: ");
		description = sc.nextLine();
		System.out.println("Price: ");
		price = sc.nextDouble();
		System.out.println("Stock: ");
		stock = sc.nextInt();
		sc.close();
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(new JFrame("Choose File"));
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			try {
				Files.copy(selectedFile.toPath(),new File("./server_res/"+name).toPath(),StandardCopyOption.REPLACE_EXISTING); 
				Database db = new Database();
				Product p = new Product(name,description,selectedFile.getName(),price,stock);
				db.addProduct(p); 
				
			} catch (Exception e) {
				System.out.println("Error copying file"+e);
				System.out.println("Product not saved");
			}

		}

	}
}
