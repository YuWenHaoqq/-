//package priv.wenhao.base.rocketmq;
//
//
//import lombok.Data;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//
//import java.util.List;
//
///**
// * ClassName: RocketMQProperties
// * Description: 读取配置文件信息
// * Author: yuWenHao
// * Date: 2020/2/14
// */
//
//@Configuration
//@PropertySource(value = {"classpath:config/rocketmq.properties"})
//@Data
//public class RocketMQProperties {
//
//	@Value("${rocketmq.namesrvAddr}")
//	private String namesrvAddr;
//	@Value("${rocketmq.producerGroupName}")
//	private String producerGroupName;
//	@Value("${rocketmq.transactionProducerGroupName}")
//	private String transactionProducerGroupName;
//	@Value("${rocketmq.consumerGroupName}")
//	private String consumerGroupName;
//	@Value("${rocketmq.producerInstanceName}")
//	private String producerInstanceName;
//	@Value("${rocketmq.consumerInstanceName}")
//	private String consumerInstanceName;
//	@Value("${rocketmq.producerTranInstanceName}")
//	private String producerTranInstanceName;
//	@Value("${rocketmq.consumerBatchMaxSize}")
//	private int consumerBatchMaxSize;
//	@Value("${rocketmq.consumerBroadcasting}")
//	private boolean consumerBroadcasting;
//	@Value("${rocketmq.enableHistoryConsumer}")
//	private boolean enableHistoryConsumer;
//	@Value("${rocketmq.enableOrderConsumer}")
//	private boolean enableOrderConsumer;
//	@Value("${rocketmq.subscribe}")
//	private List<String> subscribe;
//}
