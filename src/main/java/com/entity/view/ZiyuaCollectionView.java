package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.ZiyuaCollectionEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 教学资源收藏
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("ziyua_collection")
public class ZiyuaCollectionView extends ZiyuaCollectionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 类型的值
	*/
	@ColumnInfo(comment="类型的字典表值",type="varchar(200)")
	private String ziyuaCollectionValue;

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
	//级联表 教学资源
					 
		/**
		* 教学资源 的 老师
		*/
		@ColumnInfo(comment="老师",type="int(11)")
		private Integer ziyuaLaoshiId;
		/**
		* 教学资源名称
		*/

		@ColumnInfo(comment="教学资源名称",type="varchar(200)")
		private String ziyuaName;
		/**
		* 教学资源编号
		*/

		@ColumnInfo(comment="教学资源编号",type="varchar(200)")
		private String ziyuaUuidNumber;
		/**
		* 教学资源照片
		*/

		@ColumnInfo(comment="教学资源照片",type="varchar(200)")
		private String ziyuaPhoto;
		/**
		* 教学资源类型
		*/
		@ColumnInfo(comment="教学资源类型",type="int(11)")
		private Integer ziyuaTypes;
			/**
			* 教学资源类型的值
			*/
			@ColumnInfo(comment="教学资源类型的字典表值",type="varchar(200)")
			private String ziyuaValue;
		/**
		* 资源下载
		*/

		@ColumnInfo(comment="资源下载",type="varchar(200)")
		private String ziyuaFile;
		/**
		* 资源视频
		*/

		@ColumnInfo(comment="资源视频",type="varchar(200)")
		private String ziyuaVideo;
		/**
		* 教学资源介绍
		*/

		@ColumnInfo(comment="教学资源介绍",type="longtext")
		private String ziyuaContent;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer ziyuaDelete;



	public ZiyuaCollectionView() {

	}

	public ZiyuaCollectionView(ZiyuaCollectionEntity ziyuaCollectionEntity) {
		try {
			BeanUtils.copyProperties(this, ziyuaCollectionEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 类型的值
	*/
	public String getZiyuaCollectionValue() {
		return ziyuaCollectionValue;
	}
	/**
	* 设置： 类型的值
	*/
	public void setZiyuaCollectionValue(String ziyuaCollectionValue) {
		this.ziyuaCollectionValue = ziyuaCollectionValue;
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
	//级联表的get和set 教学资源
		/**
		* 获取：教学资源 的 老师
		*/
		public Integer getZiyuaLaoshiId() {
			return ziyuaLaoshiId;
		}
		/**
		* 设置：教学资源 的 老师
		*/
		public void setZiyuaLaoshiId(Integer ziyuaLaoshiId) {
			this.ziyuaLaoshiId = ziyuaLaoshiId;
		}

		/**
		* 获取： 教学资源名称
		*/
		public String getZiyuaName() {
			return ziyuaName;
		}
		/**
		* 设置： 教学资源名称
		*/
		public void setZiyuaName(String ziyuaName) {
			this.ziyuaName = ziyuaName;
		}

		/**
		* 获取： 教学资源编号
		*/
		public String getZiyuaUuidNumber() {
			return ziyuaUuidNumber;
		}
		/**
		* 设置： 教学资源编号
		*/
		public void setZiyuaUuidNumber(String ziyuaUuidNumber) {
			this.ziyuaUuidNumber = ziyuaUuidNumber;
		}

		/**
		* 获取： 教学资源照片
		*/
		public String getZiyuaPhoto() {
			return ziyuaPhoto;
		}
		/**
		* 设置： 教学资源照片
		*/
		public void setZiyuaPhoto(String ziyuaPhoto) {
			this.ziyuaPhoto = ziyuaPhoto;
		}
		/**
		* 获取： 教学资源类型
		*/
		public Integer getZiyuaTypes() {
			return ziyuaTypes;
		}
		/**
		* 设置： 教学资源类型
		*/
		public void setZiyuaTypes(Integer ziyuaTypes) {
			this.ziyuaTypes = ziyuaTypes;
		}


			/**
			* 获取： 教学资源类型的值
			*/
			public String getZiyuaValue() {
				return ziyuaValue;
			}
			/**
			* 设置： 教学资源类型的值
			*/
			public void setZiyuaValue(String ziyuaValue) {
				this.ziyuaValue = ziyuaValue;
			}

		/**
		* 获取： 资源下载
		*/
		public String getZiyuaFile() {
			return ziyuaFile;
		}
		/**
		* 设置： 资源下载
		*/
		public void setZiyuaFile(String ziyuaFile) {
			this.ziyuaFile = ziyuaFile;
		}

		/**
		* 获取： 资源视频
		*/
		public String getZiyuaVideo() {
			return ziyuaVideo;
		}
		/**
		* 设置： 资源视频
		*/
		public void setZiyuaVideo(String ziyuaVideo) {
			this.ziyuaVideo = ziyuaVideo;
		}

		/**
		* 获取： 教学资源介绍
		*/
		public String getZiyuaContent() {
			return ziyuaContent;
		}
		/**
		* 设置： 教学资源介绍
		*/
		public void setZiyuaContent(String ziyuaContent) {
			this.ziyuaContent = ziyuaContent;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getZiyuaDelete() {
			return ziyuaDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setZiyuaDelete(Integer ziyuaDelete) {
			this.ziyuaDelete = ziyuaDelete;
		}


	@Override
	public String toString() {
		return "ZiyuaCollectionView{" +
			", ziyuaCollectionValue=" + ziyuaCollectionValue +
			", ziyuaName=" + ziyuaName +
			", ziyuaUuidNumber=" + ziyuaUuidNumber +
			", ziyuaPhoto=" + ziyuaPhoto +
			", ziyuaFile=" + ziyuaFile +
			", ziyuaVideo=" + ziyuaVideo +
			", ziyuaContent=" + ziyuaContent +
			", ziyuaDelete=" + ziyuaDelete +
			", yonghuUuidNumber=" + yonghuUuidNumber +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuEmail=" + yonghuEmail +
			"} " + super.toString();
	}
}
