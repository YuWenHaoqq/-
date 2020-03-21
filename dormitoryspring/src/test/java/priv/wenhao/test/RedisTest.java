package priv.wenhao.test;

import io.lettuce.core.api.sync.RedisCommands;
import org.junit.Test;

import javax.annotation.Resource;

public class RedisTest {
	@Resource(name = "thirdTemplate")
	private RedisCommands<String,String> redisCommands;
	@Test
	public void test(){
		redisCommands.select(8);
		redisCommands.set("999","999");
		redisCommands.set("999","1000");


	}
}
