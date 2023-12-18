package com.SuperCloudStorge.Model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CredentialModel {

    private Integer credentialid;

    private String url;

    private String username;

    private String key;

    private String password;
    private String encryptedPassword;

    private Integer userid;


}
