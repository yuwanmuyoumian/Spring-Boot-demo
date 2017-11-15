package com.spring.lemon.mapper;

import com.spring.lemon.entity.bean.LemonEmployee;
import tk.mybatis.mapper.common.Mapper;


public interface LemonEmployeeMapper extends Mapper<LemonEmployee>
{
    LemonEmployee getEmployee();
}
