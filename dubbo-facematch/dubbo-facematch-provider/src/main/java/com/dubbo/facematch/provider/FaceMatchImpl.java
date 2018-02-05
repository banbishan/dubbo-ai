package com.dubbo.facematch.provider;

import java.net.URLEncoder;

import com.dubbo.facematch.api.FaceMatch;
import com.dubbo.facematch.common.Base64Util;
import com.dubbo.facematch.common.FileUtil;
import com.dubbo.facematch.common.HttpUtil;

/**
 * 人脸对比
 */
public class FaceMatchImpl implements FaceMatch {

	/**
	 * 重要提示代码中所需工具类 FileUtil,Base64Util,HttpUtil,GsonUtils请从
	 * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
	 * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
	 * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
	 * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3 下载
	 */
	public String match(byte[] b, String id) {
		// 请求url
		String url = "https://aip.baidubce.com/rest/2.0/face/v2/match";
		try {
			// String filePath = "E:\\生物识别\\id.jpg";
//			byte[] imgData1 = FileUtil.readFileByBytes(name);
			byte[] imgData1 = b;
			String imgStr = Base64Util.encode(imgData1);
			String imgParam = URLEncoder.encode(imgStr, "UTF-8");

			String filePath2 = "E:\\生物识别\\"+id+"\\"+id+".jpg";
			byte[] imgData2 = null;
			try {
				imgData2 = FileUtil.readFileByBytes(filePath2);
			} catch (Exception e) {
				System.out.println("id不存在");
			}
			
			String imgStr2 = Base64Util.encode(imgData2);
			String imgParam2 = URLEncoder.encode(imgStr2, "UTF-8");

			String param = "images=" + imgParam + "," + imgParam2;

			// 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间，
			// 客户端可自行缓存，过期后重新获取。
			String accessToken = AuthService.getAuth();

			String result = HttpUtil.post(url, accessToken, param);
			System.out.println(result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
