package com.network.calendar_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.regex.Pattern;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "members")
public class Member {
    private static final int MAX_ID_LENGTH = 10;
    private static final int MAX_PASSWORD_LENGTH = 10;
    private static final int MAX_NAME_LENGTH = 20;

    private static final Pattern ID_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]+$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]+$");

    @Id
    private String loginId;

    private String password;
    private String name;
    private String email;

    public Member(String loginId, String password, String name, String email) {
        idTest(loginId);
        this.loginId = loginId;
        passwordTest(password);
        this.password = password;
        nameTest(name);
        this.name = name;
        this.email = email;
    }

    private void idTest(String id){
        if(id.length() > MAX_ID_LENGTH){
            throw new IllegalArgumentException("ID는 "+MAX_ID_LENGTH+"자까지 가능합니다.");
        }
        if(!ID_PATTERN.matcher(id).matches()){
            throw new IllegalArgumentException("ID는 알파벳과 숫자의 조합만 가능합니다.");
        }
    }

    private void passwordTest(String password){
        if(password.length() > MAX_PASSWORD_LENGTH){
            throw new IllegalArgumentException("password는 "+MAX_PASSWORD_LENGTH+"자까지 가능합니다.");
        }
        if(!PASSWORD_PATTERN.matcher(password).matches()){
            throw new IllegalArgumentException("비밀번호는 알파벳과 숫자의 조합만 가능합니다");
        }
    }

    private void nameTest(String name){
        if(name.length() > MAX_NAME_LENGTH){
            throw new IllegalArgumentException("이름은 "+MAX_NAME_LENGTH+"자까지 가능합니다");
        }
    }
}