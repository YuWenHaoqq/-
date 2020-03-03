//package priv.wenhao.base.rocketmq;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
//import org.apache.rocketmq.client.consumer.listener.*;
//import org.apache.rocketmq.client.exception.MQClientException;
//import org.apache.rocketmq.client.producer.DefaultMQProducer;
//import org.apache.rocketmq.client.producer.TransactionMQProducer;
//import org.apache.rocketmq.common.message.MessageExt;
//import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Configuration
////@EnableConfigurationProperties(RocketMQProperties.class)
//@Slf4j
//public class RocketMQConfiguration {
//	@Resource
//	private RocketMQProperties rocketMQProperties;
//	//	事件监听
//	@Autowired
//	private ApplicationEventPublisher publisher = null;
//	private static boolean isFirstSub = true;
//	private static long startTime = System.currentTimeMillis();
//
//	/**
//	 * 容器初始化的时候,打印参数
//	 * 被@PostConstruct修饰的方法会在服务器加载Servle的时候运行,并且只会被服务器执行一次
//	 */
//	@PostConstruct
//	public void init() {
//		System.err.println(rocketMQProperties.getNamesrvAddr());
//		System.err.println(rocketMQProperties.getProducerGroupName());
//		System.err.println(rocketMQProperties.getConsumerBatchMaxSize());
//		System.err.println(rocketMQProperties.getConsumerGroupName());
//		System.err.println(rocketMQProperties.getConsumerInstanceName());
//		System.err.println(rocketMQProperties.getProducerInstanceName());
//		System.err.println(rocketMQProperties.getProducerTranInstanceName());
//		System.err.println(rocketMQProperties.getTransactionProducerGroupName());
//		System.err.println(rocketMQProperties.isConsumerBroadcasting());
//		System.err.println(rocketMQProperties.isEnableHistoryConsumer());
//		System.err.println(rocketMQProperties.isEnableOrderConsumer());
//		System.err.println(rocketMQProperties.getSubscribe().get(0));
//	}
//
//	/**
//	 * 创建普通消息发送者实例
//	 */
//	@Bean
//	public DefaultMQProducer defaultMQProducer() throws MQClientException {
//		DefaultMQProducer producer = new DefaultMQProducer(
//				rocketMQProperties.getProducerGroupName());
//		producer.setNamesrvAddr(rocketMQProperties.getNamesrvAddr());
//		producer.setInstanceName(rocketMQProperties.getProducerInstanceName());
//		producer.setVipChannelEnabled(false);
//		producer.setRetryTimesWhenSendAsyncFailed(10);
//		producer.start();
//		log.info("rocketmq producer server is starting...");
//		return producer;
//	}
//
//	/**
//	 * 创建支持消息事务发送的实例
//	 */
//	@Bean
//	public TransactionMQProducer transactionMQProducer() throws MQClientException {
//		TransactionMQProducer producer = new TransactionMQProducer(
//				rocketMQProperties.getTransactionProducerGroupName());
//		producer.setNamesrvAddr(rocketMQProperties.getNamesrvAddr());
//		producer.setInstanceName(rocketMQProperties.getProducerTranInstanceName());
//		producer.setRetryTimesWhenSendAsyncFailed(10);
////		事务回查最小并发数
//		producer.setCheckThreadPoolMinSize(2);
////		事务回查最大并发数
//		producer.setCheckThreadPoolMaxSize(2);
////		队列数
//		producer.setCheckRequestHoldMax(2000);
//		producer.start();
//		log.info("rocketmq transaction producer server is starting....");
//		return producer;
//	}
//
//	/**
//	 * 创建消息消费的实例
//	 */
//	@Bean
//	public DefaultMQPushConsumer pushConsumer() throws MQClientException {
//		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(
//				rocketMQProperties.getConsumerGroupName());
//		consumer.setNamesrvAddr(rocketMQProperties.getNamesrvAddr());
//		consumer.setInstanceName(rocketMQProperties.getConsumerInstanceName());
//
////		判断是否是广播模式
//		if (rocketMQProperties.isConsumerBroadcasting()) {
//			consumer.setMessageModel(MessageModel.BROADCASTING);
//		}
////		设置批量消费
//		consumer.setConsumeMessageBatchMaxSize(
//				rocketMQProperties.getConsumerBatchMaxSize() == 0 ? 1 : rocketMQProperties.getConsumerBatchMaxSize());
////		获取topic和tag
//		List<String> subscribeList = rocketMQProperties.getSubscribe();
//		for (String subscribe : subscribeList) {
//			consumer.subscribe(subscribe.split(":")[0], subscribe.split(":")[1]);
//		}
////		顺序消费
//		if (rocketMQProperties.isEnableOrderConsumer()) {
//			consumer.registerMessageListener(new MessageListenerOrderly() {
//				@Override
//				public ConsumeOrderlyStatus consumeMessage(
//						List<MessageExt> msgs, ConsumeOrderlyContext context) {
//					try {
//						context.setAutoCommit(true);
//						msgs = filterMessage(msgs);
//						if (msgs.size() == 0) {
//							return ConsumeOrderlyStatus.SUCCESS;
//						}
//						publisher.publishEvent(new MessageEvent(msgs,consumer));
//					} catch (Exception e) {
//						e.printStackTrace();
//						return ConsumeOrderlyStatus.SUCCESS;
//					}
//					return ConsumeOrderlyStatus.SUCCESS;
//				}
//			});
//
//		}else{
////			并发消费
//			consumer.registerMessageListener(new MessageListenerConcurrently() {
//				@Override
//				public ConsumeConcurrentlyStatus consumeMessage(
//						List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
//					try{
////						过滤消息
//						msgs=filterMessage(msgs);
//						if (msgs.size()==0){
//							return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//						}
//						publisher.publishEvent(new MessageEvent(msgs, consumer));
//					}catch (Exception e){
//						e.printStackTrace();
//						return ConsumeConcurrentlyStatus.RECONSUME_LATER;
//					}
//					return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//				}
//			});
//		}
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				try{
//					Thread.sleep(5000);
//					try{
//						consumer.start();
//					}catch (Exception e){
//						e.printStackTrace();
//					}
//					log.info("rocketmq consumer server is starting...");
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		}).start();
//		return consumer;
//	}
//
//	/***
//	 * ClassName:RocketMQConfiguration
//	 * Description: 消息过滤
//	 * param:[msgs]
//	 * return:java.util.List<org.apache.rocketmq.common.message.MessageExt>
//	 * Author:yu wenhao
//	 * date:2020/2/24
//	 */
//	private List<MessageExt> filterMessage(List<MessageExt> msgs) {
//		if (isFirstSub && !rocketMQProperties.isEnableHistoryConsumer()) {
//			msgs = msgs.stream()
//					.filter(item -> startTime - item.getBornTimestamp() < 0)
//					.collect(Collectors.toList());
//		}
//		if (isFirstSub && msgs.size() > 0) {
//			isFirstSub = false;
//		}
//		return msgs;
//	}
//}
