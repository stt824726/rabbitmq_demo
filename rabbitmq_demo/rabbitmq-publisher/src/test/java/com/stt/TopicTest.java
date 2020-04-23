package com.stt;

import com.stt.topic.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicTest {

	@Autowired
	private TopicSender sender;

	@Test
	public void topic() throws Exception {
		for(int i = 0 ; i < 10000; i++){
			sender.send();
		}
	}

	@Test
	public void topic1() throws Exception {
		sender.send1();
		Thread.sleep(3000000);
	}

	@Test
	public void topic2() throws Exception {
		sender.send2();
	}

}