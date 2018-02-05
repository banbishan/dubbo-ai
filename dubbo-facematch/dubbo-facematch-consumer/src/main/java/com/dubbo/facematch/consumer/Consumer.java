package com.dubbo.facematch.consumer;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dubbo.facematch.api.FaceMatch;
import com.dubbo.facematch.api.SaveFace;
import com.dubbo.facematch.common.FileUtil;

public class Consumer {

	public static void main(String[] args) {
		System.setProperty("java.net.preferIPv4Stack", "true");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/dubbo-demo-consumer.xml"});
        context.start();
        
        faceMatchTest(context);
        
//        saveFaceTest(context);
        
	}
	
	public static void saveFaceTest(ClassPathXmlApplicationContext context){
		SaveFace saveFace = (SaveFace) context.getBean("SaveFace");
		String name = "E:\\生物识别\\id.jpg";
		String id = "1234567890987654321";
		byte[] b;
		try {
			b = FileUtil.readFileByBytes(name);
			System.out.println(saveFace.saveFace(b, id));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void faceMatchTest(ClassPathXmlApplicationContext context) {
		FaceMatch faceMatch = (FaceMatch) context.getBean("FaceMatch"); // get remote service proxy

        String name = "E:\\生物识别\\id.jpg";
        String id = "1234567890987654321";
        System.out.println("调到了");
        byte[] b;
		try {
			b = FileUtil.readFileByBytes(name);
			System.out.println(faceMatch.match(b,id));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
