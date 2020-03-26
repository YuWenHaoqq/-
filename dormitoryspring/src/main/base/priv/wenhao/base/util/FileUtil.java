package priv.wenhao.base.util;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

public class FileUtil {
	public static ResponseEntity<FileSystemResource> exportFile(File file) throws UnsupportedEncodingException {
		if (file==null){
			return null;
		}
		String fileName=new String(file.getName().getBytes("UTF-8"),"iso-8859-1");
//		System.out.println(fileName);
		HttpHeaders headers=new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Content-Disposition", "attachment;filename="+ URLEncoder.encode(file.getName(),"utf-8"));
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		headers.add("Last-Modified", new Date().toString());
		headers.add("ETag", String.valueOf(System.currentTimeMillis()));
		return ResponseEntity.ok()
				.headers(headers)
				.contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.body(new FileSystemResource(file));
	}

}
