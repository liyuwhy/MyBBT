package com.bbt.main.bean;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

@AVClassName("OrderCase")
public class OrderCase extends AVObject{


	// 订单（订单id，类型，状态，目的地址，发单者id，接单者id,浏览次数，跑腿金额，发生时间，结束时间,付款方式）	
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
