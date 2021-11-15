package it.iagica.shoppingsite.Model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "utenti" /*,catalog = "iagica"*/)
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_USER", unique = true, nullable = false)
    private Integer id;

    @Column(name = "USERNAME_USER")
    private String username;

    @Column(name = "PASSWORD_USER")
    private String password;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "IMG_USER")
    private String photo = "https://robohash.org/11.png?size=100x100";


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idUser")
    private List<Orders> listaOrdini;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Item> prodotti;


    public User() {
        super();
    }


    public User(String username, String password, String email, String photo) {
        super();
        this.username = username;
        this.password = password;
        this.address = email;
        this.photo = photo;

    }


    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<Orders> getListaOrdini() {
        return listaOrdini;
    }


    public void setListaOrdini(List<Orders> listaOrdini) {
        this.listaOrdini = listaOrdini;
    }


    public List<Item> getProdotti() {
        return prodotti;
    }


    public void setProdotti(List<Item> prodotti) {
        this.prodotti = prodotti;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", photo='" + photo + '\'' +
                ", listaOrdini=" + listaOrdini +
                ", prodotti=" + prodotti +
                '}';
    }
}
