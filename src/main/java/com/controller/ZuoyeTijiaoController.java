
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
 * 作业提交
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/zuoyeTijiao")
public class ZuoyeTijiaoController {
    private static final Logger logger = LoggerFactory.getLogger(ZuoyeTijiaoController.class);

    private static final String TABLE_NAME = "zuoyeTijiao";

    @Autowired
    private ZuoyeTijiaoService zuoyeTijiaoService;


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
    private ZuoyeService zuoyeService;//作业
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
        CommonUtil.checkMap(params);
        PageUtils page = zuoyeTijiaoService.queryPage(params);

        //字典表数据转换
        List<ZuoyeTijiaoView> list =(List<ZuoyeTijiaoView>)page.getList();
        for(ZuoyeTijiaoView c:list){
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
        ZuoyeTijiaoEntity zuoyeTijiao = zuoyeTijiaoService.selectById(id);
        if(zuoyeTijiao !=null){
            //entity转view
            ZuoyeTijiaoView view = new ZuoyeTijiaoView();
            BeanUtils.copyProperties( zuoyeTijiao , view );//把实体数据重构到view中
            //级联表 作业
            //级联表
            ZuoyeEntity zuoye = zuoyeService.selectById(zuoyeTijiao.getZuoyeId());
            if(zuoye != null){
            BeanUtils.copyProperties( zuoye , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"
, "laoshiId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setZuoyeId(zuoye.getId());
            }
            //级联表 老师
            //级联表
            LaoshiEntity laoshi = laoshiService.selectById(zuoyeTijiao.getLaoshiId());
            if(laoshi != null){
            BeanUtils.copyProperties( laoshi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"
, "laoshiId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setLaoshiId(laoshi.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(zuoyeTijiao.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"
, "laoshiId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
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
    public R save(@RequestBody ZuoyeTijiaoEntity zuoyeTijiao, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,zuoyeTijiao:{}",this.getClass().getName(),zuoyeTijiao.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            zuoyeTijiao.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        else if("老师".equals(role))
            zuoyeTijiao.setLaoshiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<ZuoyeTijiaoEntity> queryWrapper = new EntityWrapper<ZuoyeTijiaoEntity>()
            .eq("zuoye_id", zuoyeTijiao.getZuoyeId())
            .eq("laoshi_id", zuoyeTijiao.getLaoshiId())
            .eq("yonghu_id", zuoyeTijiao.getYonghuId())
            .in("zuoye_tijiao_yesno_types", new Integer[]{1,2})
            .eq("zuoye_tijiao_pigai", zuoyeTijiao.getZuoyeTijiaoPigai())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZuoyeTijiaoEntity zuoyeTijiaoEntity = zuoyeTijiaoService.selectOne(queryWrapper);
        if(zuoyeTijiaoEntity==null){
            zuoyeTijiao.setZuoyeTijiaoYesnoTypes(1);
            zuoyeTijiao.setInsertTime(new Date());
            zuoyeTijiao.setCreateTime(new Date());
            zuoyeTijiaoService.insert(zuoyeTijiao);
            return R.ok();
        }else {
            if(zuoyeTijiaoEntity.getZuoyeTijiaoYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(zuoyeTijiaoEntity.getZuoyeTijiaoYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ZuoyeTijiaoEntity zuoyeTijiao, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,zuoyeTijiao:{}",this.getClass().getName(),zuoyeTijiao.toString());
        ZuoyeTijiaoEntity oldZuoyeTijiaoEntity = zuoyeTijiaoService.selectById(zuoyeTijiao.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            zuoyeTijiao.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
//        else if("老师".equals(role))
//            zuoyeTijiao.setLaoshiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(zuoyeTijiao.getZuoyeTijiaoText()) || "null".equals(zuoyeTijiao.getZuoyeTijiaoText())){
                zuoyeTijiao.setZuoyeTijiaoText(null);
        }
        if("".equals(zuoyeTijiao.getZuoyeShangchuanFile()) || "null".equals(zuoyeTijiao.getZuoyeShangchuanFile())){
                zuoyeTijiao.setZuoyeShangchuanFile(null);
        }

            zuoyeTijiaoService.updateById(zuoyeTijiao);//根据id更新
            return R.ok();
    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody ZuoyeTijiaoEntity zuoyeTijiaoEntity, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,zuoyeTijiaoEntity:{}",this.getClass().getName(),zuoyeTijiaoEntity.toString());

        ZuoyeTijiaoEntity oldZuoyeTijiao = zuoyeTijiaoService.selectById(zuoyeTijiaoEntity.getId());//查询原先数据

//        if(zuoyeTijiaoEntity.getZuoyeTijiaoYesnoTypes() == 2){//通过
//            zuoyeTijiaoEntity.setZuoyeTijiaoTypes();
//        }else if(zuoyeTijiaoEntity.getZuoyeTijiaoYesnoTypes() == 3){//拒绝
//            zuoyeTijiaoEntity.setZuoyeTijiaoTypes();
//        }
        zuoyeTijiaoService.updateById(zuoyeTijiaoEntity);//审核

        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<ZuoyeTijiaoEntity> oldZuoyeTijiaoList =zuoyeTijiaoService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        zuoyeTijiaoService.deleteBatchIds(Arrays.asList(ids));

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
            List<ZuoyeTijiaoEntity> zuoyeTijiaoList = new ArrayList<>();//上传的东西
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
                            ZuoyeTijiaoEntity zuoyeTijiaoEntity = new ZuoyeTijiaoEntity();
//                            zuoyeTijiaoEntity.setZuoyeTijiaoUuidNumber(data.get(0));                    //报名唯一编号 要改的
//                            zuoyeTijiaoEntity.setZuoyeId(Integer.valueOf(data.get(0)));   //作业 要改的
//                            zuoyeTijiaoEntity.setLaoshiId(Integer.valueOf(data.get(0)));   //老师 要改的
//                            zuoyeTijiaoEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            zuoyeTijiaoEntity.setZuoyeTijiaoText(data.get(0));                    //内容 要改的
//                            zuoyeTijiaoEntity.setZuoyeShangchuanFile(data.get(0));                    //作业上传 要改的
//                            zuoyeTijiaoEntity.setZuoyeTijiaoYesnoTypes(Integer.valueOf(data.get(0)));   //提交状态 要改的
//                            zuoyeTijiaoEntity.setZuoyeTijiaoPigai(Integer.valueOf(data.get(0)));   //批改分数 要改的
//                            zuoyeTijiaoEntity.setInsertTime(date);//时间
//                            zuoyeTijiaoEntity.setCreateTime(date);//时间
                            zuoyeTijiaoList.add(zuoyeTijiaoEntity);


                            //把要查询是否重复的字段放入map中
                                //报名唯一编号
                                if(seachFields.containsKey("zuoyeTijiaoUuidNumber")){
                                    List<String> zuoyeTijiaoUuidNumber = seachFields.get("zuoyeTijiaoUuidNumber");
                                    zuoyeTijiaoUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> zuoyeTijiaoUuidNumber = new ArrayList<>();
                                    zuoyeTijiaoUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("zuoyeTijiaoUuidNumber",zuoyeTijiaoUuidNumber);
                                }
                        }

                        //查询是否重复
                         //报名唯一编号
                        List<ZuoyeTijiaoEntity> zuoyeTijiaoEntities_zuoyeTijiaoUuidNumber = zuoyeTijiaoService.selectList(new EntityWrapper<ZuoyeTijiaoEntity>().in("zuoye_tijiao_uuid_number", seachFields.get("zuoyeTijiaoUuidNumber")));
                        if(zuoyeTijiaoEntities_zuoyeTijiaoUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ZuoyeTijiaoEntity s:zuoyeTijiaoEntities_zuoyeTijiaoUuidNumber){
                                repeatFields.add(s.getZuoyeTijiaoUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [报名唯一编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        zuoyeTijiaoService.insertBatch(zuoyeTijiaoList);
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
        PageUtils page = zuoyeTijiaoService.queryPage(params);

        //字典表数据转换
        List<ZuoyeTijiaoView> list =(List<ZuoyeTijiaoView>)page.getList();
        for(ZuoyeTijiaoView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ZuoyeTijiaoEntity zuoyeTijiao = zuoyeTijiaoService.selectById(id);
            if(zuoyeTijiao !=null){


                //entity转view
                ZuoyeTijiaoView view = new ZuoyeTijiaoView();
                BeanUtils.copyProperties( zuoyeTijiao , view );//把实体数据重构到view中

                //级联表
                    ZuoyeEntity zuoye = zuoyeService.selectById(zuoyeTijiao.getZuoyeId());
                if(zuoye != null){
                    BeanUtils.copyProperties( zuoye , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"
, "laoshiId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setZuoyeId(zuoye.getId());
                }
                //级联表
                    LaoshiEntity laoshi = laoshiService.selectById(zuoyeTijiao.getLaoshiId());
                if(laoshi != null){
                    BeanUtils.copyProperties( laoshi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"
, "laoshiId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setLaoshiId(laoshi.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(zuoyeTijiao.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"
, "laoshiId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
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
    public R add(@RequestBody ZuoyeTijiaoEntity zuoyeTijiao, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,zuoyeTijiao:{}",this.getClass().getName(),zuoyeTijiao.toString());
        Wrapper<ZuoyeTijiaoEntity> queryWrapper = new EntityWrapper<ZuoyeTijiaoEntity>()
            .eq("zuoye_tijiao_uuid_number", zuoyeTijiao.getZuoyeTijiaoUuidNumber())
            .eq("zuoye_id", zuoyeTijiao.getZuoyeId())
            .eq("laoshi_id", zuoyeTijiao.getLaoshiId())
            .eq("yonghu_id", zuoyeTijiao.getYonghuId())
            .eq("zuoye_tijiao_text", zuoyeTijiao.getZuoyeTijiaoText())
            .in("zuoye_tijiao_yesno_types", new Integer[]{1,2})
            .eq("zuoye_tijiao_pigai", zuoyeTijiao.getZuoyeTijiaoPigai())
//            .notIn("zuoye_tijiao_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZuoyeTijiaoEntity zuoyeTijiaoEntity = zuoyeTijiaoService.selectOne(queryWrapper);
        if(zuoyeTijiaoEntity==null){
            zuoyeTijiao.setZuoyeTijiaoYesnoTypes(1);
            zuoyeTijiao.setInsertTime(new Date());
            zuoyeTijiao.setCreateTime(new Date());
        zuoyeTijiaoService.insert(zuoyeTijiao);

            return R.ok();
        }else {
            if(zuoyeTijiaoEntity.getZuoyeTijiaoYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(zuoyeTijiaoEntity.getZuoyeTijiaoYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

}

