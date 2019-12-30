package priv.wenhao.base.pojo.dto;

import lombok.Data;

/**
 * ClassName: IPMessageDto
 * Description:
 * Author: yuWenHao
 * Date: 2019/12/30
 */

@Data
public class IPMessageDto {
	/**
	 * ip : 122.225.55.70
	 * country : 中国
	 * area :
	 * region : 浙江
	 * city : 嘉兴
	 * county : XX
	 * isp : 电信
	 * country_id : CN
	 * area_id :
	 * region_id : 330000
	 * city_id : 330400
	 * county_id : xx
	 * isp_id : 100017
	 */
	private String ip;
	private String country;
	private String area;
	private String region;
	private String city;
	private String county;
	private String isp;
	private String country_id;
	private String area_id;
	private String region_id;
	private String city_id;
	private String county_id;
	private String isp_id;
}
