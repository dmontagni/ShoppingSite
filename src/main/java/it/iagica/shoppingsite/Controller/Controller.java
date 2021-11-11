package it.iagica.shoppingsite.Controller;

import it.iagica.shoppingsite.Model.User;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;


@org.springframework.stereotype.Controller
public class Controller {
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
            return "profilo";
        } else{
            return "saveError";
        }
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
