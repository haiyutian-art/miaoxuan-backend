package com.jf.mapper;

import com.jf.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 管理员数据访问接口
 */
@Mapper
@Repository
public interface AdminMapper {
    
    /**
     * 根据主键删除
     */
    int deleteByPrimaryKey(Integer adminId);

    /**
     * 插入管理员
     */
    int insert(Admin record);

    /**
     * 选择性插入管理员
     */
    int insertSelective(Admin record);

    /**
     * 根据主键查询
     */
    Admin selectByPrimaryKey(Integer adminId);

    /**
     * 选择性更新
     */
    int updateByPrimaryKeySelective(Admin record);

    /**
     * 更新管理员
     */
    int updateByPrimaryKey(Admin record);
    
    /**
     * 根据管理员编号查询
     */
    Admin selectByAdminNo(String adminNo);
} 