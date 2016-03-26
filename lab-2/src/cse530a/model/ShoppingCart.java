package cse530a.model;

import java.util.ArrayList;
import java.util.Date;

public class ShoppingCart {
    private Long userId;
    private Date modificationTimestamp;
    private ArrayList<ShoppingCartItem> items;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getModificationTimestamp() {
        return modificationTimestamp;
    }

    public void setModificationTimestamp(Date modificationTimestamp) {
        this.modificationTimestamp = modificationTimestamp;
    }

    public ArrayList<ShoppingCartItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<ShoppingCartItem> items) {
        this.items = items;
    }
}
