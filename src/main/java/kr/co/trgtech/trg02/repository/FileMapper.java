package kr.co.trgtech.trg02.repository;

import org.apache.ibatis.annotations.Mapper;

import kr.co.trgtech.trg02.dto.FileInfo;

@Mapper
public interface FileMapper {
	public int insertFile(FileInfo fileInfo);
	
	
	public FileInfo selectOneImageFile(String no);

	public FileInfo selectFile(String no);	
	public FileInfo selectFileByLoginId(FileInfo fileInfo);	
	
}
