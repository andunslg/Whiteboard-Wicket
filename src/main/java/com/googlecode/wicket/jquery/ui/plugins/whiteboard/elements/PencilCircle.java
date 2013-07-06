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

public class PencilCircle extends Element{
	protected int p1;
	protected int p2;

	public PencilCircle(int id, String label, String color, Boolean hidden, String type, Boolean trace, int p1, int p2){
		super(id,label,color,hidden,type,trace);
		this.p1=p1;
		this.p2=p2;
	}

	public static PencilCircle getPencilCircle(JSONObject object) throws JSONException{
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

		int p1=(Integer)object.get("p1");

		int p2=(Integer)object.get("p2");


		return  new PencilCircle(id,label,color,hidden,type,trace,p1,p2);

	}

	public String getJSON(){
		String jsonString="{\"id\": "+id+", \"type\": \""+type+"\", \"p1\": "+p1+", \"p2\": "+p2+"";

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

	public int getP2(){
		return p2;
	}

	public void setP2(int p2){
		this.p2=p2;
	}

	public int getP1(){
		return p1;
	}

	public void setP1(int p1){
		this.p1=p1;
	}

}
