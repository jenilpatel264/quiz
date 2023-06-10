package com.chat.app.Service;

import com.chat.app.models.Message;

public interface messageService {
	
	public Message messageService(Message message);
	
	public Message getQuestion(Message message);
	public Message getSumCheck(Message message,int sum);

}
