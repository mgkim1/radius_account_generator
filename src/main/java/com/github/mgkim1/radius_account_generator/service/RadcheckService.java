package com.github.mgkim1.radius_account_generator.service;

import com.github.mgkim1.radius_account_generator.entity.Radcheck;
import com.github.mgkim1.radius_account_generator.repository.RadcheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RadcheckService {
    private final RadcheckRepository radcheckRepository; // DB에 Radcheck 객체를 저장하기 위해 사용
    @Autowired
    public RadcheckService(RadcheckRepository radcheckRepository) { // 생성자를 이용해 radcheckRepository 의존성 주입
        this.radcheckRepository = radcheckRepository;
    }

    private String generateRandomString() { // 랜덤한 12글자 문자열(숫자, 알파벳 소문자) 생성
        StringBuilder tempStringBuilder = new StringBuilder(12); // 랜덤으로 생성될 글자들이 저장될 곳
        for(byte i = 0; i < 12; i++) { // 12글자 생성
            byte temp = (byte)(Math.random() * 36);    // 숫자 10개, 알파벳 26개. 생성되는 값은 0~35
            if(temp < 10) tempStringBuilder.append((char)(temp + 48));   // 0~9인 경우. 아스키 코드에서 숫자 0~9는 48~57이기 때문에 48을 더함
            else tempStringBuilder.append((char)(temp + 87));            // 10~35인 경우. 아스키 코드에서 소문자 a~z는 97~122. 97 - 10 = 87이기에 87을 더함
        }
        return tempStringBuilder.toString(); // StringBuilder를 String으로 변환하여 반환
    }

    public Radcheck create() { // 랜덤한 아이디, 비밀번호로 계정을 만들어서 DB에 저장하고, 계정 정보를 반환
        String username = generateRandomString(); // 사용자명 랜덤생성
        while(radcheckRepository.existsByUsername(username))
            username = generateRandomString(); // 만약 아이디가 중복되었다면 다시 생성
        Radcheck radcheck = new Radcheck(null, username, "Cleartext-Password", ":=", generateRandomString());
        // 생성된 (중복되지 않는) 아이디, 랜덤생성된 비밀번호로 Radcheck 객체 만들기
        radcheckRepository.save(radcheck);
        // 만들어진 radcheck 객체를 db에 저장
        return radcheck; // 만들어진 객체(아이디, 비번 담겨있는) 반환
    }
}
