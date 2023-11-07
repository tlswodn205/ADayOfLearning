package com.tencoding.ADayOfLearning.dto.request;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.binary.Hex;

import lombok.Data;

@Data
public class PaymentRequestDto {
	private String merchantKey 		= "EYzu8jGGMfqaDEp76gSckuvnaHHu+bC4opsSN6lHv3b2lurNYkVXrZ7Z1AoqQnXI3eLuaUFyoRNC6FkrzVjceg=="; // 상점키
	private String merchantID 		= "nicepay00m"; 				// 상점아이디
	private String goodsName; 										// 결제상품명
	private String price = "1000"; 											// 결제상품금액	
	private String buyerName; 										// 구매자명
	private String buyerTel; 										// 구매자연락처
	private String buyerEmail; 										// 구매자메일주소
	private String moid 			= "mnoid1234567890"; 			// 상품주문번호	
//	private String returnURL 		= "http://localhost:8080/payment/payResult"; // 결과페이지(절대경로) - 모바일 결제창 전용
	private String ediDate 			= getyyyyMMddHHmmss();
	private String hashString 		= encrypt(ediDate + merchantID + price + merchantKey);
	
	
	public final synchronized String getyyyyMMddHHmmss(){
		SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
		return yyyyMMddHHmmss.format(new Date());
	}

	// SHA-256 형식으로 암호화
	public String encrypt(String strData){
		String passACL = null;
		MessageDigest md = null;
		try{
			md = MessageDigest.getInstance("SHA-256");
			md.reset();
			md.update(strData.getBytes());
			byte[] raw = md.digest();
			passACL = encodeHex(raw);
		}catch(Exception e){
			System.out.print("암호화 에러" + e.toString());
		}
		return passACL;
	}
	
	public String encodeHex(byte [] b){
		char [] c = Hex.encodeHex(b);
		return new String(c);
	}
	
	
}
