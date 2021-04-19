package kr.co.trgtech.trg02.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.trgtech.trg02.dto.FileInfo;
import kr.co.trgtech.trg02.service.FileService;

@Controller
public class FileController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Autowired
	private FileService fileService;
	
	@Value("${file.upload.path}") 
	private String fileUploadPath;
	
	@ResponseBody
	@RequestMapping(value="/fileUpload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public FileInfo fileUpload(@RequestParam("file") MultipartFile file) throws Exception{
		return fileService.singleFileUpload(file, fileUploadPath);
	}
	
	/**
	 * 파일다운로드
	 * @param response
	 * @param filename
	 * @throws IOException 
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value="/imagesFielRead")
	public void  imagesRead (HttpServletResponse response, @RequestParam("no") String no) throws IOException {
		ServletOutputStream imgout = response.getOutputStream();
		FileInfo fileInfo = fileService.selectOneImageFile(no);
		
		if (fileInfo != null) {
			
			String imageFileName = fileInfo.getFilePath() + fileInfo.getSysFilename();
			
			File file = new File(imageFileName);
			if(file.exists()) {
				try (FileInputStream input = new FileInputStream(imageFileName)){
					int length;
					byte[] buffer = new byte[10];
					
					while( (length = input.read(buffer)) != -1 ) {
						imgout.write(buffer, 0, length);
					}
				} catch (FileNotFoundException e) {
					logger.error("/imagesFielRead is failed to find a file", e);
					throw e;
				}
			}
		}
	}

	
	
	
	
	
}
