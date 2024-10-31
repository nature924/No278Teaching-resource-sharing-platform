package com.entity.vo;

import com.entity.ZiyuaEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 教学资源
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("ziyua")
public class ZiyuaVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 老师
     */

    @TableField(value = "laoshi_id")
    private Integer laoshiId;


    /**
     * 教学资源名称
     */

    @TableField(value = "ziyua_name")
    private String ziyuaName;


    /**
     * 教学资源编号
     */

    @TableField(value = "ziyua_uuid_number")
    private String ziyuaUuidNumber;


    /**
     * 教学资源照片
     */

    @TableField(value = "ziyua_photo")
    private String ziyuaPhoto;


    /**
     * 教学资源类型
     */

    @TableField(value = "ziyua_types")
    private Integer ziyuaTypes;


    /**
     * 资源下载
     */

    @TableField(value = "ziyua_file")
    private String ziyuaFile;


    /**
     * 资源视频
     */

    @TableField(value = "ziyua_video")
    private String ziyuaVideo;


    /**
     * 教学资源介绍
     */

    @TableField(value = "ziyua_content")
    private String ziyuaContent;


    /**
     * 逻辑删除
     */

    @TableField(value = "ziyua_delete")
    private Integer ziyuaDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：老师
	 */
    public Integer getLaoshiId() {
        return laoshiId;
    }


    /**
	 * 获取：老师
	 */

    public void setLaoshiId(Integer laoshiId) {
        this.laoshiId = laoshiId;
    }
    /**
	 * 设置：教学资源名称
	 */
    public String getZiyuaName() {
        return ziyuaName;
    }


    /**
	 * 获取：教学资源名称
	 */

    public void setZiyuaName(String ziyuaName) {
        this.ziyuaName = ziyuaName;
    }
    /**
	 * 设置：教学资源编号
	 */
    public String getZiyuaUuidNumber() {
        return ziyuaUuidNumber;
    }


    /**
	 * 获取：教学资源编号
	 */

    public void setZiyuaUuidNumber(String ziyuaUuidNumber) {
        this.ziyuaUuidNumber = ziyuaUuidNumber;
    }
    /**
	 * 设置：教学资源照片
	 */
    public String getZiyuaPhoto() {
        return ziyuaPhoto;
    }


    /**
	 * 获取：教学资源照片
	 */

    public void setZiyuaPhoto(String ziyuaPhoto) {
        this.ziyuaPhoto = ziyuaPhoto;
    }
    /**
	 * 设置：教学资源类型
	 */
    public Integer getZiyuaTypes() {
        return ziyuaTypes;
    }


    /**
	 * 获取：教学资源类型
	 */

    public void setZiyuaTypes(Integer ziyuaTypes) {
        this.ziyuaTypes = ziyuaTypes;
    }
    /**
	 * 设置：资源下载
	 */
    public String getZiyuaFile() {
        return ziyuaFile;
    }


    /**
	 * 获取：资源下载
	 */

    public void setZiyuaFile(String ziyuaFile) {
        this.ziyuaFile = ziyuaFile;
    }
    /**
	 * 设置：资源视频
	 */
    public String getZiyuaVideo() {
        return ziyuaVideo;
    }


    /**
	 * 获取：资源视频
	 */

    public void setZiyuaVideo(String ziyuaVideo) {
        this.ziyuaVideo = ziyuaVideo;
    }
    /**
	 * 设置：教学资源介绍
	 */
    public String getZiyuaContent() {
        return ziyuaContent;
    }


    /**
	 * 获取：教学资源介绍
	 */

    public void setZiyuaContent(String ziyuaContent) {
        this.ziyuaContent = ziyuaContent;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getZiyuaDelete() {
        return ziyuaDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setZiyuaDelete(Integer ziyuaDelete) {
        this.ziyuaDelete = ziyuaDelete;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
