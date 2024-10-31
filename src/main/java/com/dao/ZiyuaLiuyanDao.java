package com.dao;

import com.entity.ZiyuaLiuyanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ZiyuaLiuyanView;

/**
 * 教学资源留言 Dao 接口
 *
 * @author 
 */
public interface ZiyuaLiuyanDao extends BaseMapper<ZiyuaLiuyanEntity> {

   List<ZiyuaLiuyanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
