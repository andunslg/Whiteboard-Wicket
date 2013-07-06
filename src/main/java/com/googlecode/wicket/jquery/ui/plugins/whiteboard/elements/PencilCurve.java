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

import java.util.ArrayList;

public class PencilCurve extends Element{
	protected int p0;
	protected ArrayList<Double[][]> points;


	public PencilCurve(int id, String label, String color, Boolean hidden, String type, Boolean trace, int p0,ArrayList<Double[][]> points){
		super(id,label,color,hidden,type,trace);
		this.p0=p0;
		this.points=points;
	}

	public static PencilCurve getPencilCurve(JSONObject object) throws JSONException{
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

		int p0=(Integer)object.get("p0");

		int pointCount=0;

		while(true){
			try{
				object.get("x"+pointCount);
				pointCount++;
			}catch(JSONException e){
				break;
			}
		}
		ArrayList<Double[][]> points=new ArrayList<Double[][]>();

		for(int i=0;i<pointCount;i++){
			if(object.get("x"+i) instanceof Double){
				Double  [][] point= {{(Double)object.get("x"+i),(Double)object.get("y"+i)}};
				points.add(point);
			}
			else {
				double x= (Integer)object.get("x"+i);
				double y=(Integer)object.get("y"+i);
				Double  [][] point= {{x,y}};
				points.add(point);
			}

		}

		return  new PencilCurve(id,label,color,hidden,type,trace,p0,points);

	}

	public String getJSON(){
		String jsonString="{\"id\": "+id+", \"type\": \""+type+"\", \"p0\": "+p0+"";

		for(int i=0;i<points.size();i++){
			jsonString=jsonString.concat(",\"x"+i+"\": "+points.get(i)[0][0]+",\"x"+i+"\":"+points.get(i)[0][1]+"");
		}

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

	public ArrayList<Double[][]> getPoints(){
		return points;
	}

	public void setPoints(ArrayList<Double[][]> points){
		this.points=points;
	}

	public int getP0(){
		return p0;
	}

	public void setP0(int p0){
		this.p0=p0;
	}

}