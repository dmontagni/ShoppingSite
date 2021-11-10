package it.iagica.shoppingsite.Model;

public class User {
    private String username;
    private String password;
    private String photo;
    private String address;
    //ricordiamoci sempre il costruttore vuoto necessario a Spring
    public User() {
    }

    public User(String username, String password, String photo, String address) {
        this.username = username;
        this.password = password;
        this.photo = photo;
        this.address = address;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    //ricordatevi di fare l’override del metodo toString()


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", photo='" + photo + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
