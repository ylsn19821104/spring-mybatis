package com.shihui.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserCard implements Serializable{
    private int id;
    private int userId;
    private String cardNo;
}
