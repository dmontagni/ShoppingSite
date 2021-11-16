package it.iagica.shoppingsite.Model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "prodotti" /*,catalog = "iagica"*/)
public class Item {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_PROD", unique = true, nullable = false)
    private Integer code;
    @Column(name = "NOME_PROD")
    private String name;
    @Column(name = "PREZZO_PROD")
    private Double price;
    @Column(name = "QUANTITA_PROD")
    private Integer quantity;
    @Column(name = "IMG")
    private String img;
    @Column(name = "DESCRIZIONE_PROD")
    private String description;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USER")
    private User user;


    /*se non funziona potrebbe essere qui*/
    @ManyToMany
    @JoinColumn(name = "ID_ORD", nullable = false)
    private List<Orders> ordini;


    public Item() {
    }

    public Item(Integer code, String name, Double price, Integer quantity, String img, String description) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.img = img;
        this.description = description;
    }

    public Item(Integer code, String name, Double price, Integer quantity, String img, String description, User user, List<Orders> ordini) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.img = img;
        this.description = description;
        this.user = user;
        this.ordini = ordini;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Orders> getOrdini() {
        return ordini;
    }

    public void setOrdini(List<Orders> ordini) {
        this.ordini = ordini;
    }

	@Override
	public String toString() {
		return "Item [code=" + code + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", img=" + img
				+ ", description=" + description + ", user=" + user + ", ordini=" + ordini + "]";
	}


}
