package com.ocean.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String username;
    @JsonIgnore // 不展示
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private String address;

}
