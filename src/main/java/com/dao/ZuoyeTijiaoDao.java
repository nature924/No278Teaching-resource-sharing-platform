package com.dao;

import com.entity.ZuoyeTijiaoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ZuoyeTijiaoView;

/**
 * 作业提交 Dao 接口
 *
 * @author 
 */
public interface ZuoyeTijiaoDao extends BaseMapper<ZuoyeTijiaoEntity> {

   List<ZuoyeTijiaoView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
