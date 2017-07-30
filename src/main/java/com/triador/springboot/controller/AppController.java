package com.triador.springboot.controller;

import com.triador.springboot.model.Address;
import com.triador.springboot.model.User;
import com.triador.springboot.service.AccountService;
import com.triador.springboot.service.AccountServiceImpl;
import com.triador.springboot.service.AddressService;
import com.triador.springboot.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AppController {

	@Autowired
	AccountService accountService;
	@Autowired
	UserService userService;
	@Autowired
	AddressService addressService;

	@RequestMapping("/")
	String home(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		User user = userService.findByUsername("admin");

		if (user == null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			user = new User();
			user.setPassword(DigestUtils.md5Hex("admin"));
			user.setActive(true);
			user.setBirthday(format.parse("1995-25-01"));
			user.setUsername("admin");
			user.setFirstname("admin");
			user.setLastname("admin");
			user.setParty("root");
			user.setCreatedTimestamp(new Date());
			user.setLastUpdatedTimestamp(new Date());
			user.setEmail("admin@mail.ru");

			Address address = new Address();
			address.setZip("default");
			address.setCity("default");
			address.setCountry("default");
			address.setDistrict("default");
			address.setStreet("default");

			user.setAddress(address);

			userService.saveUser(user);
		}

		user = accountService.getUserBySessionId(request.getSession().getId());
		if (user != null) {
			if ("root".equals(user.getParty()) || "admin".equals(user.getParty())) {
				response.sendRedirect("adminapi/user/");
			}
			else {
				response.sendRedirect("userapi/user/");
			}
			return null;
		}
		else {
			return "index";
		}
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	String signUpPage() {
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	void signup(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {

		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String party = request.getParameter("party");

		if ("root".equals(party) || "admin".equals(party)) {
			response.getWriter().write("У Вас нет прав!");
			response.getWriter().flush();
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

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

		User user = new User();
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

}
