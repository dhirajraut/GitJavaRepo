package JMS;

import javax.jms.*;
import javax.naming.*;
import java.io.*;
import java.io.InputStreamReader;
import java.util.Properties;
import javax.ejb.MessageDrivenBean;

public class Chat implements MessageListener, MessageDrivenBean{

	private TopicSession pubSession;
	private TopicSession subSession;
	private TopicConnection connection;
	private TopicPublisher publisher;
	private String userName;

	public Chat (final String topicName,
					final String userName,
					final String password) throws Exception{
		
		Properties properties = new Properties();
		
		InitialContext initialContext = new InitialContext(properties);
		
		TopicConnectionFactory conFactory = (TopicConnectionFactory)
			initialContext.lookup("TopicConnectionFactoryName");
		
		TopicConnection connection = conFactory.createTopicConnection(
				userName, password);
		
		pubSession = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		subSession = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		this.userName = userName;
		
		Topic topic = (Topic) initialContext.lookup(topicName);
		
		TopicPublisher publisher = pubSession.createPublisher(topic);
		TopicSubscriber subscriber = subSession.createSubscriber(topic);
		
		subscriber.setMessageListener(this);
		
		connection.start();
	}
	
	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage) message;
			String text = textMessage.getText();
			System.out.println(text);
		}
		catch (JMSException e) {
			e.printStackTrace();
		}
	}
	private void writeMessage (final String text) throws JMSException{
		TextMessage message = pubSession.createTextMessage();
		message.setText(userName + " : " + text);
		publisher.publish(message);
	}
	
	private void close() throws JMSException {
		connection.close();
	}
	
	
	public static void main (String args[]) {
		try {
			if (args.length < 3) {
				System.out.println("Topic/Username Missing");
			}
			else {
				Chat chat = new Chat (args[0], args[1], args[2]);
				
				BufferedReader command = new BufferedReader(new InputStreamReader(System.in));
				while (true) {
					String s = command.readLine();
					if (s.equalsIgnoreCase("exit")) {
						chat.close();
						System.exit(0);
					}
					else {
						chat.writeMessage(s);
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
