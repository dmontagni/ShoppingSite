package it.iagica.shoppingsite.Model;

import java.sql.Date;
import java.util.List;

public class Ordini {
    private Integer idOrdini;
    private String code;
    private Date orderDate;
    private List<Item> items;
    private Date shippingDate;
    private Double totalPrice;

    public Ordini() {
    }

    public Integer getIdOrdini() {
        return idOrdini;
    }

    public void setIdOrdini(Integer idOrdini) {
        this.idOrdini = idOrdini;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Date getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Ordini{" +
                "idOrdini=" + idOrdini +
                ", code='" + code + '\'' +
                ", orderDate=" + orderDate +
                ", items=" + items +
                ", shippingDate=" + shippingDate +
                ", totalPrice=" + totalPrice +
                '}';
    }
}