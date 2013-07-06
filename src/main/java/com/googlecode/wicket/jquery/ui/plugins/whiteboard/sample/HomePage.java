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
import com.googlecode.wicket.jquery.ui.plugins.whiteboard.elements.*;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.json.JSONException;
import org.apache.wicket.ajax.json.JSONObject;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.HashMap;

public class HomePage extends WebPage{
	private static final long serialVersionUID=1L;
	private HashMap<Integer,Element> elementMap=new HashMap<Integer,Element>();

	public HomePage(final PageParameters parameters){
		super(parameters);

		add(new WhiteboardBehavior());

		final AbstractDefaultAjaxBehavior behave=new AbstractDefaultAjaxBehavior(){
			protected void respond(final AjaxRequestTarget target){
				String editedElement=String.valueOf(getRequest().getRequestParameters().getParameterValue("editedElement"));
				String elementList=String.valueOf(getRequest().getRequestParameters().getParameterValue("elementList"));
				System.out.println(elementList);
				try{

					//Mapping JSON String to Objects and Adding to the Element List
					if(editedElement.contains("PointFree")){
						PointFree pointFree=PointFree.getPointFree(new JSONObject(editedElement));
						if(!elementMap.containsKey(pointFree.getId())){
							elementMap.put(pointFree.getId(),pointFree);
						}else{
							elementMap.remove(pointFree.getId());
							elementMap.put(pointFree.getId(),pointFree);
						}
					}else if(editedElement.contains("PencilCurve")){
						PencilCurve pencilCurve=PencilCurve.getPencilCurve(new JSONObject(editedElement));
						if(!elementMap.containsKey(pencilCurve.getId())){
							elementMap.put(pencilCurve.getId(),pencilCurve);
						}else{
							elementMap.remove(pencilCurve.getId());
							elementMap.put(pencilCurve.getId(),pencilCurve);
						}
					}else if(editedElement.contains("PencilFreeLine")){
						PencilFreeLine pencilFreeLine=PencilFreeLine.getPencilFreeLine(new JSONObject(editedElement));
						if(!elementMap.containsKey(pencilFreeLine.getId())){
							elementMap.put(pencilFreeLine.getId(),pencilFreeLine);
						}else{
							elementMap.remove(pencilFreeLine.getId());
							elementMap.put(pencilFreeLine.getId(),pencilFreeLine);
						}
					}else if(editedElement.contains("PencilRect")){
						PencilRect pencilRect=PencilRect.getPencilRect(new JSONObject(editedElement));
						if(!elementMap.containsKey(pencilRect.getId())){
							elementMap.put(pencilRect.getId(),pencilRect);
						}else{
							elementMap.remove(pencilRect.getId());
							elementMap.put(pencilRect.getId(),pencilRect);
						}
					}else if(editedElement.contains("PencilPointAtRect")){
						PencilPointAtRect pencilPointAtRect=PencilPointAtRect.getPencilPointAtRect(new JSONObject(editedElement));
						if(!elementMap.containsKey(pencilPointAtRect.getId())){
							elementMap.put(pencilPointAtRect.getId(),pencilPointAtRect);
						}else{
							elementMap.remove(pencilPointAtRect.getId());
							elementMap.put(pencilPointAtRect.getId(),pencilPointAtRect);
						}
					}else if(editedElement.contains("PencilCircle")){
						PencilCircle pencilCircle=PencilCircle.getPencilCircle(new JSONObject(editedElement));
						if(!elementMap.containsKey(pencilCircle.getId())){
							elementMap.put(pencilCircle.getId(),pencilCircle);
						}else{
							elementMap.remove(pencilCircle.getId());
							elementMap.put(pencilCircle.getId(),pencilCircle);
						}
					}else if(editedElement.contains("Text")){
						Text text=Text.getText(new JSONObject(editedElement));
						if(!elementMap.containsKey(text.getId())){
							elementMap.put(text.getId(),text);
						}else{
							elementMap.remove(text.getId());
							elementMap.put(text.getId(),text);
						}
					}else if(editedElement.contains("PointAtLine")){
						PointAtLine pointAtLine=PointAtLine.getPointAtLine(new JSONObject(editedElement));
						if(!elementMap.containsKey(pointAtLine.getId())){
							elementMap.put(pointAtLine.getId(),pointAtLine);
						}else{
							elementMap.remove(pointAtLine.getId());
							elementMap.put(pointAtLine.getId(),pointAtLine);
						}
					}else if(editedElement.contains("PointAtCircle")){
						PointAtCircle pointAtCircle=PointAtCircle.getPointAtCircle(new JSONObject(editedElement));
						if(!elementMap.containsKey(pointAtCircle.getId())){
							elementMap.put(pointAtCircle.getId(),pointAtCircle);
						}else{
							elementMap.remove(pointAtCircle.getId());
							elementMap.put(pointAtCircle.getId(),pointAtCircle);
						}
					}else if(editedElement.contains("Point_2l")){
						Point_2l point_2l=Point_2l.getPoint_2l(new JSONObject(editedElement));
						if(!elementMap.containsKey(point_2l.getId())){
							elementMap.put(point_2l.getId(),point_2l);
						}else{
							elementMap.remove(point_2l.getId());
							elementMap.put(point_2l.getId(),point_2l);
						}
					}else if(editedElement.contains("Point_2c")){
						Point_2c point_2c=Point_2c.getPoint_2c(new JSONObject(editedElement));
						if(!elementMap.containsKey(point_2c.getId())){
							elementMap.put(point_2c.getId(),point_2c);
						}else{
							elementMap.remove(point_2c.getId());
							elementMap.put(point_2c.getId(),point_2c);
						}
					}else if(editedElement.contains("Point_lc")){
						Point_lc point_lc=Point_lc.getPoint_lc(new JSONObject(editedElement));
						if(!elementMap.containsKey(point_lc.getId())){
							elementMap.put(point_lc.getId(),point_lc);
						}else{
							elementMap.remove(point_lc.getId());
							elementMap.put(point_lc.getId(),point_lc);
						}
					}else if(editedElement.contains("LineGeneral")){
						LineGeneral lineGeneral=LineGeneral.getLineGeneral(new JSONObject(editedElement));
						if(!elementMap.containsKey(lineGeneral.getId())){
							elementMap.put(lineGeneral.getId(),lineGeneral);
						}else{
							elementMap.remove(lineGeneral.getId());
							elementMap.put(lineGeneral.getId(),lineGeneral);
						}
					}else if(editedElement.contains("Line_2p")){
						Line_2p line_2p=Line_2p.getLine_2p(new JSONObject(editedElement));
						if(!elementMap.containsKey(line_2p.getId())){
							elementMap.put(line_2p.getId(),line_2p);
						}else{
							elementMap.remove(line_2p.getId());
							elementMap.put(line_2p.getId(),line_2p);
						}
					}else if(editedElement.contains("Segment")){
						Segment segment=Segment.getSegment(new JSONObject(editedElement));
						if(!elementMap.containsKey(segment.getId())){
							elementMap.put(segment.getId(),segment);
						}else{
							elementMap.remove(segment.getId());
							elementMap.put(segment.getId(),segment);
						}
					}else if(editedElement.contains("CircleGeneral")){
						CircleGeneral circleGeneral=CircleGeneral.getCircleGeneral(new JSONObject(editedElement));
						if(!elementMap.containsKey(circleGeneral.getId())){
							elementMap.put(circleGeneral.getId(),circleGeneral);
						}else{
							elementMap.remove(circleGeneral.getId());
							elementMap.put(circleGeneral.getId(),circleGeneral);
						}
					}else if(editedElement.contains("Circle_3p")){
						Circle_3p circle_3p=Circle_3p.getCircle_3p(new JSONObject(editedElement));
						if(!elementMap.containsKey(circle_3p.getId())){
							elementMap.put(circle_3p.getId(),circle_3p);
						}else{
							elementMap.remove(circle_3p.getId());
							elementMap.put(circle_3p.getId(),circle_3p);
						}
					}

				}catch(JSONException e){
					e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
				}
			}

			public void renderHead(Component component, IHeaderResponse response){
				super.renderHead(component,response);
				String componentMarkupId=component.getMarkupId();
				String callbackUrl=getCallbackUrl().toString();

				response.render(JavaScriptHeaderItem.forScript("var callbackUrl='"+callbackUrl+"';","urlHolder"));
			}
		};

		add(behave);

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
							"collection.acceptJsonStr('"+json+"');"+
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
						"javascript: collection.parseJson('[{\"id\": 0, \"type\": \"PointFree\", \"x\": -495, \"y\": 44},{\"id\": 1, \"type\": \"PointFree\", \"x\": -334, \"y\": -25},{\"id\": 2, \"type\": \"PencilRect\", \"p1\": 0, \"p2\": 1}]');"+
						"");
			}
		});
	}

}
