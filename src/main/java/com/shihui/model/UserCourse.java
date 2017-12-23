package com.shihui.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserCourse implements Serializable {
    private int id;
    private Course course;
    private int userId;
}
