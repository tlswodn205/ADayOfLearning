package com.tencoding.ADayOfLearning.util;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;


@Service("mail")
public class Mail {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendAuthEmail(String email, String authCode) {
		MimeMessagePreparator preparatory = mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            String content = "";
            content += "<h3>안녕하세요. 하루의배움입니다.</h3>";
            content += "<p>아래코드를 입력해주세요.</p>";
            content += "<div style=\"border: 1px solid black; width: 200px; text-align: center;\">";
            content += "<h5>인증코드입니다</h5>";
            content += "<strong>"+authCode+"</strong>";
            content += "</div>";
                        
            helper.setTo(email);
            helper.setFrom("ADayOfLearningTeam@gmail.com");
            helper.setSubject("하루의배움 인증코드입니다");
            
            helper.setText(content, true); //html 타입이므로, 두번째 파라미터에 true 설정
        };
        javaMailSender.send(preparatory);
	}
	
    public String createCode() {
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for(int i=0; i<8; i++) {
            int idx = random.nextInt(3);

            switch (idx) {
                case 0 :
                	// a(97) ~ z(122)
                    key.append((char) ((int)random.nextInt(26) + 97));
                    break;
                case 1:
                	// A(65) ~ Z(90)
                    key.append((char) ((int)random.nextInt(26) + 65));
                    break;
                case 2:
                	// 0 ~ 9
                    key.append(random.nextInt(9));
                    break;
            }
        }
        return key.toString();
    }
}