package it.iagica.shoppingsite.Controller;

import it.iagica.shoppingsite.Model.Item;
import it.iagica.shoppingsite.Model.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@org.springframework.stereotype.Controller
public class ControllerMirko {

	List<Item> prodotti = new ArrayList();
	
	
    @GetMapping
    public String login(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("item", new Item());
        return "login";
    }

//    @PostMapping("/login") //sostituire con l'annotazione @GetMapping
//    public String greeting(@ModelAttribute User user,
//                           Model model) {
//        model.addAttribute("user", user);
//        return "result";
//    }
  
    
    
    //questo controllo switcha da profilo a carrello aggiornando la lista di oggetti nella combo
    @GetMapping("/cart") //QUESTO RIEMPE LA COMBO BOX
  public String carrello(@ModelAttribute Item item, Model model) {
    	 	model.addAttribute("items", getItem());
    	
        return "cart";
  }
    

  
    @PostMapping(value = "/save")
    public String save(@ModelAttribute("entryogg") Item oggetto, Model model) {
    	//oggetto.setName("mario");
    	System.out.println(oggetto.toString()); 
    	model.addAttribute("insertoggetto", inserisciOggetto(oggetto)); //inserisce nella lista
    	model.addAttribute("item",prodotti);
    	model.addAttribute("items", getItem()); //questo riempie il combo box
  
    
     return "cart";
    }
    
  
    
    
    public List<Item> inserisciOggetto(Item oggetto) {
    	 // prodotti.add(new Item(1,"0AB1NE","COCOMERO","cocomero estate",3.5,"https://icons.iconarchive.com/icons/fi3ur/fruitsalad/64/watermelon-icon.png"));
    	  prodotti.add(oggetto);
    	 return prodotti;
    }
    
	private List<Item> getItem(){
    	
        Item item1 = new Item(1,"0AB1NE","COCOMERO","cocomero estate",3.5,"https://icons.iconarchive.com/icons/fi3ur/fruitsalad/64/watermelon-icon.png");
        Item item2 = new Item(2,"0545NA","BANANA","banana genovese",5.5,"https://icons.iconarchive.com/icons/iconicon/veggies/64/bananas-icon.png");
        Item item3 = new Item(3,"BA998J","MELONE","melone indiano",2.5,"https://icons.iconarchive.com/icons/google/noto-emoji-food-drink/64/32342-melon-icon.png");
        return Arrays.asList(item1,item2,item3);
    }
    
//    @GetMapping("/")
//    public String home(Model model) {
//        model.addAttribute("user", new User());
//        return "login";
//    }
//    @PostMapping("/login")
//    public String login(@ModelAttribute User user, Model model) {
//        model.addAttribute("user", user);
//        return "result";
//    }





}
