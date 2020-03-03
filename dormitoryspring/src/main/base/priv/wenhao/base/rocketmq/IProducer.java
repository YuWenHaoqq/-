//package priv.wenhao.base.rocketmq;
//
//import org.apache.rocketmq.client.producer.SendCallback;
//import priv.wenhao.base.pojo.vo.MQEntity;
//
///**
// * ClassName: IProducer
// * Description: 消息生产者接口
// * Author: yuWenHao
// * Date: 2020/3/3
// */
//
//public interface IProducer {
//	/**
//	 * 同步发送MQ
//	 * @param topic
//	 * @param entity
//	 */
//	void send(String topic, MQEntity entity);
//
//	/**
//	 * 发送MQ,提供回调函数，超时时间默认3s
//	 * @param topic
//	 * @param entity
//	 * @param sendCallback
//	 */
//	void send( String topic, MQEntity entity, SendCallback sendCallback );
//
//	/**
//	 * 单向发送MQ，不等待服务器回应且没有回调函数触发，适用于某些耗时非常短，但对可靠性要求并不高的场景，例如日志收集。
//	 * @param topic
//	 * @param entity
//	 */
//	void sendOneway(String topic, MQEntity entity);
//}
