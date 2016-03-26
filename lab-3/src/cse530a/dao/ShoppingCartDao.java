package cse530a.dao;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;

import cse530a.model.Book;
import cse530a.model.ShoppingCart;
import cse530a.model.ShoppingCartItem;

public class ShoppingCartDao {
    public static ShoppingCart createCart(Session session) {
    	ShoppingCart shoppingCart = new ShoppingCart();
    	shoppingCart.setModificationTimestamp(new Timestamp(System.currentTimeMillis()));
    	session.save(shoppingCart);
        return shoppingCart;
    }
    
    public static ShoppingCart retrieveCart(Session session, Long id) {
    	return (ShoppingCart) session.get(ShoppingCart.class, id);
    }
    
    public static void updateCart(Session session, ShoppingCart cart) {
    	cart.setModificationTimestamp(new Timestamp(System.currentTimeMillis()));
    	session.save(cart);
    }
    
    public static void deleteCart(Session session, ShoppingCart cart) {
       	session.delete(cart);
    }
    
    public static ShoppingCartItem createItem(Session session, ShoppingCart cart, Book book, Integer quantity) {
    	ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
    	shoppingCartItem.setShoppingCart(cart);
    	shoppingCartItem.setBook(book);
    	shoppingCartItem.setQuantity(quantity);
    	// add the new items to the list
    	List<ShoppingCartItem> items = cart.getItems();
    	items.add(shoppingCartItem);
    	// save changes
    	session.save(cart);
    	session.save(shoppingCartItem);
        return shoppingCartItem;
    }
    
    public static ShoppingCartItem retrieveItem(Session session, Long id) {
    	return (ShoppingCartItem) session.get(ShoppingCartItem.class, id);
    }
    
    public static void updateItem(Session session, ShoppingCartItem item) {
    	item.setQuantity(item.getQuantity() + 1);
    	session.save(item);
    }
    
    public static void deleteItem(Session session, ShoppingCart cart, ShoppingCartItem item) {
    	int quantity = item.getQuantity();
    	if (quantity > 1){
        	item.setQuantity(item.getQuantity() -1);
        	session.save(item);	
    	} else {
        	// delete the items from the list
        	List<ShoppingCartItem> items = cart.getItems();
        	items.remove(item);
        	// save changes
    		session.delete(item);	
    		
    	}
    }
}
