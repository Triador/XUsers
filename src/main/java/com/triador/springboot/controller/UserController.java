package com.triador.springboot.controller;

import com.triador.springboot.service.AccountService;
import com.triador.springboot.model.User;
import com.triador.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/userapi")
public class UserController {

    @Autowired
    AccountService accountService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ModelAndView listAllUsers(HttpServletRequest request) {

        User user = accountService.getUserBySessionId(request.getSession().getId());
        if (user == null) return new ModelAndView("index");

        List<User> users = userService.findAllUsers();
        users.remove(userService.findByUsername("admin"));
        Map<String, Object> params = new HashMap<>();
        params.put("users", users);

        return new ModelAndView("user", params);

    }

    @RequestMapping(value = "/search/")
    public ModelAndView search(HttpServletRequest request) throws ParseException {

        User user = accountService.getUserBySessionId(request.getSession().getId());
        if (user == null) return new ModelAndView("index");

        String searchBy = request.getParameter("by");
        String subject = request.getParameter("subject");
        Set<User> users = new HashSet<>();

        switch (searchBy) {
            case "name":
                users.addAll(userService.findByFirstnameContainingIgnoreCase(subject));
                users.addAll(userService.findByLastnameContainingIgnoreCase(subject));
                break;
            case "birthday":
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                users.addAll(userService.findAllByBirthday(format.parse(subject)));
                break;
            case "email":
                users.addAll(userService.findAllByEmail(subject));
                break;
            default:
                users.addAll(userService.findAllUsers());
        }

        Map<String, Object> params = new HashMap<>();
        user = userService.findByUsername("admin");
        users.remove(user);
        params.put("users", users);

        return new ModelAndView("user", params);
    }
}
