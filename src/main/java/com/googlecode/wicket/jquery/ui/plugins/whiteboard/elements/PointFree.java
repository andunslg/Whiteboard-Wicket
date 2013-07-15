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

public class PointFree extends Element{
	protected double x;	//	x coordinate of the point
	protected double y;	//	y coordinate of the point

	public PointFree(int id, String label, String color, Boolean hidden, String type, Boolean trace,double x, double y){
		this.id=id;
		this.label=label;
		this.color=color;
		this.hidden=hidden;
		this.type=type;
		this.trace=trace;
		this.x=x;
		this.y=y;
	}

	public PointFree(JSONObject object) throws JSONException{
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

		double x=0.0;
		if(object.get("x") instanceof Double){
			x=(Double)object.get("x");
		}
		else{
			x=(Integer)object.get("x");
		}

		double y=0.0;
		if(object.get("y") instanceof Double){
			y=(Double)object.get("y");
		}
		else{
			y=(Integer)object.get("y");
		}

		this.id=id;
		this.label=label;
		this.color=color;
		this.hidden=hidden;
		this.type=type;
		this.trace=trace;
		this.x=x;
		this.y=y;

	}

	public String getJSON(){
		JSONObject jsonObject=new JSONObject();
		try{
			jsonObject.put("id",id);
			jsonObject.put("type",type);
			jsonObject.put("x",x);
			jsonObject.put("y",y);
			if(label!=null){
				jsonObject.put("label",label);
			}
			if(color!=null){
				jsonObject.put("color",color);
			}
			if(hidden!=null){
				jsonObject.put("hidden",hidden);
			}
			if(trace!=null){
				jsonObject.put("trace",trace);
			}

		}catch(JSONException e){
			e.printStackTrace();
		}

		return jsonObject.toString();
	}

	public double getX(){
		return x;
	}

	public void setX(double x){
		this.x=x;
	}

	public double getY(){
		return y;
	}

	public void setY(double y){
		this.y=y;
	}
}
