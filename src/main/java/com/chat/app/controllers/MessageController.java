package com.chat.app.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chat.app.Service.messageService;
import com.chat.app.models.Message;

@RestController
public class MessageController {
	
	@Autowired messageService messageService;
	
	@MessageMapping("/message")
	@SendTo("/topic/return-to")
	public Message getContent(@RequestBody Message message) throws InterruptedException
	{	
		
		return this.messageService.messageService(message);
	}
	Message messageOrg;
	
	@PostMapping("/question")
	public ResponseEntity<Message> getQuestion(@RequestBody Message message)
	{
		if(message.getContent().equals(""))
		{
			message.setContent("Please give a valid input");
			return new ResponseEntity<>(messageOrg,HttpStatus.BAD_REQUEST);
		}
		messageOrg=this.messageService.getQuestion(message);
		return new ResponseEntity<>(messageOrg,HttpStatus.OK);
	}
	
	@PostMapping("sum/{sum}")
	public ResponseEntity<Message> getSum(@RequestBody Message message,@PathVariable Integer sum)
	{
		
		messageOrg=this.messageService.getSumCheck(message, sum);
		
		if(messageOrg.getContent().contains("That's great"))
		{
			return new ResponseEntity<>(messageOrg,HttpStatus.OK);
		}
		else 
		{
			return new ResponseEntity<>(messageOrg,HttpStatus.BAD_REQUEST);
		}
		
	}
	

}
