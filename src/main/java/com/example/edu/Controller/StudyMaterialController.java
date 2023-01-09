package com.example.edu.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.edu.Model.StudyMaterialModel;
import com.example.edu.Service.StudyMaterialService;

@Controller
public class StudyMaterialController {
	@Autowired
	private StudyMaterialService studyMaterialService;

	@RequestMapping("/studymaterial")
	public String studymaterial(Model model,HttpSession session, HttpServletResponse response) {
		if ((session.getAttribute("user")) == null) {

			return "redirect:/staffloginview";
		}
		model.addAttribute("allFiles", studyMaterialService.getAllFiles());
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0);
		return "book";
	}

	@PostMapping("/savebooks")
	public String fileUpload(@RequestParam("name") MultipartFile file,Model model,@RequestParam String course)throws IOException
	{
		byte[] content= file.getBytes();
		String name=file.getOriginalFilename();
		String fileType=file.getContentType();
		StudyMaterialModel filemodel=new StudyMaterialModel(content,name,fileType,course);
		studyMaterialService.saveFile(filemodel);
		return"redirect:/studymaterial";
	}
	
	@RequestMapping("/dlt/{id}")
	public String deleteBooks(@PathVariable Long id, Model model) {
		studyMaterialService.dlt(id);
		return "redirect:/studymaterial";
	}
}
