package it.iagica.shoppingsite.Model;

import java.sql.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ordini" /*,catalog = "iagica"*/)
public class Orders {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_ORD", unique = true, nullable = false)
    private Integer idOrders;
    @Column(name = "DATA_ORD")
    private Date orderDate;
    @Column(name = "IMPORTO_TOT_ORD")
    private Double totalPrice;
    @Column(name = "DATA_SPED")
    private Date shippingDate;


    @ManyToMany
    @JoinTable(
            name = "ordini_prodotti",
            joinColumns = @JoinColumn(name = "ID_ORD"),
            inverseJoinColumns = @JoinColumn(name = "ID_PROD")
    )


    private List<Item>listaProdotti;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USER", nullable = false)
    private User idUser;




    public Orders() {
        super();
    }


    public Orders(Integer idOrders, Date orderDate, Double totalPrice, Date shippingDate, User idUser) {
        this.idOrders = idOrders;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.shippingDate = shippingDate;
        this.idUser = idUser;
    }

    public Integer getIdOrders() {
        return idOrders;
    }

    public void setIdOrders(Integer idOrders) {
        this.idOrders = idOrders;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "idOrders=" + idOrders +
                ", orderDate=" + orderDate +
                ", totalPrice=" + totalPrice +
                ", shippingDate=" + shippingDate +
                ", idUser=" + idUser +
                '}';
    }
}
