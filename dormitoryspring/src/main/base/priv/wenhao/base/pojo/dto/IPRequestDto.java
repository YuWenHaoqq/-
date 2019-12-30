package priv.wenhao.base.pojo.dto;

import lombok.Data;
/**
 * ClassName: MessageDto
 * Description:
 * Author: yuWenHao
 * Date: 2019/12/30
 */

@Data
public class IPRequestDto {

	/**
	 * code : 0
	 * data : {"ip":"122.225.55.70","country":"中国","area":"","region":"浙江","city":"嘉兴","county":"XX","isp":"电信","country_id":"CN","area_id":"","region_id":"330000","city_id":"330400","county_id":"xx","isp_id":"100017"}
	 */
	private int code;
	private IPMessageDto data;
}
