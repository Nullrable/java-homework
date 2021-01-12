package com.lsd.cache.mybatis.dao;

import com.lsd.cache.mybatis.entity.City;
import com.lsd.cache.mybatis.dto.CityQuery;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (City)表数据库访问层
 *
 * @Author nhsoft.lsd
 */
public interface CityDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    City read(Integer id);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param query 实例对象
     * @return 对象列表
     */
    List<City> list(CityQuery query);

    /**
     * 新增数据
     *
     * @param city 实例对象
     * @return 
     */
    void save(City city);

    /**
     * 修改数据
     *
     * @param city 实例对象
     * @return 
     */
    void update(City city);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 
     */
    void delete(Integer id);

}