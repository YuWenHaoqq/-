package priv.wenhao.dormitory.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "rocketMQ消息生产者")
@RestController
@RequestMapping("rocketMQ")
public class RocketMQController {
//	@Autowired
//	private DefaultMQProducer defaultMQProducer;
//
//	@Autowired
//	private TransactionMQProducer transactionMQProducer;
//
//	/**
//	 * ClassName:RocketMQController
//	 * Description: 发送普通消息
//	 * param:[]
//	 * return:void
//	 * Author:yu wenhao
//	 * date:2020/2/24
//	 */
//	@ApiOperation(value = "发送普通消息", httpMethod = "GET")
//	@GetMapping("/sendMsg")
//	public void sendMsg() {
//		for (int i = 0; i < 100; i++) {
//			UserQuery userQuery = new UserQuery();
//			userQuery.setId(String.valueOf(i));
//			userQuery.setUserName("jhp" + i);
//			String json = JSON.toJSONString(userQuery);
//			Message message = new Message("user-topic", "white", json.getBytes());
//			try {
//				SendResult sendResult = defaultMQProducer.send(message);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	/***
//	 * ClassName:RocketMQController
//	 * Description: 发送事务消息
//	 * param:[]
//	 * return:java.lang.String
//	 * Author:yu wenhao
//	 * date:2020/2/24
//	 */
//	@ApiOperation(value = "发送事务消息发送",httpMethod = "GET")
//	@GetMapping("/sendTransactionMsg")
//	public String sendTransactionMsg() {
//		SendResult sendResult = null;
//		try {
////			a,b,c三个值对应三个不同的状态
//			String ms = "c";
//			Message msg = new Message("user-topic", "white", ms.getBytes());
////			发送事务消息
//			sendResult = transactionMQProducer.sendMessageInTransaction(msg, (Message msg1, Object arg) -> {
//				String value = "";
//				if (arg instanceof String) {
//					value = (String) arg;
//				}
//				if (value==""){
//					throw new RuntimeException("发送消息不能为空");
//				}else if (value=="a"){
//					return LocalTransactionState.ROLLBACK_MESSAGE;
//				} else if (value == "b") {
//					return LocalTransactionState.COMMIT_MESSAGE;
//				}
//				return LocalTransactionState.ROLLBACK_MESSAGE;
//			},4);
//			System.out.println(sendResult);
//		}catch (Exception e){
//			e.printStackTrace();
//		}
//
//		return sendResult.toString();
//	}
//
//	/***
//	* ClassName:RocketMQController
//	* Description: 支持顺序发送消息
//	* param:[]
//	* return:void
//	* Author:yu wenhao
//	* date:2020/2/24
//	*/
//	@ApiOperation(value = "支持顺序发生消息",httpMethod = "GET")
//	@GetMapping("/sendMessOrder")
//	public void sendMsgOrder(){
//		for (int i=0;i<100;i++){
//			UserQuery userQuery=new UserQuery();
//			userQuery.setId(String.valueOf(i));
//			userQuery.setUserName("jhp"+i);
//			String json=JSON.toJSONString(userQuery);
//			Message msg=new Message("user-topic","white",json.getBytes());
//			try{
//				SendResult sendResult=defaultMQProducer.send(msg, new MessageQueueSelector() {
//					@Override
//					public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
//						int index=((Integer)arg)%mqs.size();
//						return mqs.get(index);
//					}
//				},i);
//				System.out.println("消息id:"+sendResult.getMsgId()+":"+","+"发送状态"+sendResult.getSendStatus());
//			}catch (Exception e){
//				e.printStackTrace();
//			}
//		}
//
//	}
}
