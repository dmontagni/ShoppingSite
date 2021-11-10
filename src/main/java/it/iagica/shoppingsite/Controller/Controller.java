package it.iagica.shoppingsite.Controller;

import it.iagica.shoppingsite.Model.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping
    public String login(Model model){
        model.addAttribute("user", new User());
        return "login";
    }
//    @PostMapping("/login") //sostituire con l’annotazione @GetMapping
//    public String greeting(@ModelAttribute User user,
//                           Model model) {
//        model.addAttribute("user", user);
//        return "result";
//    }
    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model) {
        String userName = "Mohamed";
        String password = "asd";
        if(userName.equals(user.getUsername()) && password.equals(user.getPassword())){
            model.addAttribute("user", user);
            model.addAttribute("users", getFakeUsers());
            return "result";
        } else{
            return "error";
        }
    }
    private List<User> getFakeUsers(){
        User user1 = new User("Daniele","123",getAvatar(),"via Roma");
        User user2 = new User("Mohamed","123",getAvatar(),"Piazza dei Consoli");
        User user3 = new User("Luca","123",getAvatar(),"via Crisalide");
        return Arrays.asList(user1,user2,user3);
    }
    private String getAvatar(){
        return "https://robohash.org/"+ new Random().nextInt() +".png?size=100x100";
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


