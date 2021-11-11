package it.iagica.shoppingsite.Controller;

import it.iagica.shoppingsite.Model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;


@org.springframework.stereotype.Controller
public class SimpleController {
    public static List<User> usersList = new ArrayList<User>();
@GetMapping("/")
    public static String showHome(Model model){
    model.addAttribute("user", new User());
    return "login";
}
@GetMapping("/register")
    public String showRegistration(Model model){
    User users  = new User();
    model.addAttribute("user", users);
    return "register";
}

@PostMapping("/register")
    public String salvaUser(@ModelAttribute User user , Model model){
    usersList.add(user);
    if(user != null){
        model.addAttribute("user", user);
        return "login";
    } else{
        return "saveError";
    }


}
//provvisiorio
    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user , Model model){
        model.addAttribute("user",user);
        if(getUtente(usersList,user)!= null){
            model.addAttribute("user", user);
            return "homepage";
        } else{
            return "saveError";
        }
    }

    @GetMapping("/profilo")
    public String profilo(Model model){
        User user = new User("nome1","cognome1","https://robohash.org/12545.png?size=100x100","Via");
        model.addAttribute("user", user);
        return "profilo";
    }

    @PostMapping("/profilo")
    public String profiloAggiornato(@ModelAttribute User user, Model model){
        //qui va inserita la modifica dell'user
        usersList.add(user);
        model.addAttribute("user", user);
        return "homepage";
    }

    @GetMapping("/homepage")
    public String homepage(Model model){
        return "homepage";
    }
// Metodo per analizzare la lista e cerca user se corrisponde con user all' interno della lista
    public  User getUtente (List<User> usersList , User user){
        for (User utente: usersList) {
            if (utente.getUsername().equals(user.getUsername()) && utente.getPassword().equals(user.getPassword())){
                return utente;
            }
        }
        return null;
    }




}