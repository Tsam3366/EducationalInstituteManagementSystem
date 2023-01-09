package com.example.edu.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.edu.Model.NoticeModel;
import com.example.edu.Repository.NoticeRepository;

@Service
public class NoticeBoardService {

	@Autowired
	private NoticeRepository noticeRepository;
	
	public void saveNotice(NoticeModel noticeModel) {
		noticeRepository.save(noticeModel);
	}
	public List<NoticeModel> getAll()
	{
		return noticeRepository.findAll();
	}
	public void delbyid(Long id) {
		noticeRepository.deleteById(id);
	}
	public NoticeModel getbyid(Long id) {
		return noticeRepository.findById(id).get();
	}
}
