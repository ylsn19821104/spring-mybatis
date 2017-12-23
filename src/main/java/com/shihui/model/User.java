package com.shihui.model;


import com.shihui.emodel.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1286331582025023042L;

    private Integer id;
    private String name;
    private String email;
    private Sex sex;

    private UserCard userCard;

    private List<UserCourse> userCourses;
}
