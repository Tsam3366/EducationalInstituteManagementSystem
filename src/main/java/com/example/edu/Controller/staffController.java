package com.example.edu.Controller;

import java.sql.Date;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.edu.Model.Student;
import com.example.edu.Service.EmailSenderService;
import com.example.edu.Service.StudentService;

@Controller
public class staffController {
	@Autowired
	private StudentService studentService;

	@Autowired
	private EmailSenderService emailSenderService;

	@RequestMapping("/staffindex")
	public String staffindex(HttpSession session, HttpServletResponse response,Model model) {
		if ((session.getAttribute("user")) == null) {

			return "redirect:/staffloginview";
		}
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		model.addAttribute("c1",studentService.parichaya());
		model.addAttribute("c2",studentService.prathamik());
		model.addAttribute("c3",studentService.madhyama());
		model.addAttribute("c4",studentService.rashtrabhasha());
		model.addAttribute("c5",studentService.praveshika());
		model.addAttribute("c6",studentService.vp());
		model.addAttribute("c7",studentService.Vu());
		model.addAttribute("c8",studentService.Pp());
		model.addAttribute("c9",studentService.Pu());
		model.addAttribute("c11",studentService.eLower());
		model.addAttribute("c22",studentService.eHigher());
		model.addAttribute("c33",studentService.tlower());
		model.addAttribute("c44",studentService.tHigher());
		model.addAttribute("c55",studentService.coa());
		return "staffindex";
	}

	@RequestMapping("showregister")
	public String showStaffRegister(HttpSession session,HttpServletResponse response) {
		if ((session.getAttribute("user")) == null) {

			return "redirect:staffloginview";
		}
		response.setHeader("Cache-Control", " no-cache,no-store,must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		return "registrationstaff";
	}

	@PostMapping("/savestudentt")
	public ModelAndView saveStudd(Student stud, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		Student userexist = studentService.findbymail(stud.getEmail());

		if (userexist != null) {
			bindingResult.rejectValue("email", "error.student", "This mail is already registered by another student");
		}
		if (bindingResult.hasErrors()) {
			mav.addObject("failureMessage", "Use Another Email");
			mav.setViewName("registrationstaff");
		} else {
			studentService.saveStudent(stud);
			mav.addObject("successMessage", "User has been registered");
			mav.addObject("stud", new Student());
			mav.setViewName("registrationstaff");
		}
		return mav;
	}

	@RequestMapping("/getall")
	public String allStudent(Model model,HttpSession session,HttpServletResponse response) {
		if ((session.getAttribute("user")) == null) {

			return "redirect:/staffloginview";
		}
		model.addAttribute("count", studentService.findactivestud().size());
		model.addAttribute("getall", studentService.findactivestud());
		response.setHeader("Cache-Control", " no-cache,no-store,must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		return "allStudent";
	}

	@RequestMapping("/filterstud")
	public String filterStudent(Model model, @RequestParam String courses, @RequestParam String timing,HttpSession session
			,HttpServletResponse response) {
		if ((session.getAttribute("user")) == null) {

			return "redirect:/staffloginview";
		}
		if (courses.equals("null")) {
			return "redirect:/getall";
		}
		model.addAttribute("count", studentService.filter(courses, timing).size());
		model.addAttribute("getall", studentService.filter(courses, timing));
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0);
		return "allStudent";
	}

	@RequestMapping("/stafflogout")
	public String stafflogout(HttpSession session, HttpServletResponse response) {
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping("/managestud")
	public String manage(HttpSession session, HttpServletResponse response) {
		if ((session.getAttribute("user")) == null) {

			return "redirect:/staffloginview";
		}
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0);
		return "managestud";
	}

	@RequestMapping("/managefilter")
	public String manageStud(@RequestParam String courses, Model model,HttpSession session, HttpServletResponse response) {
		if ((session.getAttribute("user")) == null) {

			return "redirect:/staffloginview";
		}
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0);
		model.addAttribute("get", studentService.courseFilter(courses));
		return "managestud";
	}

	@RequestMapping("/managedelete/{id}")
	public String manageDelete(@PathVariable Long id) {
		studentService.del(id);
		return "managestud";
	}

	@RequestMapping("/manageedit/{id}")
	public String manageedit(@PathVariable Long id, Model model) {
		model.addAttribute("stud", studentService.getStudById(id));
		return "editStud";
	}

	@RequestMapping("/updatestud/{id}")
	public String updateStud(@PathVariable Long id, Model model, @ModelAttribute("stud") Student stud) {
		Student existing = studentService.getStudById(id);
		existing.setId(id);
		existing.setSname(stud.getSname());
		existing.setMname(stud.getMname());
		existing.setFname(stud.getFname());
		existing.setDob(stud.getDob());
		existing.setGender(stud.getGender());
		existing.setAdmissionDate(new Date(System.currentTimeMillis()));
		existing.setAddress(stud.getAddress());
		existing.setCourses(stud.getCourses());
		existing.setMobile(stud.getMobile());
		existing.setTiming(stud.getTiming());
		existing.setStatus(stud.getStatus());
		existing.setFees(stud.getFees());
		studentService.saveStudent(existing);
		return "redirect:/managestud";
	}

	@RequestMapping("/forgotpassword")
	public String forgetPass(@RequestParam String email, Model model) {
		if (email.equals("thiravisamraj@gmail.com")) {
			model.addAttribute("mail", "Password sent to your mail");
			emailSenderService.sendMail("thiravisamraj@gmail.com", "StarkSam Industries", "Your Password is admin");
			return "loginpage";
		} else {
			model.addAttribute("mail", "Please Enter Correct Mail");
			return "loginpage";
		}
	}

	@RequestMapping("/reject")
	public String rejectReq(@RequestParam Long id) {
		studentService.del(id);
		return "redirect:/getlist";
	}
}
