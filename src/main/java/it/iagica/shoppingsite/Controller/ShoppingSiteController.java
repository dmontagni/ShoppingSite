package it.iagica.shoppingsite.Controller;

import it.iagica.shoppingsite.Model.Item;
import it.iagica.shoppingsite.Model.Ordini;
import it.iagica.shoppingsite.Model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@org.springframework.stereotype.Controller
public class ShoppingSiteController {
    public static List<User> usersList = new ArrayList<User>();
    List<Item> prodottiCarrello = new ArrayList();
    List<Item> catalogo = getItems();

    @GetMapping("/")
    public static String showHome(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/register")
    public String showRegistration(Model model) {
        User users = new User();
        model.addAttribute("user", users);
        return "register";
    }

    @PostMapping("/register")
    public String salvaUser(@ModelAttribute User user, Model model) {
        usersList.add(user);
        if (user != null) {
            model.addAttribute("user", user);
            return "login";
        } else {
            return "saveError";
        }


    }

    //provvisiorio
    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model, Ordini ordini) {
        model.addAttribute("user", user);
        model.addAttribute("ordini", ordini);
        model.addAttribute("ordinis", ordinis());
        if (getUtente(usersList, user) != null) {
            model.addAttribute("user", user);
            return "home";
        } else {
            return "saveError";
        }
    }

    @GetMapping("/profiloPage")
    public String profilo(Model model) {
        User user = new User("nome1", "cognome1", "https://robohash.org/12545.png?size=100x100", "Via");
        model.addAttribute("user", user);
        return "profiloPage";
    }

    @PostMapping("/profiloPage")
    public String profiloAggiornato(@ModelAttribute User user, Model model) {
        //qui va inserita la modifica dell'user
        usersList.add(user);
        model.addAttribute("user", user);
        return "homepage";
    }

    @GetMapping("/homepage")
    public String homepage(Model model) {
        return "homepage";
    }

    // Metodo per analizzare la lista e cerca user se corrisponde con user all' interno della lista
    public User getUtente(List<User> usersList, User user) {
        for (User utente : usersList) {
            if (utente.getUsername().equals(user.getUsername()) && utente.getPassword().equals(user.getPassword())) {
                return utente;
            }
        }
        return null;
    }

    public List<Ordini> ordinis() {
        Date date = new Date(System.currentTimeMillis());
        Ordini ordine1 = new Ordini("cod_1", date, 22d);
        Ordini ordine2 = new Ordini("cod_2", date, 21d);
        Ordini ordine3 = new Ordini("cod_3", date, 23d);
        return Arrays.asList(ordine1, ordine2, ordine3);
    }

    @GetMapping("/order")
    public String showOrder(Model model) {
        Ordini ordini = new Ordini();
        model.addAttribute("ordini", ordini);
        model.addAttribute("ordinis", ordinis());
        return "order";
    }

    //questo controllo switcha da profilo a carrello aggiornando la lista di oggetti nella combo
    @GetMapping("/cart") //QUESTO RIEMPE LA COMBO BOX
    public String carrello(@ModelAttribute Item item, Model model) {
        model.addAttribute("catalogo", catalogo);
        model.addAttribute("item", new Item());
        return "cart";
    }


    @PostMapping(value = "/save")
    public String save(@ModelAttribute("item") Item item, Model model) {
        //oggetto.setName("mario");
        System.out.println(item);
        model.addAttribute("prodottiCarrello", inserisciOggetto(item)); //inserisce nella lista
        model.addAttribute("catalogo", catalogo); //questo riempie il combo box
        return "cart";
    }


    public List<Item> inserisciOggetto(Item oggetto) {
        for (Item item : catalogo) {
            if (item.getCode().equals(oggetto.getCode())) {
                prodottiCarrello.add(item);
            }
        }
        return prodottiCarrello;
    }

    private List<Item> getItems() {

        Item item1 = new Item(1, "0AB1NE", "COCOMERO", "cocomero estate", 3.5, "https://icons.iconarchive.com/icons/fi3ur/fruitsalad/64/watermelon-icon.png");
        Item item2 = new Item(2, "0545NA", "BANANA", "banana genovese", 5.5, "https://icons.iconarchive.com/icons/iconicon/veggies/64/bananas-icon.png");
        Item item3 = new Item(3, "BA998J", "MELONE", "melone indiano", 2.5, "https://icons.iconarchive.com/icons/google/noto-emoji-food-drink/64/32342-melon-icon.png");
        return Arrays.asList(item1, item2, item3);
    }

    @GetMapping("salvaOrdine")
    public String salvaOrdine(Model model) {
        //Order order = new Order();
        //questo metodo deve semplicemente aggiungere il carrello all'ENNESIMO ORDINE (DA CREARE NUOVO) SULLA LISTA ORDINI
        // ricordate che la lista ordini come il catalogo e il carrello saranno GLOBALI a livello di controller
        //ordinis.add(order);
        return "home";
    }
}
