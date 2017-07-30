package com.triador.springboot.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.triador.springboot.model.Address;
import com.triador.springboot.service.AccountService;
import com.triador.springboot.service.AddressService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.triador.springboot.model.User;
import com.triador.springboot.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/adminapi")
public class AdminController {

    @Autowired
    AccountService accountService;
    @Autowired
    UserService userService; //Service which will do all data retrieval/manipulation work
    @Autowired
    AddressService addressService;

    // -------------------Retrieve All Users---------------------------------------------

    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ModelAndView listAllUsers(HttpServletRequest request) {

        User user = accountService.getUserBySessionId(request.getSession().getId());
        if (user == null) return new ModelAndView("index");

        if (!"root".equals(user.getParty()) && !"admin".equals(user.getParty())) {
            return new ModelAndView("rightsError");
        }

        List<User> users = userService.findAllUsers();

        Map<String, Object> params = new HashMap<>();
        params.put("users", users);

        return new ModelAndView("admin", params);

    }

    // -------------------Retrieve Single User------------------------------------------

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public void getUser(@PathVariable("id") long id, HttpServletRequest request, HttpServletResponse response) throws IOException {

        User user = accountService.getUserBySessionId(request.getSession().getId());
        if (user == null) {
            response.sendRedirect("/");
            return;
        }

        if (!"root".equals(user.getParty()) && !"admin".equals(user.getParty())) {
            response.sendRedirect("/admin/error");
            return;
        }

        user = userService.findById(id);
        if (user == null) {
            response.getWriter().write("User with id " + id + " not exist.");
            response.getWriter().flush();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        response.getWriter().write("id = " + id + " username = " + user.getUsername());
        response.getWriter().flush();
        response.setStatus(HttpServletResponse.SC_OK);
    }

    // -------------------Create a User-------------------------------------------

    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public void createUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {

        User user = accountService.getUserBySessionId(request.getSession().getId());
        if (user == null) {
            response.sendRedirect("/");
            return;
        }

        if (!"root".equals(user.getParty()) && !"admin".equals(user.getParty())) {
            response.sendRedirect("/admin/error");
            return;
        }

        String username = request.getParameter("username").toLowerCase();
        String email = request.getParameter("email");

        if (userService.findByEmail(email) != null) {
            response.getWriter().write("User with this email already exist");
            response.getWriter().flush();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if (userService.findByUsername(username) != null) {
            response.getWriter().write("User with this login already exist");
            response.getWriter().flush();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        user = new User();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        user.setLastname(request.getParameter("lastname"));
        user.setFirstname(request.getParameter("firstname"));
        user.setEmail(request.getParameter("email"));
        user.setActive(true);
        user.setParty(request.getParameter("party"));
        user.setPassword(DigestUtils.md5Hex(request.getParameter("password")));
        user.setUsername(request.getParameter("username").toLowerCase());

        user.setCreatedTimestamp(new Date());
        user.setLastUpdatedTimestamp(new Date());
        user.setBirthday(format.parse(request.getParameter("birthday")));

        Address address = new Address();

        address.setZip(request.getParameter("zip"));
        address.setCity(request.getParameter("city"));
        address.setCountry(request.getParameter("country"));
        address.setDistrict(request.getParameter("district"));
        address.setStreet(request.getParameter("street"));

        user.setAddress(address);
        userService.saveUser(user);
        addressService.saveAddress(address);

        response.sendRedirect("/");


    }

    // ------------------- Update a User ------------------------------------------------

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public void updateUser(@PathVariable("id") long id, HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {

        User user = accountService.getUserBySessionId(request.getSession().getId());
        if (user == null) {
            response.sendRedirect("/");
            return;
        }

        if (!"root".equals(user.getParty()) && !"admin".equals(user.getParty())) {
            response.sendRedirect("/adminapi/error");
            return;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        user = userService.findById(id);

        if (user == null) {
            response.getWriter().write("Unable to update. User with id " + id + " not found.");
            response.getWriter().flush();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String username = request.getParameter("username").toLowerCase();
        String email = request.getParameter("email");

        if (!email.equals(userService.findById(id).getEmail())) {
            if (userService.findByEmail(email) != null) {
                response.getWriter().write("User with this email already exist");
                response.getWriter().flush();
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        }

        if (!username.equals(userService.findById(id).getUsername())) {
            if (userService.findByUsername(username) != null) {
                response.getWriter().write("User with this login already exist");
                response.getWriter().flush();
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        }

        user.setLastname(request.getParameter("lastname"));
        user.setFirstname(request.getParameter("firstname"));
        user.setEmail(request.getParameter("email"));
        user.setParty(request.getParameter("party"));
        user.setPassword(DigestUtils.md5Hex(request.getParameter("password")));
        user.setUsername(request.getParameter("username").toLowerCase());

        user.setLastUpdatedTimestamp(new Date());
        user.setBirthday(format.parse(request.getParameter("birthday")));

        Address address = addressService.findById(user.getAddress().getId());

        address.setZip(request.getParameter("zip"));
        address.setCity(request.getParameter("city"));
        address.setCountry(request.getParameter("country"));
        address.setDistrict(request.getParameter("district"));
        address.setStreet(request.getParameter("street"));

        user.setAddress(address);

        userService.updateUser(user);

        response.sendRedirect("/adminapi/user/");
    }

    // ------------------- Delete a User-----------------------------------------

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public void deleteUser(@PathVariable("id") long id, HttpServletRequest request, HttpServletResponse response) throws IOException {

        User user = accountService.getUserBySessionId(request.getSession().getId());
        if (user == null) {
            response.sendRedirect("/");
            return;
        }

        if (!"root".equals(user.getParty()) && !"admin".equals(user.getParty())) {
            response.sendRedirect("/adminapi/error");
            return;
        }

        user = userService.findById(id);
        if (user == null) {
            response.getWriter().write("Unable to delete. User with id " + id + " not found.");
            response.getWriter().flush();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        userService.deleteUserById(id);
        addressService.deleteAddressById(user.getAddress().getId());
        response.sendRedirect("/adminapi/user/");
    }

    // ------------------- Sign out-----------------------------

    @RequestMapping(value = "/signout", method = RequestMethod.POST)
    public ModelAndView signout(HttpServletRequest request) {
        String session = request.getSession().getId();
        User user = accountService.getUserBySessionId(session);

        accountService.deleteSession(request.getSession().getId());

        return new ModelAndView("index");
    }

    // ------------------- Sign in -------------------------------

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public void signin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String password = DigestUtils.md5Hex(request.getParameter("password"));
        String sessionId = request.getSession().getId();

        if (name == null || password == null || sessionId == null) {
            response.getWriter().write("Wrong login or password");
            response.getWriter().flush();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        User user = userService.findByUsername(name);

        if (user == null) {
            response.getWriter().write("Wrong login or password");
            response.getWriter().flush();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if (!user.getPassword().equals(password)) {
            response.sendRedirect("/");
            return;
        }

        accountService.addSession(sessionId, user);

        if ("root".equals(user.getParty()) || "admin".equals(user.getParty())) {
            response.sendRedirect("/adminapi/user/");
        } else {
            response.sendRedirect("/userapi/user/");
        }
    }

    // ------------------- Change status -----------------------------

    @RequestMapping(value = "/change/{id}", method = RequestMethod.POST)
    public void statusChange(@PathVariable("id") long id, HttpServletRequest request, HttpServletResponse response) throws IOException {

        User user = accountService.getUserBySessionId(request.getSession().getId());
        if (user == null) {
            response.sendRedirect("/");
            return;
        }

        if (!"root".equals(user.getParty()) && !"admin".equals(user.getParty())) {
            response.sendRedirect("/adminapi/error");
            return;
        }

        user = userService.findById(id);
        user.setActive(!user.getActive());
        user.setLastUpdatedTimestamp(new Date());
        userService.updateUser(user);

        response.sendRedirect("/adminapi/user/");
    }

    @RequestMapping(value = "/search/")
    public ModelAndView search(HttpServletRequest request) throws ParseException {

        User user = accountService.getUserBySessionId(request.getSession().getId());
        if (user == null) return new ModelAndView("index");

        if (!"root".equals(user.getParty()) && !"admin".equals(user.getParty())) {
            return new ModelAndView("rightsError");
        }

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
        params.put("users", users);

        return new ModelAndView("admin", params);
    }

    // ------------------- rightsError page ----------------------------

    @RequestMapping(value = "/error")
    public ModelAndView rightsError() {
        return new ModelAndView("rightsError");
    }

}