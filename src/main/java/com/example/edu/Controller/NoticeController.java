package com.example.edu.Controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.edu.Model.NoticeModel;
import com.example.edu.Service.NoticeBoardService;

@Controller
public class NoticeController 
{
	@Autowired
	private NoticeBoardService boardService;
	
	@RequestMapping("/notice")
	public String viewNotice(Model model,HttpSession session, HttpServletResponse response) {
		if ((session.getAttribute("user")) == null) {

			return "redirect:/staffloginview";
		}
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0);
		model.addAttribute("get", boardService.getAll());
		return "notice";
	}
	@RequestMapping("/savenotice")
	public String view(NoticeModel notice)
	{
		boardService.saveNotice(notice);
		return "redirect:/notice";
	}
	@RequestMapping("/deletenotice/{id}")
	public String dlt(@PathVariable Long id)
	{
		boardService.delbyid(id);
		return"redirect:/notice";
	}
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Long id,Model model) {
		model.addAttribute("notice", boardService.getbyid(id));
		return "editnotice";
	}
	@RequestMapping("/updatenotice/{id}")
	public String upd(@PathVariable Long id,@ModelAttribute("notice") NoticeModel notice,Model model)
	{
		NoticeModel existing=boardService.getbyid(id);
		existing.setId(id);
		existing.setTitle(notice.getTitle());
		existing.setDetails(notice.getDetails());
		boardService.saveNotice(existing);
		return"redirect:/notice";
	}
}
