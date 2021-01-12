package com.lsd.cache.mybatis.service.impl;

import com.lsd.cache.mybatis.entity.City;
import com.lsd.cache.mybatis.dao.CityDao;
import com.lsd.cache.mybatis.service.CityService;
import org.springframework.stereotype.Service;
import com.lsd.cache.mybatis.dto.CityQuery;

import javax.annotation.Resource;
import java.util.List;

/**
 * (City)表服务实现类
 *
 * @author nhsoft.lsd
 */
@Service("cityService")
public class CityServiceImpl implements CityService {
    @Resource
    private CityDao cityDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public City read(Integer id) {
        return this.cityDao.read(id);
    }

    /**
     * 查询多条数据
     *
     * @param query 查询对象
     * @return 对象列表
     */
    @Override
    public List<City> list(CityQuery query) {
        return this.cityDao.list(query);
    }

    /**
     * 新增数据
     *
     * @param city 实例对象
     * @return 实例对象
     */
    @Override
    public City save(City city) {
        this.cityDao.save(city);
        return city;
    }

    /**
     * 修改数据
     *
     * @param city 实例对象
     * @return 实例对象
     */
    @Override
    public City update(City city) {
        this.cityDao.update(city);
        return city;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 
     */
    @Override
    public void delete(Integer id) {
        this.cityDao.delete(id) ;
    }
}