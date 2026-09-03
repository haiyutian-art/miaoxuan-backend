package com.jf.mapper;

import com.jf.pojo.Major;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 专业数据访问接口
 */
@Mapper
@Repository
public interface MajorMapper {
    
    /**
     * 根据主键删除
     */
    int deleteByPrimaryKey(Integer majorId);

    /**
     * 插入专业
     */
    int insert(Major record);

    /**
     * 选择性插入专业
     */
    int insertSelective(Major record);

    /**
     * 根据主键查询
     */
    Major selectByPrimaryKey(Integer majorId);

    /**
     * 选择性更新
     */
    int updateByPrimaryKeySelective(Major record);

    /**
     * 更新专业
     */
    int updateByPrimaryKey(Major record);
    
    /**
     * 根据专业ID获取专业名称
     * @param majorId 专业ID
     * @return 专业名称
     */
    String majorNameByMajorId(@Param("majorId") Integer majorId);
} 