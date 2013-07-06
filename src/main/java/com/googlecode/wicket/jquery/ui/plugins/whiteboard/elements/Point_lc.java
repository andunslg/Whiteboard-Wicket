/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.googlecode.wicket.jquery.ui.plugins.whiteboard.elements;

import org.apache.wicket.ajax.json.JSONException;
import org.apache.wicket.ajax.json.JSONObject;

public class Point_lc extends Element{
	protected int obj1;
	protected int obj2;
	protected int num;

	public Point_lc(int id, String label, String color, Boolean hidden, String type, Boolean trace, int obj1, int obj2, int num){
		super(id,label,color,hidden,type,trace);
		this.obj1=obj1;
		this.obj2=obj2;
		this.num=num;
	}

	public static Point_lc getPoint_lc(JSONObject object) throws JSONException{
		Integer id=(Integer)object.get("id");

		String label=null;
		try{
			label=(String)object.get("lable");
		}catch(JSONException e){
			//Add Error Handling
		}

		String color=null;
		try{
			color=(String)object.get("color");
		}catch(JSONException e){
			//Add Error Handling
		}

		Boolean trace=null;
		try{
			trace=(Boolean)object.get("trace");
		}catch(JSONException e){
			//Add Error Handling
		}

		Boolean hidden=null;
		try{
			hidden=(Boolean)object.get("hidden");
		}catch(JSONException e){
			//Add Error Handling
		}

		String type=(String)object.get("type");

		int obj1=(Integer)object.get("obj1");

		int obj2=(Integer)object.get("obj2");

		int num=(Integer)object.get("num");

		return  new Point_lc(id,label,color,hidden,type,trace,obj1,obj2,num);

	}

	public String getJSON(){
		String jsonString="{\"id\": "+id+", \"type\": \""+type+"\", \"obj1\": "+obj1+", \"obj2\": "+obj2+", \"num\": "+num+"";

		if(label!=null){
			jsonString=jsonString.concat(",\"label\": \""+label+"\"");
		}
		if(color!=null){
			jsonString=jsonString.concat(",\"color\": \""+color+"\"");
		}
		if(hidden!=null){
			jsonString=jsonString.concat(",\"hidden\": "+hidden+"");
		}
		if(trace!=null){
			jsonString=jsonString.concat(",\"trace\": "+trace+"");
		}

		jsonString=jsonString.concat("}");

		return jsonString;
	}

	public int getObj2(){
		return obj2;
	}

	public void setObj2(int obj2){
		this.obj2=obj2;
	}

	public int getObj1(){
		return obj1;
	}

	public void setObj1(int obj1){
		this.obj1=obj1;
	}

	public int getNum(){
		return num;
	}

	public void setNum(int num){
		this.num=num;
	}

}
