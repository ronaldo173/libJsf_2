package ua.ronaldo173.library.web.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class LoginController {

    public LoginController() {
    }

    public String login() {
        return "books";
    }


}
