package kr.co.trgtech.trg02.core.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import kr.co.trgtech.trg02.dto.FileInfo;



public class FileManager {

	/**
	 * 파일업로드
	 * @param files
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static List< FileInfo> upload(List<MultipartFile> files, String filePath) throws Exception{
		
		List< FileInfo> list = new ArrayList< FileInfo>();
		
		String subPath = DateUtil.getDate("yyyyMM");
		String fileFullPath = filePath + "/" + subPath + "/";

		if (null != files && files.size() > 0) {

			File dir = new File(fileFullPath);
			if(!dir.exists())	dir.mkdirs();

			for (MultipartFile multipartFile : files) {

				if(!multipartFile.isEmpty()){
					String fileRealNm = multipartFile.getOriginalFilename();

					String fileExt = fileRealNm.substring(fileRealNm.lastIndexOf(".") + 1,fileRealNm.length()).toLowerCase();
					if(fileExt.equals("jsp")) fileExt = "txt";
					String fileSysNm = SysUtil.getShortUUID()+ "." + fileExt;
					long fileSize = multipartFile.getSize();

					File f = new File(fileFullPath + fileSysNm);
//					if(f.exists()) f.delete();

					multipartFile.transferTo(f);

					FileInfo file = new FileInfo();
					file.setFilePath(fileFullPath);
					file.setSysFilename(fileSysNm);
					file.setOrgFilename(fileRealNm);
					file.setSize(fileSize);
					file.setExt(fileExt);

					list.add(file);

				}
			}

		}

		return list;
	}

	/**
	 * 파일업로드
	 * @param mf
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static  FileInfo upload(MultipartFile mf, String filePath) throws Exception{

		FileInfo fileInfo = new  FileInfo();
		
		String subPath = DateUtil.getDate("yyyyMM");
		String fileFullPath = filePath + "/" + subPath + "/";
		
		if (null != mf) {
			File dir = new File(fileFullPath);
			if(!dir.exists())	dir.mkdirs();

			if(!mf.isEmpty()){
				String fileRealNm = mf.getOriginalFilename();

				String fileExt = fileRealNm.substring(fileRealNm.lastIndexOf(".") + 1,fileRealNm.length()).toLowerCase();
				if(fileExt.equals("jsp")) fileExt = "txt";
				String fileSysNm = SysUtil.getShortUUID()+ "." + fileExt;
				long fileSize = mf.getSize();
				

				File f = new File(fileFullPath+ fileSysNm);
//				if(f.exists()) f.delete();

				mf.transferTo(f);
				
				fileInfo.setFilePath(fileFullPath);
				fileInfo.setSysFilename(fileSysNm);
				fileInfo.setOrgFilename(fileRealNm);
				fileInfo.setSize(fileSize);
				fileInfo.setExt(fileExt);
				
			}
		}
		return fileInfo;
	}

	
	/**
	 * 파일 다운로드
	 * @param request
	 * @param response
	 * @param fileInfo
	 * @throws UnsupportedEncodingException
	 */
	public static void fileDownload(HttpServletResponse response,  FileInfo fileInfo) throws UnsupportedEncodingException {
		
		if(fileInfo == null) return;
		
		String fileSysFilenam = fileInfo.getFilePath() + fileInfo.getSysFilename();
		String fileName =  URLEncoder.encode(fileInfo.getOrgFilename(),"UTF-8");
		
		try{ 
			File f =  new File(fileSysFilenam);
			
	        response.setContentType("application/octet-stream");
	        response.setHeader("Content-Disposition", "attachment; filename=" +  fileName);
	        response.setHeader("Content-Length", "" + f.length() );
	        
	        InputStream in = new FileInputStream(f);
	        OutputStream out =  response.getOutputStream();        
	        
	        BufferedInputStream bin = null;
	        BufferedOutputStream bos = null; 
	        
	        try {
	            bin = new BufferedInputStream( in );
	            bos = new BufferedOutputStream( out ); 
	        
	            byte[] buf = new byte[2048]; //buffer size 2K.
	            int read = 0;
	            while ((read = bin.read(buf)) != -1) {
	                bos.write(buf,0,read);
	            }
	        } finally {
	        	try {if(bos != null) bos.flush();  bos.close(); } catch(Exception e){};
	        	try {if(bin != null) bin.close();} catch(Exception e){};
	        }        

		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 파일삭제
	 * @param fileNm
	 */
	public static void fileDelete(String fileNm) {
		File f = new File(fileNm);
		if(f.exists()) f.delete();
	}
	
	
	public static String getMimeType(String fileRealNm) {
		
		String fileExt = fileRealNm.substring(fileRealNm.lastIndexOf(".") + 1, fileRealNm.length()).toLowerCase();

		String mimeType = "";

		if ("jpg".equals(fileExt)) {
			mimeType = "image/jpeg;";
		} else if ("jpeg".equals(fileExt)) {
			mimeType = "image/jpeg;";
		} else if ("png".equals(fileExt)) {
			mimeType = "image/png;";
		} else if ("bmp".equals(fileExt)) {
			mimeType = "image/bmp;";
		} else if ("gif".equals(fileExt)) {
			mimeType = "image/gif;";
		} else {
			mimeType = "image/jpeg;";
		}

		return mimeType;
		
	}

}
