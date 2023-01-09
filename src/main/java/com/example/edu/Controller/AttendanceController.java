package com.example.edu.Controller;

import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.edu.AttendanceModelDto;
import com.example.edu.Service.AttendanceService;
import com.example.edu.Service.StudentService;

@Controller
public class AttendanceController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private AttendanceService attendanceService;

	@RequestMapping("/attendance")
	public String showAttendance(HttpSession session, HttpServletResponse response) {
		if ((session.getAttribute("user")) == null) {

			return "redirect:/staffloginview";
		}
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0);
		return "attendance";
	}

	@RequestMapping("/find")
	public String findAttendance(Model model, @RequestParam("timing") String a, 
			HttpSession session,HttpServletRequest req,HttpServletResponse response) {
		if ((session.getAttribute("user")) == null) {

			return "redirect:/staffloginview";
		}
		if (a.equals("null")) {
			return "redirect:/attendance";
		}
		
		String name = req.getParameter("timing");
		model.addAttribute("timing", name);
		model.addAttribute("get", studentService.forAttendance(a));
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0);
		return "attendance";
	}

	@RequestMapping("/saveattendance")
	public String saveAttendance(@ModelAttribute AttendanceModelDto form, Model model) {
		if (form.getAttendances() != null) {
			attendanceService.save(form.getAttendances());
			return "redirect:/attendance";
		}
		return "redirect:/attendance";
	}

	@RequestMapping("/view")
	public String view(HttpSession session, HttpServletResponse response) {
		if ((session.getAttribute("user")) == null) {

			return "redirect:/staffloginview";
		}
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0);
		return "viewAttendance";
	}

	@RequestMapping("/viewAttendance")
	public String viewAttendance(Model model, @RequestParam("timing") String a, @RequestParam("date") Date b,
			HttpSession session, HttpServletResponse response) {
		if ((session.getAttribute("user")) == null) {

			return "redirect:/staffloginview";
		}
		model.addAttribute("gett", attendanceService.view(a, b));
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0);
		return "viewAttendance";
	}
}
