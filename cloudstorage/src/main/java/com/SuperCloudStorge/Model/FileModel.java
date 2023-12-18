package com.SuperCloudStorge.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FileModel {
       private Integer fileId;

       private String filename;

       private String contenttype;

       private Long filesize;

       private int userid;

       private byte[] filedata;


}
