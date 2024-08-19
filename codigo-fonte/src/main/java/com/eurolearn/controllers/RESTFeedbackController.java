package com.eurolearn.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eurolearn.dto.FeedbackDTO;
import com.eurolearn.services.FeedbackService;

@RestController
@RequestMapping("/restfeedbacks")
public class RESTFeedbackController {

	
	@Autowired
	public FeedbackService service;
	
	@GetMapping()
	public ResponseEntity<List<FeedbackDTO>> findAll(){
		List<FeedbackDTO> list = service.findAll();
		return ResponseEntity.ok(list);
	}
	
}
