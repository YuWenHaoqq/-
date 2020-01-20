package priv.wenhao.test;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

public class Consumer {
	@Test
	public void consumerUse() throws Exception {
//		创建一个消息消费者,并设置一个消息消费组
		DefaultMQPushConsumer consumenr=new DefaultMQPushConsumer("niwei_consumer_group");
//		指定NameServer地址
		consumenr.setNamesrvAddr("localhost:9876");
//		设置Consumer第一次启动时从队列头部开始消费还是从队列尾部开始消费
		consumenr.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
//		订阅指定topic下的所有信息
		consumenr.subscribe("topic_example_java","*");

//		注册消息监听器
		consumenr.registerMessageListener(new MessageListenerConcurrently() {
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
//				默认list里只有一条消息,可以通过设置参数来批量接受消息
				if (list != null){
					for (MessageExt ext:list){
						try{
							System.out.println(new Date()+new String(ext.getBody(),"UTF-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					}
				}
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});
//		消费对象在使用前必须调用start初始化
		consumenr.start();
		System.out.println("消息消费者已启动");
	}
}
