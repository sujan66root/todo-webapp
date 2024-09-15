package com.sujan.springboot.todowebapp.login;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private AuthenticateService authenticate;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String goToLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String goToWelcome(@RequestParam String username, @RequestParam String password, ModelMap map, HttpSession session) {
        if (authenticate.authenticate(username, password)) {
            map.put("username", username);
            map.put("password", password);
            session.setAttribute("username", username);
            return "redirect:/todos";
        } else {
            //error message to be displayed in the login page
            map.put("error", "Invalid username or password");
            return "login";        }
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate(); // Invalidate the session to log out the user
        return "logout"; // Redirect to the logout page
    }
}
