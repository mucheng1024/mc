package cn.charlie.cache;

import java.util.Date;

/**
 * 缓存对象
 * @author charlie
 *
 */
public class CacheItem {
	private Date createTime = new Date(); //创建缓存对象的时间
	private Object entity; //缓存的实体
	
	public CacheItem(Object obj){
		this.entity = obj;
	}
	
	/**
	 * 获得缓存对象的创建时间
	 * @return
	 */
	public Date getCreateTime(){
		return this.createTime;
	}
	
	/**
	 * 重置创建时间为当前访问时间
	 */
	public void setCreateTime(){
		createTime = new Date();
	}
	
	/**
	 * 设置缓存对象
	 * @param obj
	 */
	public void setEntity(Object obj){
		this.entity = obj;
	}
	
	/**
	 * 判断缓存实体是否过期
	 * @param expireTime
	 * @return
	 */
	public boolean isExpired(long expireTime){
		return (System.currentTimeMillis()-createTime.getTime() > expireTime);
	}

	@Override
	public String toString() {
		return entity.toString();
	}	
}
