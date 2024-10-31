package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 教学资源
 *
 * @author 
 * @email
 */
@TableName("ziyua")
public class ZiyuaEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ZiyuaEntity() {

	}

	public ZiyuaEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 老师
     */
    @ColumnInfo(comment="老师",type="int(11)")
    @TableField(value = "laoshi_id")

    private Integer laoshiId;


    /**
     * 教学资源名称
     */
    @ColumnInfo(comment="教学资源名称",type="varchar(200)")
    @TableField(value = "ziyua_name")

    private String ziyuaName;


    /**
     * 教学资源编号
     */
    @ColumnInfo(comment="教学资源编号",type="varchar(200)")
    @TableField(value = "ziyua_uuid_number")

    private String ziyuaUuidNumber;


    /**
     * 教学资源照片
     */
    @ColumnInfo(comment="教学资源照片",type="varchar(200)")
    @TableField(value = "ziyua_photo")

    private String ziyuaPhoto;


    /**
     * 教学资源类型
     */
    @ColumnInfo(comment="教学资源类型",type="int(11)")
    @TableField(value = "ziyua_types")

    private Integer ziyuaTypes;


    /**
     * 资源下载
     */
    @ColumnInfo(comment="资源下载",type="varchar(200)")
    @TableField(value = "ziyua_file")

    private String ziyuaFile;


    /**
     * 资源视频
     */
    @ColumnInfo(comment="资源视频",type="varchar(200)")
    @TableField(value = "ziyua_video")

    private String ziyuaVideo;


    /**
     * 教学资源介绍
     */
    @ColumnInfo(comment="教学资源介绍",type="longtext")
    @TableField(value = "ziyua_content")

    private String ziyuaContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "ziyua_delete")

    private Integer ziyuaDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Ziyua{" +
            ", id=" + id +
            ", laoshiId=" + laoshiId +
            ", ziyuaName=" + ziyuaName +
            ", ziyuaUuidNumber=" + ziyuaUuidNumber +
            ", ziyuaPhoto=" + ziyuaPhoto +
            ", ziyuaTypes=" + ziyuaTypes +
            ", ziyuaFile=" + ziyuaFile +
            ", ziyuaVideo=" + ziyuaVideo +
            ", ziyuaContent=" + ziyuaContent +
            ", ziyuaDelete=" + ziyuaDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
