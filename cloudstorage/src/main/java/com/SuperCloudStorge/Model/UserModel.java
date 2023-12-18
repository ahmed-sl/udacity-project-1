package com.SuperCloudStorge.Model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class UserModel {


      private Integer userId;
      private String username;
      private String salt;
      private String password;
      private String firstname;
      private String lastname;

}
