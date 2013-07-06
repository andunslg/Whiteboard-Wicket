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

public class CircleGeneral extends Element{
	protected double a;
	protected double b;
	protected double c;

	public CircleGeneral(int id, String label, String color, Boolean hidden, String type, Boolean trace, double a, double b, double c){
		super(id,label,color,hidden,type,trace);
		this.a=a;
		this.b=b;
		this.c=c;
	}

	public static CircleGeneral getCircleGeneral(JSONObject object) throws JSONException{
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

		double a=(Integer)object.get("a");

		double b=(Integer)object.get("b");

		double c=(Integer)object.get("c");

		return  new CircleGeneral(id,label,color,hidden,type,trace,a,b,c);

	}

	public String getJSON(){
		String jsonString="{\"id\": "+id+", \"type\": \""+type+"\", \"a\": "+a+", \"b\": "+b+", \"c\": "+c+"";

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

	public double getB(){
		return b;
	}

	public void setB(double b){
		this.b=b;
	}

	public double getA(){
		return a;
	}

	public void setA(double a){
		this.a=a;
	}

	public double getC(){
		return c;
	}

	public void setC(double c){
		this.c=c;
	}

}
