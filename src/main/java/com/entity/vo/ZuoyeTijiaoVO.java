package com.entity.vo;

import com.entity.ZuoyeTijiaoEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 作业提交
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("zuoye_tijiao")
public class ZuoyeTijiaoVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 报名唯一编号
     */

    @TableField(value = "zuoye_tijiao_uuid_number")
    private String zuoyeTijiaoUuidNumber;


    /**
     * 作业
     */

    @TableField(value = "zuoye_id")
    private Integer zuoyeId;


    /**
     * 老师
     */

    @TableField(value = "laoshi_id")
    private Integer laoshiId;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 内容
     */

    @TableField(value = "zuoye_tijiao_text")
    private String zuoyeTijiaoText;


    /**
     * 作业上传
     */

    @TableField(value = "zuoye_shangchuan_file")
    private String zuoyeShangchuanFile;


    /**
     * 提交状态
     */

    @TableField(value = "zuoye_tijiao_yesno_types")
    private Integer zuoyeTijiaoYesnoTypes;


    /**
     * 批改分数
     */

    @TableField(value = "zuoye_tijiao_pigai")
    private Integer zuoyeTijiaoPigai;


    /**
     * 作业提交时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间 show3 listShow
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
	 * 设置：报名唯一编号
	 */
    public String getZuoyeTijiaoUuidNumber() {
        return zuoyeTijiaoUuidNumber;
    }


    /**
	 * 获取：报名唯一编号
	 */

    public void setZuoyeTijiaoUuidNumber(String zuoyeTijiaoUuidNumber) {
        this.zuoyeTijiaoUuidNumber = zuoyeTijiaoUuidNumber;
    }
    /**
	 * 设置：作业
	 */
    public Integer getZuoyeId() {
        return zuoyeId;
    }


    /**
	 * 获取：作业
	 */

    public void setZuoyeId(Integer zuoyeId) {
        this.zuoyeId = zuoyeId;
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
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：内容
	 */
    public String getZuoyeTijiaoText() {
        return zuoyeTijiaoText;
    }


    /**
	 * 获取：内容
	 */

    public void setZuoyeTijiaoText(String zuoyeTijiaoText) {
        this.zuoyeTijiaoText = zuoyeTijiaoText;
    }
    /**
	 * 设置：作业上传
	 */
    public String getZuoyeShangchuanFile() {
        return zuoyeShangchuanFile;
    }


    /**
	 * 获取：作业上传
	 */

    public void setZuoyeShangchuanFile(String zuoyeShangchuanFile) {
        this.zuoyeShangchuanFile = zuoyeShangchuanFile;
    }
    /**
	 * 设置：提交状态
	 */
    public Integer getZuoyeTijiaoYesnoTypes() {
        return zuoyeTijiaoYesnoTypes;
    }


    /**
	 * 获取：提交状态
	 */

    public void setZuoyeTijiaoYesnoTypes(Integer zuoyeTijiaoYesnoTypes) {
        this.zuoyeTijiaoYesnoTypes = zuoyeTijiaoYesnoTypes;
    }
    /**
	 * 设置：批改分数
	 */
    public Integer getZuoyeTijiaoPigai() {
        return zuoyeTijiaoPigai;
    }


    /**
	 * 获取：批改分数
	 */

    public void setZuoyeTijiaoPigai(Integer zuoyeTijiaoPigai) {
        this.zuoyeTijiaoPigai = zuoyeTijiaoPigai;
    }
    /**
	 * 设置：作业提交时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：作业提交时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
