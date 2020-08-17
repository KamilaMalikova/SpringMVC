package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import system.model.User;
import system.service.UserService;

import java.sql.SQLException;


@Controller
public class LogInController {
    @Autowired
    private UserService userService;

    @GetMapping(value = { "/" })
    public ModelAndView getIndexPage(ModelAndView modelAndView)
    {
//        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userForm", new User());
        modelAndView.setViewName("index");
        return modelAndView;

    }

    @PostMapping("/login")
    public @ResponseBody String checkUser(@ModelAttribute("userForm")User user) throws SQLException {
//        System.out.println(user);
//        if (userService.checkUser(user)){
//            return "valid";
//        }
//        else {
//            return "not valid";
//        }
        String result = Integer.toString(userService.insertUser(user));
        return result;
    }
}
