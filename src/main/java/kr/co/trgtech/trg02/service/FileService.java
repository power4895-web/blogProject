package kr.co.trgtech.trg02.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.trgtech.trg02.core.utils.FileManager;
import kr.co.trgtech.trg02.dto.FileInfo;
import kr.co.trgtech.trg02.repository.FileMapper;

@Service
public class FileService {

	@Autowired
	FileMapper fileMapper;
	
	public FileInfo singleFileUpload(MultipartFile file, String path) throws Exception{
		return FileManager.upload(file, path);
	}
	
	
	public FileInfo selectOneImageFile(String no) {
		return fileMapper.selectOneImageFile(no);
	}
	
	/**
	 * 파일 조회
	 * @param id
	 * @return
	 */
	public FileInfo selectFile(String no) { 
		return fileMapper.selectFile(no);
	}
	
	
	public FileInfo selectFileByLoginId(FileInfo fileInfo){
		return fileMapper.selectFileByLoginId(fileInfo);
	}
	
	
	
}
