import java.util.ArrayList;
import java.util.HashMap;


public class ShoppingCart{

	private HashMap<String,Item> cart;
	
	public ShoppingCart(){
		cart = new HashMap<String,Item>();
	}
	void addProduct(Product p, int quantity){

		if(cart.containsKey(p.getId())){
			if(cart.get(p.getId()).quantity<=p.getStock()){
				cart.get(p.getId()).quantity += quantity;
			}else{
				System.out.println("Not enough stock!");
			}
		}else{
			if(quantity<=p.getStock()){
				Item i = new Item(p,quantity);
				cart.put(i.id, i);
			}else{
				System.out.println("Not enough stock!");
			}
		}
	}
	void removeProduct(Product p){
		cart.remove(p.getId());
	}
	void printShoppingCart(){
		ArrayList<Item> products = new ArrayList<Item>(cart.values());
		if(products.size() == 0){
			System.out.println("Your cart is empty!");
		}
		for(Item i :products){
			System.out.println(i.toString());
		}
	}
	public class Item{
		int quantity;
		double price;
		Product product;
		String id;
		Item(Product p,int q){
			quantity = q;
			product = p;
			price = product.getPrice();
			id = product.getId();
		}
		@Override
		public String toString(){
			return "quantity: "+quantity+"\nProduct: "+product.toString();
		}
	}
	

}
