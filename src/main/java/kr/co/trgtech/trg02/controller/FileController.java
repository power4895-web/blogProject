package kr.co.trgtech.trg02.controller;

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
	
	
}
