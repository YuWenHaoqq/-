//package priv.wenhao.base.rocketmq;
//
//import org.apache.rocketmq.common.message.MessageExt;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
///**
// * ClassName: ConsumerService
// * Description: 建立mq消息消费者
// * Author: yuWenHao
// * Date: 2020/2/24
// */
//
//@Component
//public class ConsumerService {
////	可以设计成时间监听器
//	@EventListener(condition = "#event.msg[0].topic=='user-topic'&& #event.msg[0].tag=='white'")
//	public void rocketmqMsgListener(MessageEvent event){
//		try {
//			List<MessageExt> msgs=event.getMsgs();
//			for (MessageExt msg:msgs){
//				System.out.println("消费的消息"+new String(msg.getBody()));
//			}
//		}catch (Exception e){
//			e.printStackTrace();
//		}
//	}
//}
