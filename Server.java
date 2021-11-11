import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.UUID;

import javax.swing.JFileChooser;
import javax.swing.JFrame;



public class Server{
	
	private ServerSocket serverSocket;
	private Socket socket;
	private ObjectInputStream InputStream;
	private ObjectOutputStream OutputStream;
	private static final int port = 8081;
	public static void main(String[] args){
		Server s = new Server();
	//	s.createProduct();
		s.init();
	}
	public Server(){
		
	}
	void init(){
		try{
			while(true){
				serverSocket = new ServerSocket(8081);
				System.out.println("Waiting for connections");
				socket = serverSocket.accept();
				System.out.println("Connection from:\n"+serverSocket);

				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());


				Database db = new Database();
				oos.writeObject(db.getProducts());
						
				
				socket.close();
				oos.close();
				serverSocket.close();

			}
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
		String id = UUID.randomUUID().toString();
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(new JFrame("Choose File"));
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			try {
				Files.copy(selectedFile.toPath(),
						new File("./server_res/"+id+name).toPath(),
						StandardCopyOption.REPLACE_EXISTING); 
				Database db = new Database();
				Product p = new Product(id,name,description,id+selectedFile.getName(),price,stock);
				db.addProduct(p); 
				
			} catch (Exception e) {
				System.out.println("Error copying file"+e);
				System.out.println("Product not saved");
			}

		}

	}
}
