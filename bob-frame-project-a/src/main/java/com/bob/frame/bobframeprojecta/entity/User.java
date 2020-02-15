package com.bob.frame.bobframeprojecta.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-02-10 20:22:04
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 873662708363392654L;
    
    private Integer id;
    
    private String username;
    
    private String sex;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date birthday;
    
    private String address;
    
    private String password;

}