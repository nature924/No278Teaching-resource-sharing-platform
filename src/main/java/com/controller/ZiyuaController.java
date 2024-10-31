
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
 * 教学资源
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/ziyua")
public class ZiyuaController {
    private static final Logger logger = LoggerFactory.getLogger(ZiyuaController.class);

    private static final String TABLE_NAME = "ziyua";

    @Autowired
    private ZiyuaService ziyuaService;


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
    private ZiyuaCollectionService ziyuaCollectionService;//教学资源收藏
    @Autowired
    private ZiyuaLiuyanService ziyuaLiuyanService;//教学资源留言
    @Autowired
    private ZuoyeService zuoyeService;//作业
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
        params.put("ziyuaDeleteStart",1);params.put("ziyuaDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = ziyuaService.queryPage(params);

        //字典表数据转换
        List<ZiyuaView> list =(List<ZiyuaView>)page.getList();
        for(ZiyuaView c:list){
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
        ZiyuaEntity ziyua = ziyuaService.selectById(id);
        if(ziyua !=null){
            //entity转view
            ZiyuaView view = new ZiyuaView();
            BeanUtils.copyProperties( ziyua , view );//把实体数据重构到view中
            //级联表 老师
            //级联表
            LaoshiEntity laoshi = laoshiService.selectById(ziyua.getLaoshiId());
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
    public R save(@RequestBody ZiyuaEntity ziyua, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,ziyua:{}",this.getClass().getName(),ziyua.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("老师".equals(role))
            ziyua.setLaoshiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<ZiyuaEntity> queryWrapper = new EntityWrapper<ZiyuaEntity>()
            .eq("laoshi_id", ziyua.getLaoshiId())
            .eq("ziyua_name", ziyua.getZiyuaName())
            .eq("ziyua_types", ziyua.getZiyuaTypes())
            .eq("ziyua_video", ziyua.getZiyuaVideo())
            .eq("ziyua_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZiyuaEntity ziyuaEntity = ziyuaService.selectOne(queryWrapper);
        if(ziyuaEntity==null){
            ziyua.setZiyuaDelete(1);
            ziyua.setInsertTime(new Date());
            ziyua.setCreateTime(new Date());
            ziyuaService.insert(ziyua);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ZiyuaEntity ziyua, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,ziyua:{}",this.getClass().getName(),ziyua.toString());
        ZiyuaEntity oldZiyuaEntity = ziyuaService.selectById(ziyua.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("老师".equals(role))
//            ziyua.setLaoshiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(ziyua.getZiyuaPhoto()) || "null".equals(ziyua.getZiyuaPhoto())){
                ziyua.setZiyuaPhoto(null);
        }
        if("".equals(ziyua.getZiyuaFile()) || "null".equals(ziyua.getZiyuaFile())){
                ziyua.setZiyuaFile(null);
        }
        if("".equals(ziyua.getZiyuaVideo()) || "null".equals(ziyua.getZiyuaVideo())){
                ziyua.setZiyuaVideo(null);
        }
        if("".equals(ziyua.getZiyuaContent()) || "null".equals(ziyua.getZiyuaContent())){
                ziyua.setZiyuaContent(null);
        }

            ziyuaService.updateById(ziyua);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<ZiyuaEntity> oldZiyuaList =ziyuaService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<ZiyuaEntity> list = new ArrayList<>();
        for(Integer id:ids){
            ZiyuaEntity ziyuaEntity = new ZiyuaEntity();
            ziyuaEntity.setId(id);
            ziyuaEntity.setZiyuaDelete(2);
            list.add(ziyuaEntity);
        }
        if(list != null && list.size() >0){
            ziyuaService.updateBatchById(list);
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
            List<ZiyuaEntity> ziyuaList = new ArrayList<>();//上传的东西
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
                            ZiyuaEntity ziyuaEntity = new ZiyuaEntity();
//                            ziyuaEntity.setLaoshiId(Integer.valueOf(data.get(0)));   //老师 要改的
//                            ziyuaEntity.setZiyuaName(data.get(0));                    //教学资源名称 要改的
//                            ziyuaEntity.setZiyuaUuidNumber(data.get(0));                    //教学资源编号 要改的
//                            ziyuaEntity.setZiyuaPhoto("");//详情和图片
//                            ziyuaEntity.setZiyuaTypes(Integer.valueOf(data.get(0)));   //教学资源类型 要改的
//                            ziyuaEntity.setZiyuaFile(data.get(0));                    //资源下载 要改的
//                            ziyuaEntity.setZiyuaVideo(data.get(0));                    //资源视频 要改的
//                            ziyuaEntity.setZiyuaContent("");//详情和图片
//                            ziyuaEntity.setZiyuaDelete(1);//逻辑删除字段
//                            ziyuaEntity.setInsertTime(date);//时间
//                            ziyuaEntity.setCreateTime(date);//时间
                            ziyuaList.add(ziyuaEntity);


                            //把要查询是否重复的字段放入map中
                                //教学资源编号
                                if(seachFields.containsKey("ziyuaUuidNumber")){
                                    List<String> ziyuaUuidNumber = seachFields.get("ziyuaUuidNumber");
                                    ziyuaUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> ziyuaUuidNumber = new ArrayList<>();
                                    ziyuaUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("ziyuaUuidNumber",ziyuaUuidNumber);
                                }
                        }

                        //查询是否重复
                         //教学资源编号
                        List<ZiyuaEntity> ziyuaEntities_ziyuaUuidNumber = ziyuaService.selectList(new EntityWrapper<ZiyuaEntity>().in("ziyua_uuid_number", seachFields.get("ziyuaUuidNumber")).eq("ziyua_delete", 1));
                        if(ziyuaEntities_ziyuaUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ZiyuaEntity s:ziyuaEntities_ziyuaUuidNumber){
                                repeatFields.add(s.getZiyuaUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [教学资源编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        ziyuaService.insertBatch(ziyuaList);
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
    * 个性推荐
    */
    @IgnoreAuth
    @RequestMapping("/gexingtuijian")
    public R gexingtuijian(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("gexingtuijian方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        CommonUtil.checkMap(params);
        List<ZiyuaView> returnZiyuaViewList = new ArrayList<>();

        //查看收藏
        Map<String, Object> params1 = new HashMap<>(params);params1.put("sort","id");params1.put("yonghuId",request.getSession().getAttribute("userId"));
        params1.put("shangxiaTypes",1);
        params1.put("ziyuaYesnoTypes",2);
        PageUtils pageUtils = ziyuaCollectionService.queryPage(params1);
        List<ZiyuaCollectionView> collectionViewsList =(List<ZiyuaCollectionView>)pageUtils.getList();
        Map<Integer,Integer> typeMap=new HashMap<>();//购买的类型list
        for(ZiyuaCollectionView collectionView:collectionViewsList){
            Integer ziyuaTypes = collectionView.getZiyuaTypes();
            if(typeMap.containsKey(ziyuaTypes)){
                typeMap.put(ziyuaTypes,typeMap.get(ziyuaTypes)+1);
            }else{
                typeMap.put(ziyuaTypes,1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for(Integer type:typeList){
            Map<String, Object> params2 = new HashMap<>(params);params2.put("ziyuaTypes",type);
            params2.put("shangxiaTypes",1);
            params2.put("ziyuaYesnoTypes",2);
            PageUtils pageUtils1 = ziyuaService.queryPage(params2);
            List<ZiyuaView> ziyuaViewList =(List<ZiyuaView>)pageUtils1.getList();
            returnZiyuaViewList.addAll(ziyuaViewList);
            if(returnZiyuaViewList.size()>= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        params.put("shangxiaTypes",1);
        params.put("ziyuaYesnoTypes",2);
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = ziyuaService.queryPage(params);
        if(returnZiyuaViewList.size()<limit){//返回数量还是小于要求数量
            int toAddNum = limit - returnZiyuaViewList.size();//要添加的数量
            List<ZiyuaView> ziyuaViewList =(List<ZiyuaView>)page.getList();
            for(ZiyuaView ziyuaView:ziyuaViewList){
                Boolean addFlag = true;
                for(ZiyuaView returnZiyuaView:returnZiyuaViewList){
                    if(returnZiyuaView.getId().intValue() ==ziyuaView.getId().intValue()) addFlag=false;//返回的数据中已存在此商品
                }
                if(addFlag){
                    toAddNum=toAddNum-1;
                    returnZiyuaViewList.add(ziyuaView);
                    if(toAddNum==0) break;//够数量了
                }
            }
        }else {
            returnZiyuaViewList = returnZiyuaViewList.subList(0, limit);
        }

        for(ZiyuaView c:returnZiyuaViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnZiyuaViewList);
        return R.ok().put("data", page);
    }

    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = ziyuaService.queryPage(params);

        //字典表数据转换
        List<ZiyuaView> list =(List<ZiyuaView>)page.getList();
        for(ZiyuaView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ZiyuaEntity ziyua = ziyuaService.selectById(id);
            if(ziyua !=null){


                //entity转view
                ZiyuaView view = new ZiyuaView();
                BeanUtils.copyProperties( ziyua , view );//把实体数据重构到view中

                //级联表
                    LaoshiEntity laoshi = laoshiService.selectById(ziyua.getLaoshiId());
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
    public R add(@RequestBody ZiyuaEntity ziyua, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,ziyua:{}",this.getClass().getName(),ziyua.toString());
        Wrapper<ZiyuaEntity> queryWrapper = new EntityWrapper<ZiyuaEntity>()
            .eq("laoshi_id", ziyua.getLaoshiId())
            .eq("ziyua_name", ziyua.getZiyuaName())
            .eq("ziyua_uuid_number", ziyua.getZiyuaUuidNumber())
            .eq("ziyua_types", ziyua.getZiyuaTypes())
            .eq("ziyua_video", ziyua.getZiyuaVideo())
            .eq("ziyua_delete", ziyua.getZiyuaDelete())
//            .notIn("ziyua_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZiyuaEntity ziyuaEntity = ziyuaService.selectOne(queryWrapper);
        if(ziyuaEntity==null){
            ziyua.setZiyuaDelete(1);
            ziyua.setInsertTime(new Date());
            ziyua.setCreateTime(new Date());
        ziyuaService.insert(ziyua);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

