package cse530a.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.sql.Timestamp;

import cse530a.model.Book;
import cse530a.model.ShoppingCart;
import cse530a.model.ShoppingCartItem;

public class ShoppingCartDao {
	public static ShoppingCart createCart(Connection conn, Long userId) {
		// FIXME
		return null;
	}
	
	private static final String DELETE_SHOPPINGCART_WITHETEMS = " delete from shopping_cart_items where cart_id = ? and isbn = ? ";
	private static final String UPDATE_SHOPPINGCART_WITHETEMS = " update shopping_cart_items set quantity = ? where cart_id = ? and isbn = ? ";
	private static final String INSERT_SHOPPINGCART_WITHETEMS = " insert into shopping_cart_items (cart_id,isbn,quantity) values (?,?,1) ";
	private static final String UPDATE_TIMESTAMP = " update shopping_carts set modification_timestamp = ? where user_id = ? ";
	
	public static void updateCart(Connection conn, Long userId, String isbn, String indicator) throws SQLException {

		PreparedStatement stmt = null;
		
		// 1. if we want to remove a book form shopping cart
		if(indicator.equals("delete")){
			try{
				// update the time stamp
				stmt = conn.prepareStatement(UPDATE_TIMESTAMP);
				stmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
				stmt.setLong(2, userId);
				stmt.executeUpdate();	
				// iterate through the cart and get the original quantity
				ArrayList<ShoppingCartItem> items = ShoppingCartDao.retrieveCart(conn, userId).getItems();
				Iterator<ShoppingCartItem> itr = items.iterator(); 
				int quantity = 0;
				while(itr.hasNext()) {
					ShoppingCartItem item= itr.next();
					if(item.getIsbn().equals(isbn)){
						quantity = item.getQuantity() - 1; 
					}
				}
				// if there is book quantity is decreased to 0 in the shopping cart
				if(quantity == 0){
					stmt = conn.prepareStatement(DELETE_SHOPPINGCART_WITHETEMS);
					stmt.setLong(1, userId);
					stmt.setString(2, isbn);
					stmt.executeUpdate();	
				}else {
					// if there is such book in the shopping cart, we decrease the quantity
					stmt = conn.prepareStatement(UPDATE_SHOPPINGCART_WITHETEMS);
					stmt.setInt(1, quantity);
					stmt.setLong(2, userId);
					stmt.setString(3, isbn);
					stmt.executeUpdate();	
				}
			}catch (SQLException e) {
				throw e;
			} finally {
				DatabaseManager.closeStatement(stmt);
			}
		// 2. if we want to update the quantity of the book or if there is no such book there previously
		} else if (indicator.equals("updateorcreate")){
			try{
				// update the time stamp
				stmt = conn.prepareStatement(UPDATE_TIMESTAMP);
				stmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
				stmt.setLong(2, userId);
				stmt.executeUpdate();	
				// iterate through the cart and get the original quantity
				ArrayList<ShoppingCartItem> items = ShoppingCartDao.retrieveCart(conn, userId).getItems();
				Iterator<ShoppingCartItem> itr = items.iterator(); 
				int quantity = 0;
				while(itr.hasNext()) {
					ShoppingCartItem item= itr.next();
					if(item.getIsbn().equals(isbn)){
						quantity = item.getQuantity() + 1; 
					}
				}
				// if there is no such book in the shopping cart
				if(quantity == 0){
					// if there is such no book in the shopping cart, we create one
					stmt = conn.prepareStatement(INSERT_SHOPPINGCART_WITHETEMS);
					stmt.setLong(1, userId);
					stmt.setString(2, isbn);
					stmt.executeUpdate();	
				} else {
					// if there is such book in the shopping cart
					stmt = conn.prepareStatement(UPDATE_SHOPPINGCART_WITHETEMS);
					stmt.setInt(1, quantity);
					stmt.setLong(2, userId);
					stmt.setString(3, isbn);
					stmt.executeUpdate();	
				}
			}catch (SQLException e) {
				throw e;
			} finally {
				DatabaseManager.closeStatement(stmt);
			}
		} 

	}


	public static void deleteCart(Connection conn, ShoppingCart cart) throws SQLException {

	}

	private static final String RETRIEVE_SHOPPINGCART_WITHETEMS = " select cart_id, isbn, quantity from shopping_cart_items where cart_id = ? order by isbn";
	private static final String RETRIEVE_TIMESTAMP_IN_SHOPPINGCART = " select modification_timestamp from shopping_carts where user_id = ? ";

	public static ShoppingCart retrieveCart(Connection conn, Long userId) throws SQLException{
		ArrayList<ShoppingCartItem> shoppingCartItemsList = new ArrayList<ShoppingCartItem>();
		ShoppingCart shoppingCart = new ShoppingCart();

		PreparedStatement stmt = null;
		ResultSet rs = null;

		// Retrieve the items for the cart
		try {
			// Retrieve the items for the cart
			stmt = conn.prepareStatement(RETRIEVE_SHOPPINGCART_WITHETEMS);
			stmt.setLong(1, userId);
			rs = stmt.executeQuery();

			while (rs.next()) {
				ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
				// set the attribute of shopping cart items 
				shoppingCartItem.setCartId(rs.getLong(1));
				shoppingCartItem.setIsbn(rs.getString(2));
				shoppingCartItem.setQuantity(rs.getInt(3));
				// set the attribute of books of the shopping cart items
				Book book = BookDao.retrieveBook(conn, shoppingCartItem.getIsbn());
				shoppingCartItem.setBook(book);
				// Add shoppingCartItem to an ArrayList
				shoppingCartItemsList.add(shoppingCartItem);
			}
			// set the items found to the shopping cart
			shoppingCart.setItems(shoppingCartItemsList); 
		} catch (SQLException e) {
			throw e;
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closeStatement(stmt);
		}
		
		// Retrieve the modification time stamp for the cart
		try {
			stmt = conn.prepareStatement(RETRIEVE_TIMESTAMP_IN_SHOPPINGCART);
			stmt.setLong(1, userId);
			rs = stmt.executeQuery();
			if (rs.next()) {
				Timestamp t = rs.getTimestamp(1);
				shoppingCart.setModificationTimestamp(new Date(t.getTime()));
				System.out.println("The last modification time is "+new Date(t.getTime()));
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closeStatement(stmt);
		}
		
		return shoppingCart;
	}

}
