package com.shihui.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Course implements Serializable {
    private int id;
    private String name;
}
