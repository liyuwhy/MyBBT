package com.bbt.main.bean;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

@AVClassName("OrderCase")
public class OrderCase extends AVObject{


	// ����������id�����ͣ�״̬��Ŀ�ĵ�ַ��������id���ӵ���id,������������Ƚ�����ʱ�䣬����ʱ��,���ʽ��	
    public static final String ORDER_TYPE = "order_type";
    public static final String ORDER_STATUS = "order_status";
    public static final String ADDRESS = "address";
    public static final String User_ID_Send = "user_id_send";
    public static final String User_ID_Receive = "user_id_receive";
    public static final String COUNT = "order_count";
    public static final String MONEY = "order_money";
    public static final String Start_time = "start_time";
    public static final String End_time = "end_time";
    public static final String MoneyType = "money_type";
      
    
    public void setType(String type){
    	put(ORDER_TYPE, type);
    }
    
    public String getType(){
    	return getString(ORDER_TYPE);
    }
    
    public void setStatus(int status){
    	put(ORDER_STATUS,status);
    }
    
    public int getStatus(){
    	return getInt(ORDER_STATUS);
    }
    
    
    public void setAddress(String addressInfo){
    	put(ADDRESS,addressInfo);
    }
    
    public String getAddress(){
    	return getString(ADDRESS);
    }
    
     
    
     
}
