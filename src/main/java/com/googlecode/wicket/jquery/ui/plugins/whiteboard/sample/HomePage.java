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
package com.googlecode.wicket.jquery.ui.plugins.whiteboard.sample;

import com.googlecode.wicket.jquery.ui.plugins.whiteboard.WhiteboardBehavior;
import com.googlecode.wicket.jquery.ui.plugins.whiteboard.elements.Element;
import com.googlecode.wicket.jquery.ui.plugins.whiteboard.elements.PointFree;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.json.JSONException;
import org.apache.wicket.ajax.json.JSONObject;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.HashMap;

public class HomePage extends WebPage{
	private static final long serialVersionUID=1L;


	public HomePage(final PageParameters parameters){
		super(parameters);

		WhiteboardBehavior whiteboardBehavior=new WhiteboardBehavior("whiteboard");
		final HashMap<Integer,Element> elementMap=whiteboardBehavior.getElementMap();
		add(whiteboardBehavior);

		final Form<Void> form=new Form<Void>("form");
		this.add(form);

		form.add(new AjaxButton("button1"){

			private static final long serialVersionUID=1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form){
				//Printing the content of Element List
				String elementLsit="[";
				for(int i=0;i<elementMap.keySet().size();i++){
					if(i==0){
						elementLsit+=elementMap.get(i).getJSON();
					}else{
						elementLsit+=","+elementMap.get(i).getJSON();
					}
				}
				elementLsit+="]";
				target.appendJavaScript("alert('"+elementLsit+"')");
			}
		});


		form.add(new AjaxButton("button2"){

			private static final long serialVersionUID=1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form){
				JSONObject jsonObject=null;
				try{
					//Loading Whiteboard with element List
					jsonObject=new JSONObject("{\"id\": 0, \"type\": \"PointFree\", \"x\": -488.0, \"y\": 58.0,\"hidden\": false,\"trace\": false}");
					PointFree pointFree=PointFree.getPointFree(jsonObject);
					String json=pointFree.getJSON();
					target.appendJavaScript(""+
							"elementCollection.acceptJsonStr('"+json+"');"+
							"");
				}catch(JSONException e){
					e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
				}
			}
		});

		form.add(new AjaxButton("button3"){

			private static final long serialVersionUID=1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form){
				target.appendJavaScript(""+
						"elementCollection.parseJson('[{\"id\": 0, \"type\": \"PointFree\", \"x\": -495, \"y\": 44},{\"id\": 1, \"type\": \"PointFree\", \"x\": -334, \"y\": -25},{\"id\": 2, \"type\": \"PencilRect\", \"p1\": 0, \"p2\": 1}]');"+
						"");
			}
		});
	}

}
