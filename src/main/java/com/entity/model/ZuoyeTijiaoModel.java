package com.entity.model;

import com.entity.ZuoyeTijiaoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 作业提交
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ZuoyeTijiaoModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 报名唯一编号
     */
    private String zuoyeTijiaoUuidNumber;


    /**
     * 作业
     */
    private Integer zuoyeId;


    /**
     * 老师
     */
    private Integer laoshiId;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 内容
     */
    private String zuoyeTijiaoText;


    /**
     * 作业上传
     */
    private String zuoyeShangchuanFile;


    /**
     * 提交状态
     */
    private Integer zuoyeTijiaoYesnoTypes;


    /**
     * 批改分数
     */
    private Integer zuoyeTijiaoPigai;


    /**
     * 作业提交时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间 show3 listShow
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
	 * 获取：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3 listShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
