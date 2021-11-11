import java.util.ArrayList;

public class ShoppingCart{
	private ArrayList<Product> cart;
	
	public ShoppingCart(){
		cart = new ArrayList<Product>();
	}
	void addProduct(Product p){
		cart.add(p);
	}
	void removeProduct(Product p){
		cart.remove(p);
	}
	

}
