package priv.wenhao.base.config;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.codec.CompressionCodec;
import io.lettuce.core.codec.RedisCodec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Configuration
@EnableAutoConfiguration
@Slf4j
public class RedisConfig {
	@Value("${redis.database.login}")
	private int loginDataBase;

	@Value(("${redis.database.token}"))
	private int tokenDataBase;

	@Value(("${redis.database.third}"))
	private int thirdDataBase;

	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private int port;

	@Value("${spring.redis.password}")
	private String password;

	@Value("${spring.redis.timeout}")
	private long timeout;

	/***
	* ClassName:RedisConfig
	* Description:防止表单重复提交
	* param:[]
	* return:io.lettuce.core.api.sync.RedisCommands<java.lang.String,java.lang.String>
	* Author:yu wenhao
	* date:2019/12/31
	*/
//	@Bean(name = "zeroTemplate")
//	public RedisCommands<String,String> getZeroTemplate(){
//
//	}


	/***
	* Description:用于存放登录的token
	* param:[]
	* return:io.lettuce.core.api.sync.RedisCommands<java.lang.String,java.lang.String>
	* Author:yu wenhao
	* date:2019/12/20
	*/
	@Bean(name = "firstTemplate")
	public RedisCommands<String, String> getFirstTemplate() {

		RedisURI redisURI = RedisURI.builder()
				.withHost(host)
				.withPort(port)
				.withPassword(password)
				.withDatabase(loginDataBase)
				.withTimeout(Duration.of(timeout, ChronoUnit.SECONDS))
				.build();

//		创建客户端
		RedisClient redisClient = RedisClient.create(redisURI);
		StatefulRedisConnection<String, String> connection = redisClient.connect();;
		log.info("redis数据库1连接成功");

//		创建同步命令
		return connection.sync();
	}
	/***
	* ClassName:RedisConfig
	* Description: 放在第三个redis库中
	* param:[]
	* return:io.lettuce.core.api.sync.RedisCommands<java.lang.String,java.lang.String>
	* Author:yu wenhao
	* date:2020/3/9
	*/
	@Bean(name = "thirdTemplate")
	public RedisCommands<String, String> getThirdTemplate() {
		RedisURI redisURI = RedisURI.builder()
				.withHost(host)
				.withPort(port)
				.withPassword(password)
				.withDatabase(thirdDataBase)
				.withTimeout(Duration.of(timeout, ChronoUnit.SECONDS))
				.build();

//		创建客户端
		RedisClient redisClient = RedisClient.create(redisURI);
		StatefulRedisConnection<String, String> connection = redisClient.connect();;
		log.info("redis数据库3连接成功");

//		创建同步命令
		return connection.sync();
	}
	/***
	* ClassName:RedisConfig
	* Description:用于存放权限,obj为list
	* param:[]
	* return:io.lettuce.core.api.sync.RedisCommands<java.lang.String,java.lang.Object>
	* Author:yu wenhao
	* date:2019/12/31
	*/
	@Bean(name = "secondTemplate")
	public RedisCommands<String, Object> getSecondTemplate() {
		RedisURI redisURI = RedisURI.builder()
				.withHost(host)
				.withPort(port)
				.withPassword(password)
				.withDatabase(tokenDataBase)
				.withTimeout(Duration.of(timeout, ChronoUnit.SECONDS))
				.build();
//		创建客户端
		RedisClient redisClient = RedisClient.create(redisURI);
		StatefulRedisConnection<String, Object> connection = redisClient.connect(CompressionCodec.valueCompressor(
				new SerializedObjectCodec(),CompressionCodec.CompressionType.GZIP));
		log.info("redis数据库2连接成功");

//		创建线程安全的连接
		return connection.sync();
	}

	class SerializedObjectCodec implements RedisCodec<String,Object> {
		private Charset charset=Charset.forName("UTF-8");

		@Override
		public String decodeKey(ByteBuffer byteBuffer) {
			return charset.decode(byteBuffer).toString();
		}

		@Override
		public Object decodeValue(ByteBuffer byteBuffer) {
			try{
				byte[]array=new byte[byteBuffer.remaining()];
				byteBuffer.get(array);
				ObjectInputStream is=new ObjectInputStream(new ByteArrayInputStream(array));
				return is.readObject();
			}catch (Exception e){
				return null;
			}
		}

		@Override
		public ByteBuffer encodeKey(String s) {
			return charset.encode(s);
		}

		@Override
		public ByteBuffer encodeValue(Object o) {
			try{
				ByteArrayOutputStream bytes=new ByteArrayOutputStream();
				ObjectOutputStream os=new ObjectOutputStream(bytes);
				os.writeObject(o);
				return ByteBuffer.wrap(bytes.toByteArray());
			}catch (Exception e){
				return null;
			}
		}
	}
}
