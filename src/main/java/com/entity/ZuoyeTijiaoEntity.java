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
 * 作业提交
 *
 * @author 
 * @email
 */
@TableName("zuoye_tijiao")
public class ZuoyeTijiaoEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ZuoyeTijiaoEntity() {

	}

	public ZuoyeTijiaoEntity(T t) {
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
     * 报名唯一编号
     */
    @ColumnInfo(comment="报名唯一编号",type="varchar(200)")
    @TableField(value = "zuoye_tijiao_uuid_number")

    private String zuoyeTijiaoUuidNumber;


    /**
     * 作业
     */
    @ColumnInfo(comment="作业",type="int(11)")
    @TableField(value = "zuoye_id")

    private Integer zuoyeId;


    /**
     * 老师
     */
    @ColumnInfo(comment="老师",type="int(11)")
    @TableField(value = "laoshi_id")

    private Integer laoshiId;


    /**
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 内容
     */
    @ColumnInfo(comment="内容",type="text")
    @TableField(value = "zuoye_tijiao_text")

    private String zuoyeTijiaoText;


    /**
     * 作业上传
     */
    @ColumnInfo(comment="作业上传",type="varchar(200)")
    @TableField(value = "zuoye_shangchuan_file")

    private String zuoyeShangchuanFile;


    /**
     * 提交状态
     */
    @ColumnInfo(comment="提交状态",type="int(11)")
    @TableField(value = "zuoye_tijiao_yesno_types")

    private Integer zuoyeTijiaoYesnoTypes;


    /**
     * 批改分数
     */
    @ColumnInfo(comment="批改分数",type="int(11)")
    @TableField(value = "zuoye_tijiao_pigai")

    private Integer zuoyeTijiaoPigai;


    /**
     * 作业提交时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="作业提交时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间  listShow
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
	 * 获取：报名唯一编号
	 */
    public String getZuoyeTijiaoUuidNumber() {
        return zuoyeTijiaoUuidNumber;
    }
    /**
	 * 设置：报名唯一编号
	 */

    public void setZuoyeTijiaoUuidNumber(String zuoyeTijiaoUuidNumber) {
        this.zuoyeTijiaoUuidNumber = zuoyeTijiaoUuidNumber;
    }
    /**
	 * 获取：作业
	 */
    public Integer getZuoyeId() {
        return zuoyeId;
    }
    /**
	 * 设置：作业
	 */

    public void setZuoyeId(Integer zuoyeId) {
        this.zuoyeId = zuoyeId;
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
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }
    /**
	 * 设置：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：内容
	 */
    public String getZuoyeTijiaoText() {
        return zuoyeTijiaoText;
    }
    /**
	 * 设置：内容
	 */

    public void setZuoyeTijiaoText(String zuoyeTijiaoText) {
        this.zuoyeTijiaoText = zuoyeTijiaoText;
    }
    /**
	 * 获取：作业上传
	 */
    public String getZuoyeShangchuanFile() {
        return zuoyeShangchuanFile;
    }
    /**
	 * 设置：作业上传
	 */

    public void setZuoyeShangchuanFile(String zuoyeShangchuanFile) {
        this.zuoyeShangchuanFile = zuoyeShangchuanFile;
    }
    /**
	 * 获取：提交状态
	 */
    public Integer getZuoyeTijiaoYesnoTypes() {
        return zuoyeTijiaoYesnoTypes;
    }
    /**
	 * 设置：提交状态
	 */

    public void setZuoyeTijiaoYesnoTypes(Integer zuoyeTijiaoYesnoTypes) {
        this.zuoyeTijiaoYesnoTypes = zuoyeTijiaoYesnoTypes;
    }
    /**
	 * 获取：批改分数
	 */
    public Integer getZuoyeTijiaoPigai() {
        return zuoyeTijiaoPigai;
    }
    /**
	 * 设置：批改分数
	 */

    public void setZuoyeTijiaoPigai(Integer zuoyeTijiaoPigai) {
        this.zuoyeTijiaoPigai = zuoyeTijiaoPigai;
    }
    /**
	 * 获取：作业提交时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：作业提交时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间  listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间  listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ZuoyeTijiao{" +
            ", id=" + id +
            ", zuoyeTijiaoUuidNumber=" + zuoyeTijiaoUuidNumber +
            ", zuoyeId=" + zuoyeId +
            ", laoshiId=" + laoshiId +
            ", yonghuId=" + yonghuId +
            ", zuoyeTijiaoText=" + zuoyeTijiaoText +
            ", zuoyeShangchuanFile=" + zuoyeShangchuanFile +
            ", zuoyeTijiaoYesnoTypes=" + zuoyeTijiaoYesnoTypes +
            ", zuoyeTijiaoPigai=" + zuoyeTijiaoPigai +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
