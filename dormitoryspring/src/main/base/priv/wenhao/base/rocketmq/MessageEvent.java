//package priv.wenhao.base.rocketmq;
//
//import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
//import org.apache.rocketmq.common.message.MessageExt;
//import org.springframework.context.ApplicationEvent;
//
//import java.util.List;
//
///**
// * ClassName: MessageEvent
// * Description: 创建对象监听器,监听对象
// * Author: yuWenHao
// * Date: 2020/2/23
// */
//
//public class MessageEvent extends ApplicationEvent {
//
//	private static final long serialVersionUID = -4468405250074063206L;
//
//	private DefaultMQPushConsumer consumer;
//	private List<MessageExt> msgs;
//
//	public MessageEvent(List<MessageExt> msgs, DefaultMQPushConsumer consumer) {
//		super(msgs);
//		this.consumer = consumer;
//		this.setMsgs(msgs);
//	}
//
//
//	public DefaultMQPushConsumer getConsumer() {
//		return consumer;
//	}
//
//	public void setConsumer(DefaultMQPushConsumer consumer) {
//		this.consumer = consumer;
//	}
//
//	public List<MessageExt> getMsgs() {
//		return msgs;
//	}
//
//	public void setMsgs(List<MessageExt> msgs) {
//		this.msgs = msgs;
//	}
//}
