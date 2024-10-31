package com.entity.model;

import com.entity.ZiyuaEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 教学资源
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ZiyuaModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 老师
     */
    private Integer laoshiId;


    /**
     * 教学资源名称
     */
    private String ziyuaName;


    /**
     * 教学资源编号
     */
    private String ziyuaUuidNumber;


    /**
     * 教学资源照片
     */
    private String ziyuaPhoto;


    /**
     * 教学资源类型
     */
    private Integer ziyuaTypes;


    /**
     * 资源下载
     */
    private String ziyuaFile;


    /**
     * 资源视频
     */
    private String ziyuaVideo;


    /**
     * 教学资源介绍
     */
    private String ziyuaContent;


    /**
     * 逻辑删除
     */
    private Integer ziyuaDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：老师
	 */
    public Integer getLaoshiId() {
        return laoshiId;
    }


    /**
	 * 设置：老师
	 */
    public void setLaoshiId(Integer laoshiId) {
        this.laoshiId = laoshiId;
    }
    /**
	 * 获取：教学资源名称
	 */
    public String getZiyuaName() {
        return ziyuaName;
    }


    /**
	 * 设置：教学资源名称
	 */
    public void setZiyuaName(String ziyuaName) {
        this.ziyuaName = ziyuaName;
    }
    /**
	 * 获取：教学资源编号
	 */
    public String getZiyuaUuidNumber() {
        return ziyuaUuidNumber;
    }


    /**
	 * 设置：教学资源编号
	 */
    public void setZiyuaUuidNumber(String ziyuaUuidNumber) {
        this.ziyuaUuidNumber = ziyuaUuidNumber;
    }
    /**
	 * 获取：教学资源照片
	 */
    public String getZiyuaPhoto() {
        return ziyuaPhoto;
    }


    /**
	 * 设置：教学资源照片
	 */
    public void setZiyuaPhoto(String ziyuaPhoto) {
        this.ziyuaPhoto = ziyuaPhoto;
    }
    /**
	 * 获取：教学资源类型
	 */
    public Integer getZiyuaTypes() {
        return ziyuaTypes;
    }


    /**
	 * 设置：教学资源类型
	 */
    public void setZiyuaTypes(Integer ziyuaTypes) {
        this.ziyuaTypes = ziyuaTypes;
    }
    /**
	 * 获取：资源下载
	 */
    public String getZiyuaFile() {
        return ziyuaFile;
    }


    /**
	 * 设置：资源下载
	 */
    public void setZiyuaFile(String ziyuaFile) {
        this.ziyuaFile = ziyuaFile;
    }
    /**
	 * 获取：资源视频
	 */
    public String getZiyuaVideo() {
        return ziyuaVideo;
    }


    /**
	 * 设置：资源视频
	 */
    public void setZiyuaVideo(String ziyuaVideo) {
        this.ziyuaVideo = ziyuaVideo;
    }
    /**
	 * 获取：教学资源介绍
	 */
    public String getZiyuaContent() {
        return ziyuaContent;
    }


    /**
	 * 设置：教学资源介绍
	 */
    public void setZiyuaContent(String ziyuaContent) {
        this.ziyuaContent = ziyuaContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getZiyuaDelete() {
        return ziyuaDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setZiyuaDelete(Integer ziyuaDelete) {
        this.ziyuaDelete = ziyuaDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：录入时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
