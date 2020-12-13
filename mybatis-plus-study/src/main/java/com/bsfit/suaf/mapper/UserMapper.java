package com.bsfit.suaf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bsfit.suaf.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 继承basemapper之后就实现了基础的mapper
 *
 * @author bangsun
 * */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
