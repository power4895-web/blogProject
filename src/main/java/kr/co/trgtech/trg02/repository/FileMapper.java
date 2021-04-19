package kr.co.trgtech.trg02.repository;

import org.apache.ibatis.annotations.Mapper;

import kr.co.trgtech.trg02.dto.FileInfo;

@Mapper
public interface FileMapper {
	public int insertFile(FileInfo fileInfo);
}
