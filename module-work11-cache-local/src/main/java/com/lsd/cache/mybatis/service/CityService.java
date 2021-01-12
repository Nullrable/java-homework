package com.lsd.cache.mybatis.service;

import com.lsd.cache.mybatis.entity.City;
import com.lsd.cache.mybatis.dto.CityQuery;
import java.util.List;

/**
 * (City)表服务接口
 *
 * @Author nhsoft.lsd
 */
public interface CityService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    City read(Integer id);

    /**
     * 查询多条数据
     *
     * @param query 查询对象
     * @return 对象列表
     */
    List<City> list(CityQuery query);

    /**
     * 新增数据
     *
     * @param city 实例对象
     * @return 实例对象
     */
    City save(City city);

    /**
     * 修改数据
     *
     * @param city 实例对象
     * @return 实例对象
     */
    City update(City city);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 
     */
    void delete(Integer id);

}