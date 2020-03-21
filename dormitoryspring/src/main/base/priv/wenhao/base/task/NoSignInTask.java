package priv.wenhao.base.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import priv.wenhao.base.pojo.dto.UnsignHistoryDto;
import priv.wenhao.dormitory.mapper.UnsignHistoryMapper;

import java.util.List;

@Component
@Slf4j
public class NoSignInTask {
	@Autowired
	private UnsignHistoryMapper unsignHistoryMapper;

/***
* ClassName:NoSignInTask
* Description: 在每天22:00时自动生成未签到记录
* param:[]
* return:void
* Author:yu wenhao
* date:2020/3/17
*/
	@Scheduled(cron = "0 0 22 * * ?")
//	@Scheduled(fixedDelay = 6000)
	private void createNoSignJistory()throws Exception{
		log.info("开始添加未签到记录");
		List<UnsignHistoryDto> list= unsignHistoryMapper.selectUnsign();
		log.info("共有"+list.size()+"行未签到记录");
		if (list.size()==0){
			log.info("全部签到成功");
			return;
		}
		int row= unsignHistoryMapper.insertUnsign(list);
		log.info("成功插入"+row+"行数据");

	}

}
