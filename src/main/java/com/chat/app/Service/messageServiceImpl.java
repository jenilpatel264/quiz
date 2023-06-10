package com.chat.app.Service;

import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chat.app.models.Message;

import ch.qos.logback.classic.Logger;

@Service
public class messageServiceImpl implements messageService {
	
	Logger logger=(Logger) LoggerFactory.getLogger(messageServiceImpl.class);

	Message orgMessage;

	@Override
	public Message messageService(Message message) {

		// checking whether message content has any numbers or not
		String messageForm = message.getContent();
		Boolean isNumberic = false;
		char[] chars = messageForm.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (char c : chars) {
			if (Character.isDigit(c)) {
				isNumberic = true;
				break;
			}
		}

		List<String> messageDigits;
		int sum = 0;
		// validate the sum logic
		if (messageForm.contains("answer")) {
			if (isNumberic) {
				messageForm = messageForm.replaceAll("[^0-9]+", " ");
				messageDigits = Arrays.asList(messageForm.trim().split(" "));

				for (int index = 0; index < messageDigits.size() - 1; index++) {
					int number = Integer.valueOf(messageDigits.get(index));
					sum += number;
				}
				int providedAns = Integer.valueOf(messageDigits.get(messageDigits.size() - 1));

				if (sum == providedAns) {
					message.setContent("That's great");
					return message;
				} else {
					message.setContent("That's wrong . please try again");
					return message;
				}

			}
		}

		// allocated random question to user
		if (!message.getContent().contains("provide me a question")) {
			message.setContent("Please Provide a right input for adding the numbers");
			return message;
		}

		List<String> questions = Arrays.asList(
				"Here you go , solve the problem Question :\"Print the sum of 9,5 and 3\" ",
				"Here you go , solve the problem Question :\"Print the sum of 10,2\" ",
				"Here you go , solve the problem Question :\"Print the sum of 5,7 and 1\" ");

		Random rand = new Random();
		message.setContent(questions.get(rand.nextInt(questions.size())));

		return message;
	}

	@Override
	public Message getQuestion(Message message) {
		if (message.getContent() == null) {
			message.setContent("Please Provide a valid input");
			logger.info("Get message for Null content " +message.getContent());
			return message;
		}
		// TODO Auto-generated method stub
		List<String> questions = Arrays.asList(
				"Here you go , solve the problem Question :\"Print the sum of 9,5 and 3\" ",
				"Here you go , solve the problem Question :\"Print the sum of 10,2\" ",
				"Here you go , solve the problem Question :\"Print the sum of 5,7 and 1\" ");

		Random rand = new Random();
		message.setContent(questions.get(rand.nextInt(questions.size())));
		orgMessage = message;
		logger.info("send response for addition questions " +message.getContent());
		return message;
	}

	@Override
	public Message getSumCheck(Message message, int sum) {

		if (orgMessage.getContent().equals("")) {
	
			message.setContent("Please select the Question first ");
			logger.info("send response previous request Null " +message.getContent());
			return message;
		}

		System.out.println(orgMessage.getContent());
		String originalContent = orgMessage.getContent();
		String messageForm = message.getContent();

		if (originalContent.equals(messageForm)) {

			Boolean isNumberic = false;
			char[] chars = messageForm.toCharArray();
			StringBuilder sb = new StringBuilder();
			for (char c : chars) {
				if (Character.isDigit(c)) {
					isNumberic = true;
					break;
				}
			}

			List<String> messageDigits;
			int sumvalue = 0;
			// validate the sum logic
			if (messageForm.contains("sum")) {
				if (isNumberic) {
					messageForm = messageForm.replaceAll("[^0-9]+", " ");
					messageDigits = Arrays.asList(messageForm.trim().split(" "));

					for (int index = 0; index < messageDigits.size(); index++) {
						int number = Integer.valueOf(messageDigits.get(index));
						System.out.println(number);
						sumvalue += number;
					}
					int providedAns = Integer.valueOf(messageDigits.get(messageDigits.size() - 1));

					if (sum == sumvalue) {
						logger.info("send response for sum is perfect " +message.getContent());
						message.setContent("That's great");
						return message;
					} else {
						logger.info("send response for sum is not perfect " +message.getContent());
						message.setContent("That's wrong . please try again");
						return message;
					}

				}
			}

		} else {
			logger.info("You have given an answer of wrong question " +message.getContent());
			message.setContent("You have given an answer of wrong question");
			return message;
		}
		return message;

	}

}
