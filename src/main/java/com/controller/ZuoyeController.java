
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 作业
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/zuoye")
public class ZuoyeController {
    private static final Logger logger = LoggerFactory.getLogger(ZuoyeController.class);

    private static final String TABLE_NAME = "zuoye";

    @Autowired
    private ZuoyeService zuoyeService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private ChengjiService chengjiService;//成绩
    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private ForumService forumService;//交流论坛
    @Autowired
    private GonggaoService gonggaoService;//公告信息
    @Autowired
    private LaoshiService laoshiService;//老师
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private ZiyuaService ziyuaService;//教学资源
    @Autowired
    private ZiyuaCollectionService ziyuaCollectionService;//教学资源收藏
    @Autowired
    private ZiyuaLiuyanService ziyuaLiuyanService;//教学资源留言
    @Autowired
    private ZuoyeTijiaoService zuoyeTijiaoService;//作业提交
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("老师".equals(role))
            params.put("laoshiId",request.getSession().getAttribute("userId"));
        params.put("zuoyeDeleteStart",1);params.put("zuoyeDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = zuoyeService.queryPage(params);

        //字典表数据转换
        List<ZuoyeView> list =(List<ZuoyeView>)page.getList();
        for(ZuoyeView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ZuoyeEntity zuoye = zuoyeService.selectById(id);
        if(zuoye !=null){
            //entity转view
            ZuoyeView view = new ZuoyeView();
            BeanUtils.copyProperties( zuoye , view );//把实体数据重构到view中
            //级联表 老师
            //级联表
            LaoshiEntity laoshi = laoshiService.selectById(zuoye.getLaoshiId());
            if(laoshi != null){
            BeanUtils.copyProperties( laoshi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "laoshiId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setLaoshiId(laoshi.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody ZuoyeEntity zuoye, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,zuoye:{}",this.getClass().getName(),zuoye.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("老师".equals(role))
            zuoye.setLaoshiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<ZuoyeEntity> queryWrapper = new EntityWrapper<ZuoyeEntity>()
            .eq("laoshi_id", zuoye.getLaoshiId())
            .eq("zuoye_name", zuoye.getZuoyeName())
            .eq("zuoye_types", zuoye.getZuoyeTypes())
            .eq("zuoye_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZuoyeEntity zuoyeEntity = zuoyeService.selectOne(queryWrapper);
        if(zuoyeEntity==null){
            zuoye.setZuoyeDelete(1);
            zuoye.setInsertTime(new Date());
            zuoye.setCreateTime(new Date());
            zuoyeService.insert(zuoye);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ZuoyeEntity zuoye, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,zuoye:{}",this.getClass().getName(),zuoye.toString());
        ZuoyeEntity oldZuoyeEntity = zuoyeService.selectById(zuoye.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("老师".equals(role))
//            zuoye.setLaoshiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(zuoye.getZuoyePhoto()) || "null".equals(zuoye.getZuoyePhoto())){
                zuoye.setZuoyePhoto(null);
        }
        if("".equals(zuoye.getZuoyeFile()) || "null".equals(zuoye.getZuoyeFile())){
                zuoye.setZuoyeFile(null);
        }
        if("".equals(zuoye.getZuoyeContent()) || "null".equals(zuoye.getZuoyeContent())){
                zuoye.setZuoyeContent(null);
        }

            zuoyeService.updateById(zuoye);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<ZuoyeEntity> oldZuoyeList =zuoyeService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<ZuoyeEntity> list = new ArrayList<>();
        for(Integer id:ids){
            ZuoyeEntity zuoyeEntity = new ZuoyeEntity();
            zuoyeEntity.setId(id);
            zuoyeEntity.setZuoyeDelete(2);
            list.add(zuoyeEntity);
        }
        if(list != null && list.size() >0){
            zuoyeService.updateBatchById(list);
        }

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<ZuoyeEntity> zuoyeList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            ZuoyeEntity zuoyeEntity = new ZuoyeEntity();
//                            zuoyeEntity.setLaoshiId(Integer.valueOf(data.get(0)));   //老师 要改的
//                            zuoyeEntity.setZuoyeName(data.get(0));                    //作业名称 要改的
//                            zuoyeEntity.setZuoyeUuidNumber(data.get(0));                    //作业编号 要改的
//                            zuoyeEntity.setZuoyePhoto("");//详情和图片
//                            zuoyeEntity.setZuoyeTypes(Integer.valueOf(data.get(0)));   //作业类型 要改的
//                            zuoyeEntity.setZuoyeFile(data.get(0));                    //作业下载 要改的
//                            zuoyeEntity.setZuoyeContent("");//详情和图片
//                            zuoyeEntity.setZuoyeDelete(1);//逻辑删除字段
//                            zuoyeEntity.setInsertTime(date);//时间
//                            zuoyeEntity.setCreateTime(date);//时间
                            zuoyeList.add(zuoyeEntity);


                            //把要查询是否重复的字段放入map中
                                //作业编号
                                if(seachFields.containsKey("zuoyeUuidNumber")){
                                    List<String> zuoyeUuidNumber = seachFields.get("zuoyeUuidNumber");
                                    zuoyeUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> zuoyeUuidNumber = new ArrayList<>();
                                    zuoyeUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("zuoyeUuidNumber",zuoyeUuidNumber);
                                }
                        }

                        //查询是否重复
                         //作业编号
                        List<ZuoyeEntity> zuoyeEntities_zuoyeUuidNumber = zuoyeService.selectList(new EntityWrapper<ZuoyeEntity>().in("zuoye_uuid_number", seachFields.get("zuoyeUuidNumber")).eq("zuoye_delete", 1));
                        if(zuoyeEntities_zuoyeUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ZuoyeEntity s:zuoyeEntities_zuoyeUuidNumber){
                                repeatFields.add(s.getZuoyeUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [作业编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        zuoyeService.insertBatch(zuoyeList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = zuoyeService.queryPage(params);

        //字典表数据转换
        List<ZuoyeView> list =(List<ZuoyeView>)page.getList();
        for(ZuoyeView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ZuoyeEntity zuoye = zuoyeService.selectById(id);
            if(zuoye !=null){


                //entity转view
                ZuoyeView view = new ZuoyeView();
                BeanUtils.copyProperties( zuoye , view );//把实体数据重构到view中

                //级联表
                    LaoshiEntity laoshi = laoshiService.selectById(zuoye.getLaoshiId());
                if(laoshi != null){
                    BeanUtils.copyProperties( laoshi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "laoshiId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setLaoshiId(laoshi.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody ZuoyeEntity zuoye, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,zuoye:{}",this.getClass().getName(),zuoye.toString());
        Wrapper<ZuoyeEntity> queryWrapper = new EntityWrapper<ZuoyeEntity>()
            .eq("laoshi_id", zuoye.getLaoshiId())
            .eq("zuoye_name", zuoye.getZuoyeName())
            .eq("zuoye_uuid_number", zuoye.getZuoyeUuidNumber())
            .eq("zuoye_types", zuoye.getZuoyeTypes())
            .eq("zuoye_delete", zuoye.getZuoyeDelete())
//            .notIn("zuoye_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZuoyeEntity zuoyeEntity = zuoyeService.selectOne(queryWrapper);
        if(zuoyeEntity==null){
            zuoye.setZuoyeDelete(1);
            zuoye.setInsertTime(new Date());
            zuoye.setCreateTime(new Date());
        zuoyeService.insert(zuoye);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

