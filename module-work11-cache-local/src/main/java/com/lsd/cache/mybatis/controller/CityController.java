package com.lsd.cache.mybatis.controller;

import com.lsd.cache.mybatis.entity.City;
import com.lsd.cache.mybatis.service.CityService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (City)表控制层
 *
 * @author nhsoft.lsd
 */
@RestController
@RequestMapping("city")
public class CityController {
    /**
     * 服务对象
     */
    @Resource
    private CityService cityService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("read")
    public City read(Integer id) {
        return this.cityService.read(id);
    }

}