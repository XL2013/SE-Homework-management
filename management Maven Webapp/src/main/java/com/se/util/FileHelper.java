package com.se.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

public final class FileHelper {
	
		public static void saveFile(String path,MultipartFile[] files){
			File dirPath=new File(path);
			if(!dirPath.exists())
				dirPath.mkdirs();
			if(files.length!=0){
				for(MultipartFile file : files){
					try {
						File tempFile=new File(dirPath,file.getOriginalFilename());
						if(!ExcelHelper.validateExcel(tempFile.getPath())) continue;
						file.transferTo(tempFile);	
						InputStream is=new FileInputStream(tempFile);
						is.close();
					} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
					}				
				}
			}
					
		}

}
