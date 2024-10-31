package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.ZuoyeTijiaoEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 作业提交
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("zuoye_tijiao")
public class ZuoyeTijiaoView extends ZuoyeTijiaoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 提交状态的值
	*/
	@ColumnInfo(comment="提交状态的字典表值",type="varchar(200)")
	private String zuoyeTijiaoYesnoValue;

	//级联表 老师
		/**
		* 老师编号
		*/

		@ColumnInfo(comment="老师编号",type="varchar(200)")
		private String laoshiUuidNumber;
		/**
		* 老师姓名
		*/

		@ColumnInfo(comment="老师姓名",type="varchar(200)")
		private String laoshiName;
		/**
		* 老师手机号
		*/

		@ColumnInfo(comment="老师手机号",type="varchar(200)")
		private String laoshiPhone;
		/**
		* 老师身份证号
		*/

		@ColumnInfo(comment="老师身份证号",type="varchar(200)")
		private String laoshiIdNumber;
		/**
		* 老师头像
		*/

		@ColumnInfo(comment="老师头像",type="varchar(200)")
		private String laoshiPhoto;
		/**
		* 老师邮箱
		*/

		@ColumnInfo(comment="老师邮箱",type="varchar(200)")
		private String laoshiEmail;
	//级联表 用户
		/**
		* 用户编号
		*/

		@ColumnInfo(comment="用户编号",type="varchar(200)")
		private String yonghuUuidNumber;
		/**
		* 用户姓名
		*/

		@ColumnInfo(comment="用户姓名",type="varchar(200)")
		private String yonghuName;
		/**
		* 用户手机号
		*/

		@ColumnInfo(comment="用户手机号",type="varchar(200)")
		private String yonghuPhone;
		/**
		* 用户身份证号
		*/

		@ColumnInfo(comment="用户身份证号",type="varchar(200)")
		private String yonghuIdNumber;
		/**
		* 用户头像
		*/

		@ColumnInfo(comment="用户头像",type="varchar(200)")
		private String yonghuPhoto;
		/**
		* 用户邮箱
		*/

		@ColumnInfo(comment="用户邮箱",type="varchar(200)")
		private String yonghuEmail;
	//级联表 作业
					 
		/**
		* 作业 的 老师
		*/
		@ColumnInfo(comment="老师",type="int(11)")
		private Integer zuoyeLaoshiId;
		/**
		* 作业名称
		*/

		@ColumnInfo(comment="作业名称",type="varchar(200)")
		private String zuoyeName;
		/**
		* 作业编号
		*/

		@ColumnInfo(comment="作业编号",type="varchar(200)")
		private String zuoyeUuidNumber;
		/**
		* 作业封面
		*/

		@ColumnInfo(comment="作业封面",type="varchar(200)")
		private String zuoyePhoto;
		/**
		* 作业类型
		*/
		@ColumnInfo(comment="作业类型",type="int(11)")
		private Integer zuoyeTypes;
			/**
			* 作业类型的值
			*/
			@ColumnInfo(comment="作业类型的字典表值",type="varchar(200)")
			private String zuoyeValue;
		/**
		* 作业下载
		*/

		@ColumnInfo(comment="作业下载",type="varchar(200)")
		private String zuoyeFile;
		/**
		* 作业介绍
		*/

		@ColumnInfo(comment="作业介绍",type="text")
		private String zuoyeContent;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer zuoyeDelete;

	//重复字段
			/**
			* 重复字段 的types
			*/
			@ColumnInfo(comment="重复字段 的types",type="Integer")
			private Integer sexTypes;
				@ColumnInfo(comment="重复字段 的值",type="varchar(200)")
				private String sexValue;


	public ZuoyeTijiaoView() {

	}

	public ZuoyeTijiaoView(ZuoyeTijiaoEntity zuoyeTijiaoEntity) {
		try {
			BeanUtils.copyProperties(this, zuoyeTijiaoEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 提交状态的值
	*/
	public String getZuoyeTijiaoYesnoValue() {
		return zuoyeTijiaoYesnoValue;
	}
	/**
	* 设置： 提交状态的值
	*/
	public void setZuoyeTijiaoYesnoValue(String zuoyeTijiaoYesnoValue) {
		this.zuoyeTijiaoYesnoValue = zuoyeTijiaoYesnoValue;
	}


	//级联表的get和set 老师

		/**
		* 获取： 老师编号
		*/
		public String getLaoshiUuidNumber() {
			return laoshiUuidNumber;
		}
		/**
		* 设置： 老师编号
		*/
		public void setLaoshiUuidNumber(String laoshiUuidNumber) {
			this.laoshiUuidNumber = laoshiUuidNumber;
		}

		/**
		* 获取： 老师姓名
		*/
		public String getLaoshiName() {
			return laoshiName;
		}
		/**
		* 设置： 老师姓名
		*/
		public void setLaoshiName(String laoshiName) {
			this.laoshiName = laoshiName;
		}

		/**
		* 获取： 老师手机号
		*/
		public String getLaoshiPhone() {
			return laoshiPhone;
		}
		/**
		* 设置： 老师手机号
		*/
		public void setLaoshiPhone(String laoshiPhone) {
			this.laoshiPhone = laoshiPhone;
		}

		/**
		* 获取： 老师身份证号
		*/
		public String getLaoshiIdNumber() {
			return laoshiIdNumber;
		}
		/**
		* 设置： 老师身份证号
		*/
		public void setLaoshiIdNumber(String laoshiIdNumber) {
			this.laoshiIdNumber = laoshiIdNumber;
		}

		/**
		* 获取： 老师头像
		*/
		public String getLaoshiPhoto() {
			return laoshiPhoto;
		}
		/**
		* 设置： 老师头像
		*/
		public void setLaoshiPhoto(String laoshiPhoto) {
			this.laoshiPhoto = laoshiPhoto;
		}

		/**
		* 获取： 老师邮箱
		*/
		public String getLaoshiEmail() {
			return laoshiEmail;
		}
		/**
		* 设置： 老师邮箱
		*/
		public void setLaoshiEmail(String laoshiEmail) {
			this.laoshiEmail = laoshiEmail;
		}
	//级联表的get和set 用户

		/**
		* 获取： 用户编号
		*/
		public String getYonghuUuidNumber() {
			return yonghuUuidNumber;
		}
		/**
		* 设置： 用户编号
		*/
		public void setYonghuUuidNumber(String yonghuUuidNumber) {
			this.yonghuUuidNumber = yonghuUuidNumber;
		}

		/**
		* 获取： 用户姓名
		*/
		public String getYonghuName() {
			return yonghuName;
		}
		/**
		* 设置： 用户姓名
		*/
		public void setYonghuName(String yonghuName) {
			this.yonghuName = yonghuName;
		}

		/**
		* 获取： 用户手机号
		*/
		public String getYonghuPhone() {
			return yonghuPhone;
		}
		/**
		* 设置： 用户手机号
		*/
		public void setYonghuPhone(String yonghuPhone) {
			this.yonghuPhone = yonghuPhone;
		}

		/**
		* 获取： 用户身份证号
		*/
		public String getYonghuIdNumber() {
			return yonghuIdNumber;
		}
		/**
		* 设置： 用户身份证号
		*/
		public void setYonghuIdNumber(String yonghuIdNumber) {
			this.yonghuIdNumber = yonghuIdNumber;
		}

		/**
		* 获取： 用户头像
		*/
		public String getYonghuPhoto() {
			return yonghuPhoto;
		}
		/**
		* 设置： 用户头像
		*/
		public void setYonghuPhoto(String yonghuPhoto) {
			this.yonghuPhoto = yonghuPhoto;
		}

		/**
		* 获取： 用户邮箱
		*/
		public String getYonghuEmail() {
			return yonghuEmail;
		}
		/**
		* 设置： 用户邮箱
		*/
		public void setYonghuEmail(String yonghuEmail) {
			this.yonghuEmail = yonghuEmail;
		}
	//级联表的get和set 作业
		/**
		* 获取：作业 的 老师
		*/
		public Integer getZuoyeLaoshiId() {
			return zuoyeLaoshiId;
		}
		/**
		* 设置：作业 的 老师
		*/
		public void setZuoyeLaoshiId(Integer zuoyeLaoshiId) {
			this.zuoyeLaoshiId = zuoyeLaoshiId;
		}

		/**
		* 获取： 作业名称
		*/
		public String getZuoyeName() {
			return zuoyeName;
		}
		/**
		* 设置： 作业名称
		*/
		public void setZuoyeName(String zuoyeName) {
			this.zuoyeName = zuoyeName;
		}

		/**
		* 获取： 作业编号
		*/
		public String getZuoyeUuidNumber() {
			return zuoyeUuidNumber;
		}
		/**
		* 设置： 作业编号
		*/
		public void setZuoyeUuidNumber(String zuoyeUuidNumber) {
			this.zuoyeUuidNumber = zuoyeUuidNumber;
		}

		/**
		* 获取： 作业封面
		*/
		public String getZuoyePhoto() {
			return zuoyePhoto;
		}
		/**
		* 设置： 作业封面
		*/
		public void setZuoyePhoto(String zuoyePhoto) {
			this.zuoyePhoto = zuoyePhoto;
		}
		/**
		* 获取： 作业类型
		*/
		public Integer getZuoyeTypes() {
			return zuoyeTypes;
		}
		/**
		* 设置： 作业类型
		*/
		public void setZuoyeTypes(Integer zuoyeTypes) {
			this.zuoyeTypes = zuoyeTypes;
		}


			/**
			* 获取： 作业类型的值
			*/
			public String getZuoyeValue() {
				return zuoyeValue;
			}
			/**
			* 设置： 作业类型的值
			*/
			public void setZuoyeValue(String zuoyeValue) {
				this.zuoyeValue = zuoyeValue;
			}

		/**
		* 获取： 作业下载
		*/
		public String getZuoyeFile() {
			return zuoyeFile;
		}
		/**
		* 设置： 作业下载
		*/
		public void setZuoyeFile(String zuoyeFile) {
			this.zuoyeFile = zuoyeFile;
		}

		/**
		* 获取： 作业介绍
		*/
		public String getZuoyeContent() {
			return zuoyeContent;
		}
		/**
		* 设置： 作业介绍
		*/
		public void setZuoyeContent(String zuoyeContent) {
			this.zuoyeContent = zuoyeContent;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getZuoyeDelete() {
			return zuoyeDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setZuoyeDelete(Integer zuoyeDelete) {
			this.zuoyeDelete = zuoyeDelete;
		}

	//重复字段
			/**
			* 获取： 重复字段 的types
			*/
			public Integer getSexTypes() {
			return sexTypes;
			}
			/**
			* 设置： 重复字段 的types
			*/
			public void setSexTypes(Integer sexTypes) {
			this.sexTypes = sexTypes;
			}
				public String getSexValue() {
				return sexValue;
				}
				public void setSexValue(String sexValue) {
				this.sexValue = sexValue;
				}

	@Override
	public String toString() {
		return "ZuoyeTijiaoView{" +
			", zuoyeTijiaoYesnoValue=" + zuoyeTijiaoYesnoValue +
			", zuoyeName=" + zuoyeName +
			", zuoyeUuidNumber=" + zuoyeUuidNumber +
			", zuoyePhoto=" + zuoyePhoto +
			", zuoyeFile=" + zuoyeFile +
			", zuoyeContent=" + zuoyeContent +
			", zuoyeDelete=" + zuoyeDelete +
			", laoshiUuidNumber=" + laoshiUuidNumber +
			", laoshiName=" + laoshiName +
			", laoshiPhone=" + laoshiPhone +
			", laoshiIdNumber=" + laoshiIdNumber +
			", laoshiPhoto=" + laoshiPhoto +
			", laoshiEmail=" + laoshiEmail +
			", yonghuUuidNumber=" + yonghuUuidNumber +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuEmail=" + yonghuEmail +
			"} " + super.toString();
	}
}
