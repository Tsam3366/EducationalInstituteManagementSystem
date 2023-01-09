package com.example.edu.Controller;



import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpHeaders;

import com.example.edu.Model.Student;
import com.example.edu.Model.StudyMaterialModel;
import com.example.edu.Service.AttendanceService;
import com.example.edu.Service.EmailSenderService;
import com.example.edu.Service.StudentService;
import com.example.edu.Service.StudyMaterialService;

@Controller
public class StudentPageController {

	@Autowired
	private StudyMaterialService studyMaterialService;

	@Autowired
	private AttendanceService attendanceService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private EmailSenderService emailSenderService;
	
	@RequestMapping("/studentlogout")
	public String StudentLogout(HttpSession session,HttpServletResponse response) {
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping("/studymat")
	public String viewStudyMaterial(Model model,HttpSession session,HttpServletResponse response) {
		if(session.getAttribute("user")==null)
		{
			return "redirect:/studentloginview";
		}
		else {
		model.addAttribute("getall", studyMaterialService.getAllFiles());
		response.setHeader("Cache-Control", " no-cache,no-store,must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		return "studymat";
		}
	}

	@GetMapping("/dwnld/{id}")
	public ResponseEntity<byte[]> getFile(@PathVariable Long id,HttpSession session)
	{
		StudyMaterialModel fm=studyMaterialService.getbyid(id);
		HttpHeaders header=new HttpHeaders();
		header.add(HttpHeaders.CONTENT_TYPE,fm.getFileType());
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fm.getName());
		return ResponseEntity.ok()
				.headers(header).body(fm.getContent());
		
	}
	
	@RequestMapping("/studenthome")
	public String viewstudenthome(HttpSession session,HttpServletResponse response) {

		if(session.getAttribute("user")==null)
		{
			return "redirect:/studentloginview";
		}
		response.setHeader("Cache-Control", " no-cache,no-store,must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		return "studenthome";
	}
	
	@RequestMapping("/getstudattendance")
	public String getStudentAttendance(HttpSession session,Model model,HttpServletResponse response)
	{
		if(session.getAttribute("user")==null)
		{
			return "redirect:/studentloginview";
		}
		else {
		String email=(String)session.getAttribute("user");
		String name=(String)session.getAttribute("username");
		System.out.println(email);
		model.addAttribute("getlist", attendanceService.absent(email));
		response.setHeader("Cache-Control", " no-cache,no-store,must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		return "getattendance";
		}
	}
	@RequestMapping("/profileview")
	public String profileView(Model model,HttpSession session,HttpServletResponse response) {
		if(session.getAttribute("user")==null)
		{
			return "redirect:/studentloginview";
		}
		else {
		String email=(String)session.getAttribute("user");
		System.out.println(email);
		model.addAttribute("obj",studentService.findbymail(email));
		response.setHeader("Cache-Control", " no-cache,no-store,must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		return "studprofile";
		}
	}
	
		@RequestMapping("/fbk")
		public String feedbackView(Model model,HttpSession session,HttpServletResponse response)
		{
			if(session.getAttribute("user")==null)
			{
				return "redirect:/studentloginview";
			}
			else {
			String email=(String)session.getAttribute("user");
			System.out.println(email);
			model.addAttribute("obj",studentService.findbymail(email));
			response.setHeader("Cache-Control", " no-cache,no-store,must-revalidate");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			return "feed";
			}
		}
		@RequestMapping("/sendfeedback")
		public String sendFeedback(Model model,HttpSession session,HttpServletResponse response,@RequestParam String fback)
		{
			if(session.getAttribute("user")==null)
			{
				return "redirect:/studentloginview";
			}
			response.setHeader("Cache-Control", " no-cache,no-store,must-revalidate");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			String emaill=(String)session.getAttribute("user");
			emailSenderService.sendMail("thiravisamraj@gmail.com", emaill, fback);
			return "studenthome";
		}
	}


