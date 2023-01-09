package com.example.edu.Controller;

import java.sql.Date;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.edu.Model.Student;
import com.example.edu.Service.EmailSenderService;
import com.example.edu.Service.FeesService;
import com.example.edu.Service.NoticeBoardService;
import com.example.edu.Service.StudentService;

@Controller
public class MyController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private NoticeBoardService noticeBoardService;

	@Autowired
	private EmailSenderService emailSenderService;

	@Autowired
	private FeesService feesService;

	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("get", noticeBoardService.getAll());
		return "index";
	}

	@RequestMapping("/register")
	public ModelAndView register() {
		ModelAndView m = new ModelAndView();
		m.addObject("stud");
		m.setViewName("registrationstu");
		return m;
	}

	@PostMapping("/savestudent")
	public ModelAndView saveStud(Student stud, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		Student userexist = studentService.findbymail(stud.getEmail());

		if (userexist != null) {
			bindingResult.rejectValue("email", "error.student", "This mail is already registered by another student");
		}
		if (bindingResult.hasErrors()) {
			mav.addObject("failureMessage", "Use Another Email");
			mav.setViewName("registrationstu");
		} else {
			studentService.saveStudent(stud);
			mav.addObject("successMessage", "User has been registered");
			mav.addObject("stud", new Student());
			mav.setViewName("registrationstu");
		}
		return mav;
	}

	@RequestMapping("/viewlogin")
	public String viewLogin() {
		return "selectlogin";
	}

	@RequestMapping("/staffloginview")
	public String staffloginview() {
		return "loginpage";
	}

	@PostMapping("/stafflogin")
	public String staffLogin(@RequestParam(name = "staffuser") String a, @RequestParam(name = "staffpass") String b,
			Model model, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (("admin".equals(a)) && ("admin".equals(b))) {
			session.setAttribute("user", a);
			return "redirect:/staffindex";
		} else {
			model.addAttribute("ERROR", "Username or password not exist");
			return "loginpage";
		}
		
	}

	@RequestMapping("/getlist")
	public String getList(HttpServletRequest request, Model model, HttpServletResponse response,HttpSession session) {

		if ((session.getAttribute("user")) == null) {

			return "redirect:/staffloginview";
		}
		model.addAttribute("count", studentService.inactive().size());
		model.addAttribute("st", studentService.inactive());
		response.setHeader("Cache-Control", " no-cache,no-store,must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		return "staff";
	}
	@RequestMapping("studentloginview")
	public String studentloginview(Model model,HttpServletResponse response) {
		model.addAttribute("stud");
		return "studentlogin";
	}

	@PostMapping("/studlogin")
	public String login(@ModelAttribute("stud") Student stud, @RequestParam String email, Model model,
			@RequestParam Date dob, HttpSession session) {
		String status = studentService.findstatus(email);
		Student st = studentService.login(stud.getEmail(), stud.getDob());
		if ((Objects.nonNull(st)) && (status.equals("Active"))) {
			session.setAttribute("user", email);
			session.setAttribute("username", st.getSname());
			return "redirect:/studenthomee";
		} else {
			model.addAttribute("error2",
					"Username and Password does not exist,please wait untill staff will accept your request!!!");
			return "studentlogin";
		}

	}

	@RequestMapping("/studenthomee")
	public String studentHome(HttpSession session, HttpServletResponse response) {
		if ((session.getAttribute("user")) == null) {
			return "redirect:/studentloginview";
		} else {
			response.setHeader("Cache-Control", " no-cache,no-store,must-revalidate");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			return "studenthome";
		}
	}

	@RequestMapping("/active")
	public String login(@RequestParam Long id, @RequestParam String email) {
		emailSenderService.approvalMail(email, "Sam Educational Institue",
				"Your Request is accepted by staff...Welcome");
		studentService.changeStatus(id);
		return "redirect:/getlist";
	}

	@RequestMapping("/courses")
	public String courses() {
		return "courses";
	}

	@RequestMapping("/feesstructure")
	public String feestruct(Model model) {
		model.addAttribute("get", feesService.getStruct());
		return "feesstructure";
	}

	@RequestMapping("/about")
	public String about() {
		return "about";
	}
}
