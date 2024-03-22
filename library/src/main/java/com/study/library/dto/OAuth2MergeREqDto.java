package com.study.library.dto;

import lombok.Data;

@Data
public class OAuth2MergeREqDto {
    private String username;
    private String password;
    private String oauth2Name;
    private String providerName;
}
