package cse530a.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "shopping_carts")
@SequenceGenerator(name = "SHOPPING_CART_SEQ", sequenceName = "shopping_carts_cart_id_seq")
public class ShoppingCart implements Serializable {
    
	private static final long serialVersionUID = -3868107902396215130L;
	
	private Long id;
    private Date modificationTimestamp;
    private List<ShoppingCartItem> items;

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SHOPPING_CART_SEQ")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "modification_timestamp")
    public Date getModificationTimestamp() {
        return modificationTimestamp;
    }

    public void setModificationTimestamp(Date modificationTimestamp) {
        this.modificationTimestamp = modificationTimestamp;
    }

    @OneToMany (mappedBy = "shoppingCart")
    public List<ShoppingCartItem> getItems() {
        return items;
    }

    public void setItems(List<ShoppingCartItem> items) {
        this.items = items;
    }
}
