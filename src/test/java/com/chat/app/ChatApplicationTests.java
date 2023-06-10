package com.chat.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.chat.app.Service.messageService;
import com.chat.app.Service.messageServiceImpl;
import com.chat.app.models.Message;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.*;
import org.junit.Assert.*;
import nonapi.io.github.classgraph.utils.Assert;

@SpringBootTest
class ChatApplicationTests {
	@Autowired
	messageServiceImpl impl;

	@Test
	void contextLoads() {
	}

	@Test
	public void checkQuestion() {
		Message message = new Message();
		message.setName("jenil");
		message.setContent("Please give a question of addition");

		Message result = this.impl.getQuestion(message);

		assertTrue(result.getContent().contains("Here you go , solve the problem Question"));
	}

	@Test
	public void checkQuestionForNull() {
		Message message = new Message();
		message.setName("jenil");

		Message result = this.impl.getQuestion(message);

		assertFalse(result.getContent().contains("Please provisde a valid input"));
	}

//	@Test
//	public void prviousMessageContentCheck()
//	{
//		Message prevmessage=new Message();
//		prevmessage.setName("jenil");
//		prevmessage.setContent("Here you go , solve the problem Question :\\\"Print the sum of 9,5 and 3\\\" ");
//		
//		
//		Message currmessage=new Message();
//		currmessage.setName("jenil");
//		currmessage.setContent("Here you go , solve the problem Question :\\\"Print the sum of 9,5 and 3\\\" ");
//	
//		System.out.println(currmessage);
//		
//		Message result=this.impl.getSumCheck(currmessage, 17);
//		System.out.println("Msg "+result.getContent());
//		
//		assertTrue(result.getContent().equals("Please select the Question first"));
//	}
//	
	@Test
	public void MessageContentCheck() {
		Message orgMessage = new Message();
		orgMessage.setName("jenil");
		orgMessage.setContent("Here you go , solve the problem Question :\\\"Print the sum of 9,5 and 3\\\" ");

		System.out.println("dsadd");
		Message result = this.impl.getQuestion(orgMessage);
		result.setContent("Here you go , solve the problem Question :\\\"Print the sum of 9,5 and 3\\\" ");
		
		Message currmessage = new Message();
		currmessage.setName("jenil");
	
		currmessage.setContent(result.getContent());

	

		Message result2 = this.impl.getSumCheck(currmessage, 17);
	

		assertTrue(result2.getContent().equals("That's great"));
	}

	@Test
	public void MessageContentCheckForWrongAnswer() {
		Message orgMessage = new Message();
		orgMessage.setName("jenil");
		orgMessage.setContent("Here you go , solve the problem Question :\\\"Print the sum of 9,5 and 3\\\" ");

		
		Message result = this.impl.getQuestion(orgMessage);
		result.setContent("Here you go , solve the problem Question :\\\"Print the sum of 9,5 and 3\\\" ");
	
		Message currmessage = new Message();
		currmessage.setName("jenil");
	
		currmessage.setContent(result.getContent());

		

		Message result2 = this.impl.getSumCheck(currmessage, 16);
	

		assertTrue(result2.getContent().equals("That's wrong . please try again"));
	}

	@Test
	public void MessageContentCheckForWronqQuestion() {

		Message orgMessage = new Message();
		orgMessage.setName("jenil");
		orgMessage.setContent("Here you go , solve the problem Question :\\\"Print the sum of 9,5 and 3\\\" ");

		Message result = this.impl.getQuestion(orgMessage);
		result.setContent("");

		Message currmessage = new Message();
		currmessage.setName("jenil");

		currmessage.setContent("Here you go , solve the problem Question :\\\"Print the sum of 9,5 and 3\\\" ");

		Message result2 = this.impl.getSumCheck(currmessage, 16);

		assertTrue(result2.getContent().equals("Please select the Question first"));
	}

	@Test
	public void MessageContentCheckForNull() {

		Message orgMessage = new Message();
		orgMessage.setName("jenil");
		orgMessage.setContent("Here you go , solve the problem Question :\\\"Print the sum of 9,5 and 3\\\" ");

		Message result = this.impl.getQuestion(orgMessage);

		Message currmessage = new Message();
		currmessage.setName("jenil");

		currmessage.setContent("Here you go , solve the problem Question :\\\"Print the sum of 9,0 and 3\\\" ");

		Message result2 = this.impl.getSumCheck(currmessage, 16);

		assertTrue(result2.getContent().equals("You have given an answer of wrong question"));
	}

}
