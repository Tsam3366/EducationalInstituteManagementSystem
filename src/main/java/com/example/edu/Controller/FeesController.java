package com.example.edu.Controller;

import java.sql.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.edu.Service.EmailSenderService;
import com.example.edu.Service.FeesService;
import com.example.edu.Service.StudentService;

@Controller
public class FeesController {

	@Autowired
	private FeesService feesservice;
	@Autowired
	private EmailSenderService emailSenderService;

	@Autowired
	private StudentService studentService;

	@RequestMapping("/feesview")
	public String feesView(HttpSession session, HttpServletResponse response) {
		if ((session.getAttribute("user")) == null) {

			return "redirect:/staffloginview";
		}
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0);
		return "fees";
	}

	@RequestMapping("/feesfind")
	public String feesFind(@RequestParam("timing") String timing, @RequestParam("fees") String fees, Model model) {

		if (fees.equals("Unpaid")) {
			model.addAttribute("get", feesservice.filter(fees, timing));
			return "fees";
		} else {
			model.addAttribute("get", feesservice.filter(fees, timing));
			return "feesunpaid";
		}
	}

	@RequestMapping("/change")
	public String changeFess(@RequestParam("id") Long id, @RequestParam String email) {
		feesservice.updatefees(id);
		Date d = new Date(System.currentTimeMillis());
		emailSenderService.feesMail(email, "Sam Educational Institue", "Fees Paid");
		studentService.changeStatus(id);
		return "fees";
	}
}
