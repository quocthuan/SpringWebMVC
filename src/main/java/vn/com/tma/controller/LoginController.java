package vn.com.tma.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "login";
	}

	/*
	 * @RequestMapping(value = "/loginError") public String loginError(ModelMap
	 * model) { model.addAttribute("error", "true"); return "loginError"; }
	 */

//	@RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
//	public String checkLogin(HttpServletRequest request, HttpServletResponse res) {
//		String name = request.getParameter("username");
//		String password = request.getParameter("password");
//
//		if (name.equals("admin") && password.equals("admin")) {
//			return "redirect:/contacts";
//		} else {
//			return "login";
//		}
//	}
}