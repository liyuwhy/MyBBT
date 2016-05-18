package com.bbt.main.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Order_Activity extends Activity{
	
	
	private Spinner spinner_01=null;
	
	private List<String> data_list1;
	
	//private View view;
	 ArrayAdapter<String> spinner_01Adapter=null;//������

	private String[] strArray;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_desigin_order);
		
		//view=(View)findViewById(R.id.textview01);
		spinner_01=(Spinner) findViewById(R.id.spinner01);
		
		
	    strArray  = getResources().getStringArray(R.array.str_array);
		
	    data_list1= new ArrayList<String>();
	    Collections.addAll(data_list1, strArray);
		 //����
      

        
		
		//����ѡ������ArrayAdapter��������
		spinner_01Adapter=new ArrayAdapter<String> (this,android.R.layout.simple_spinner_item,data_list1);
		
		//��adapter��ӵ�spinner��
		spinner_01.setAdapter(spinner_01Adapter);
		
        //������ʽ
		spinner_01Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		}
	
	
	
	
	
	

}
