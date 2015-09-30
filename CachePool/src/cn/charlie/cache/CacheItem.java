package cn.charlie.cache;

import java.util.Date;

/**
 * �������
 * @author charlie
 *
 */
public class CacheItem {
	private Date createTime = new Date(); //������������ʱ��
	private Object entity; //�����ʵ��
	
	public CacheItem(Object obj){
		this.entity = obj;
	}
	
	/**
	 * ��û������Ĵ���ʱ��
	 * @return
	 */
	public Date getCreateTime(){
		return this.createTime;
	}
	
	/**
	 * ���ô���ʱ��Ϊ��ǰ����ʱ��
	 */
	public void setCreateTime(){
		createTime = new Date();
	}
	
	/**
	 * ���û������
	 * @param obj
	 */
	public void setEntity(Object obj){
		this.entity = obj;
	}
	
	/**
	 * �жϻ���ʵ���Ƿ����
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
