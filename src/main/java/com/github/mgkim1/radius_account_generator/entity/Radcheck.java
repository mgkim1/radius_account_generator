package com.github.mgkim1.radius_account_generator.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "radcheck")
public class Radcheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 번호. Integer과는 달리 int에는 null이 들어갈 수 없음

    @Column(length=64, nullable=false)
    private String username; // 사용자명(Wi-Fi 접속 아이디)

    @Column(length=64, nullable=false)
    private String attribute;

    @Column(length=2, nullable=false, columnDefinition="CHAR(2) DEFAULT '=='") // 이 부분은 ChatGPT를 사용해 작성
    private String op;

    @Column(length=253, nullable=false)
    private String value; // 비밀번호
}
