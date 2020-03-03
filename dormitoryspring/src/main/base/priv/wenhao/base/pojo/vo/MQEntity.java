package priv.wenhao.base.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 消息实体
 */
@Data
public class MQEntity implements Serializable {

	private Map<String, Object> extObj = new LinkedHashMap<>();

	private String mqId ;

	private String mqKey;

	/**
	 * 添加附加字段
	 * @param key
	 * @param value
	 */
	public void addExt(String key , Object value){
		extObj.put(key, value);
	}

	/**
	 * 获取附加字段
	 * @param key
	 */
	public void getExt(String key ){
		extObj.get(key);
	}

}