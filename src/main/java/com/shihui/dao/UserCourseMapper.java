package com.shihui.dao;

import com.shihui.model.UserCourse;

public interface UserCourseMapper {
    UserCourse queryByUserId(int userId);
}
