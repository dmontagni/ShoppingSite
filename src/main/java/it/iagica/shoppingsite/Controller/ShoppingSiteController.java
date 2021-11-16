package it.iagica.shoppingsite.Controller;


import it.iagica.shoppingsite.Model.Item;
import it.iagica.shoppingsite.Model.Orders;
import it.iagica.shoppingsite.Model.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@org.springframework.stereotype.Controller
public class ShoppingSiteController {
	UserManager userManager = new UserManager();
	Double importoTotale;
    List<Item> cartProducts = new ArrayList();
  //  List<Item> catalog = getItems();
    List<Orders> orders = new ArrayList<>();
    static int idOrderCode = 0;
    static String orderCode = "cod" + idOrderCode + 1;
    User userLogged;

    
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
        UserManager userManager = new UserManager();
        userManager.saveUser(user);

        if (user != null) {

            model.addAttribute("user", user);
            return "login";
        } else {
            return "saveError";
        }


    }

    //provvisiorio
    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        UserManager userManager = new UserManager();
//        User utenteDef = ;

        if (getUtente(userManager.getAllUsers(), user) != null) {
            User utenteDef = (getUtente(userManager.getAllUsers(), user));
            user.setId(utenteDef.getId());
            user.setAddress(utenteDef.getAddress());
            user.setPhoto(utenteDef.getPhoto());
            model.addAttribute("user", user);
            userLogged = user;
            return "home";
        } else {
            return "saveError";
        }
    }

    //accede alla pagina profilo dell'utente mettendo in mostra i dati con l'opzione di modifica
    @GetMapping("/profiloPage")
    public String profilo(Model model) {
        model.addAttribute("user", userLogged);
        return "profiloPage";
    }

    //ritorna alla home dalla pagina profilo con aggiornamento dati dell'utente
    @PostMapping("/profiloPage")
    public String profiloAggiornato(@ModelAttribute User user, Model model) {
        System.out.println(user);
        //gianpie questo ci serve per info chiama me o luca
        user.setId(userLogged.getId());
        userLogged = user;
        model.addAttribute("user", user);
        return "home";
    }

    //carica la pagina home
    @GetMapping("/home")
    public String homepage(Model model) {
        model.addAttribute("user", userLogged);
        return "home";
    }

    // Metodo per analizzare la lista e cerca user se corrisponde con user all' interno della lista
    public User getUtente(List<User> usersList, User user) {
        UserManager userManager = new UserManager();
        for (User utente : userManager.getAllUsers()) {
            if (utente.getUsername().equals(user.getUsername()) && utente.getPassword().equals(user.getPassword())) {
                System.out.println(utente.getId()+"-----------------------------");
                return utente;
            }
        }
        return null;
    }
    @GetMapping("/order")
    public String showOrder(Model model) {
        Orders orders = new Orders();
        UserManager userManager = new UserManager();
        System.out.println(userLogged);
        model.addAttribute("order", orders);
        //creo la lista degli ordini con id corrente

        model.addAttribute("orderList",userManager.getIdUtenteOrders(userLogged));
        return "order";
    }

    //questo controllo switcha da profilo a carrello aggiornando la lista di oggetti nella combo
    @GetMapping("/cart") //QUESTO RIEMPE LA COMBO BOX
    public String carrello(@ModelAttribute Item item, Model model) {
    	
        model.addAttribute("catalogo", userManager.getAllItems());
        model.addAttribute("item", new Item());
        return "cart";
    }


    @PostMapping(value = "/save")
    public String save(@ModelAttribute("item") Item item, Model model) {
    	Double sommatotale = 0.0;
        model.addAttribute("prodottiCarrello", inserisciOggetto(item)); //inserisce nella lista
        model.addAttribute("catalogo", userManager.getAllItems()); //questo riempie il combo box 
        for (Item item2 : cartProducts) {                            //questo aggiorna il prezzo totale del cart
        	sommatotale+= item2.getPrice();
	}
        model.addAttribute("prezzoTotale",sommatotale);					//questo aggiorna il prezzo totale del cart
       
        return "cart";
    }

    //questo controllo svuota il carrello
    @GetMapping("/clear") //QUESTO RIEMPE LA COMBO BOX
    public String clearCart(@ModelAttribute Item item, Model model) {
    	 model.addAttribute("clear", svuotaCarrello());	
        model.addAttribute("catalogo", userManager.getAllItems());
        model.addAttribute("item", new Item());
        return "cart";
    }
    
    public List<Item> svuotaCarrello() {
    	cartProducts.clear();
		return cartProducts;
    }

    public List<Item> inserisciOggetto(Item oggetto) {
        for (Item item : userManager.getAllItems()) {
            if (item.getCode().equals(oggetto.getCode())) {
                cartProducts.add(item);
                
            }
        }
       
        return cartProducts;
    }

    //private List<Item> getItems() {

        //Item item1 = new Item(1, "0AB1NE", "COCOMERO", "cocomero estate", 3.5, "https://icons.iconarchive.com/icons/fi3ur/fruitsalad/64/watermelon-icon.png");
        //Item item2 = new Item(2, "0545NA", "BANANA", "banana genovese", 5.5, "https://icons.iconarchive.com/icons/iconicon/veggies/64/bananas-icon.png");
        //Item item3 = new Item(3, "BA998J", "MELONE", "melone indiano", 2.5, "https://icons.iconarchive.com/icons/google/noto-emoji-food-drink/64/32342-melon-icon.png");
        //return Arrays.asList(item1, item2, item3);
    //}


    
    @GetMapping("/saveOrder")
    public String salvaOrdine(Model model) {
        model.addAttribute("user", userLogged);
        if (cartProducts.isEmpty()) {
        	return "saveError";
        }else {
        	model.addAttribute("user", userLogged);
        	Orders ord = new Orders();
    		Item prod = new Item();
    		Double sommatotale = 0d;
    		ord.setListaProdotti(userManager.getAllItems());
    		ord.setUser(userManager.getUser(userLogged.getUsername()));
    		 for (Item item : cartProducts) {
    			 sommatotale += item.getPrice();
             }
    		 
    		ord.setTotalPrice(sommatotale);
    		Date date = new Date(System.currentTimeMillis());
    		ord.setOrderDate(date);
    		userManager.saveOrder(ord);
    		cartProducts = new ArrayList<>();
          model.addAttribute("user", userLogged);
			return "home";
		}

        }


        //questo metodo deve semplicemente aggiungere il carrello all'ENNESIMO ORDINE (DA CREARE NUOVO) SULLA LISTA ORDINI
        // ricordate che la lista ordini come il catalogo e il carrello saranno GLOBALI a livello di controller
        //ordinis.add(order);
    
}
