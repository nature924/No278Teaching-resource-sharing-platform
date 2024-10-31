package com.dao;

import com.entity.ZiyuaCollectionEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ZiyuaCollectionView;

/**
 * 教学资源收藏 Dao 接口
 *
 * @author 
 */
public interface ZiyuaCollectionDao extends BaseMapper<ZiyuaCollectionEntity> {

   List<ZiyuaCollectionView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
