package priv.wenhao.base.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.wenhao.base.exception.BussinessException;
import priv.wenhao.base.pojo.vo.ResultVo;

import javax.servlet.http.HttpServletRequest;
/**
 * Description: HandleGlobalException
 * Author: yuWenHao
 * Date: 2019/12/17
 */

@ControllerAdvice
public class HandleGlobalExceptionAdvice {
	/***
	* Description:统一异常管理-自定义异常
	* param:[req, e]
	* return:priv.wenhao.base.pojo.vo.ResultVo
	* Author:yu wenhao
	* date:2019/12/17
	*/
	@ExceptionHandler(value = BussinessException.class)
	@ResponseBody
	public ResultVo errorHandle(HttpServletRequest req, Exception e)throws Exception{
		ResultVo resultVo=new ResultVo();
		if (e instanceof BussinessException){
			BussinessException bussinessException=(BussinessException)e;
			resultVo.setCode(bussinessException.getCode());
			resultVo.setMessage(bussinessException.getMessage());
			return resultVo;
		}
		resultVo.setData(-1);
		resultVo.setMessage("未知异常,请联系管理员");
		return resultVo;
	}

//	/***
//	* Description:统一异常管理-全局异常
//	* param:[req, e]
//	* return:priv.wenhao.base.pojo.vo.ResultVo
//	* Author:yu wenhao
//	* date:2019/12/17
//	*/
//	@ExceptionHandler(value = Exception.class)
//	@ResponseBody
//	public ResultVo exceptionHandle(HttpServletRequest req, Exception e){
//		ResultVo resultVo=new ResultVo();
//		resultVo.setCode(-1);
//		resultVo.setMessage("未知异常,请联系后台程序员");
//		return resultVo;
//	}
}
