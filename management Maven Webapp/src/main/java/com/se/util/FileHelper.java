package com.se.util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public final class FileHelper {
	
		public static void saveFile(String path,MultipartFile[] files){
			File dirPath=new File(path);
			if(!dirPath.exists())
				dirPath.mkdirs();
			if(files.length!=0){
				for(MultipartFile file : files){
					try {
						file.transferTo(new File(dirPath,file.getOriginalFilename()));
					} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
					}
				}
			}
					
		}

}
