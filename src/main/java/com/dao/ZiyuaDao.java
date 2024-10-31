package com.dao;

import com.entity.ZiyuaEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ZiyuaView;

/**
 * 教学资源 Dao 接口
 *
 * @author 
 */
public interface ZiyuaDao extends BaseMapper<ZiyuaEntity> {

   List<ZiyuaView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
