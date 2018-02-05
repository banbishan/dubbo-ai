package com.dubbo.facematch.provider;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.dubbo.facematch.api.SaveFace;

public class SaveFaceImpl implements SaveFace {

	public String saveFace(byte[] imgByte, String id) {
		String flag = "fail";
		
		String imgPath = "E:\\生物识别\\"+id+"\\"+id+".jpg";
		
		File file=new File(imgPath);
		File fileParent = file.getParentFile();  
		if(!fileParent.exists()){  
		    fileParent.mkdirs();  
		}  
		try {
			file.createNewFile();
			FileOutputStream fos=new FileOutputStream(file);
	        for (int i = 0; i < imgByte.length; i++) {
	        	fos.write(imgByte[i]);
	        }
	        flag = "success";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return flag.toString();
		
	}

}
