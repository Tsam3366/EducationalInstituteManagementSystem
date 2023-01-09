package com.example.edu.Controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.edu.Service.FeesService;

@Controller
public class FeesStructureController 
{
	@Autowired
	private FeesService feesService;
	
	@RequestMapping("/viewfeesstructure")
	public String viewFeesStruc(Model model,HttpSession session, HttpServletResponse response) {
		if ((session.getAttribute("user")) == null) {

			return "redirect:/staffloginview";
		}
		model.addAttribute("get",feesService.getStruct());
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0);
		return "feesstruct";
	}
	
	@RequestMapping("/changefees")
	public String changeFees(@RequestParam Long id,@RequestParam String fees) {
		feesService.saveFees(id, fees);
		return"redirect:/viewfeesstructure";
	}
}
