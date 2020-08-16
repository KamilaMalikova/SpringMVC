package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import system.model.User;
import system.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public @ResponseBody
    List<User> getAllUsers(){
        return  userService.getAllUsers();
    }

    @GetMapping("/validate")
    public ModelAndView validateUser(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userFromServer", new User());
        modelAndView.setViewName("usersCheckPage");
        return modelAndView;
    }

    @PostMapping("/check")
    public @ResponseBody String checkUser(@ModelAttribute("userFromServer") User user){
       if("admin".equalsIgnoreCase(user.getName())  && "admin".equalsIgnoreCase(user.getPassword())) {
           return "valid";
       }else {
           return "not valid";
       }
    }
}
