package priv.wenhao.dormitory.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.wenhao.base.pojo.vo.ResultVo;
import priv.wenhao.base.util.RsaUtil;

import java.util.Base64;

@Api(tags = "获得密钥接口")
@RestController
@RequestMapping("/encryption")
public class EncryptionController {
	@ApiOperation(value = "获得rsa公钥",httpMethod = "GET")
	@GetMapping("/rsa")
//	@SecurityParameter(inDecode = false,outEncode = false)
	public ResultVo getRsaPublicKey()throws Exception{
		ResultVo resultVo=new ResultVo();
		resultVo.setMessage("查询成功");
		resultVo.setData(new String(Base64.getEncoder().encode(RsaUtil.publicKey.getEncoded())));

		return resultVo;

	}

}
