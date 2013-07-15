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
package com.googlecode.wicket.jquery.ui.plugins.whiteboard;

import com.googlecode.wicket.jquery.ui.plugins.whiteboard.elements.*;
import com.googlecode.wicket.jquery.ui.plugins.whiteboard.resource.GoogStyleSheetResourceReference;
import com.googlecode.wicket.jquery.ui.plugins.whiteboard.resource.WhiteboardJavaScriptResourceReference;
import com.googlecode.wicket.jquery.ui.plugins.whiteboard.resource.WhiteboardStyleSheetResourceReference;
import com.googlecode.wicket.jquery.ui.plugins.whiteboard.settings.IWhiteboardLibrarySettings;
import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.json.JSONException;
import org.apache.wicket.ajax.json.JSONObject;
import org.apache.wicket.markup.head.*;
import org.apache.wicket.protocol.ws.IWebSocketSettings;
import org.apache.wicket.protocol.ws.api.IWebSocketConnection;
import org.apache.wicket.protocol.ws.api.IWebSocketConnectionRegistry;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.http.WebRequest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WhiteboardBehavior extends AbstractDefaultAjaxBehavior{

	private String whiteboardId;
	private static HashMap<Integer,Element> elementMap=new HashMap<Integer,Element>();

	public WhiteboardBehavior(String whiteboardId){
		super();
		this.whiteboardId=whiteboardId;
	}

	protected void respond(final AjaxRequestTarget target){

		RequestCycle cycle = RequestCycle.get();
		WebRequest webRequest = (WebRequest) cycle.getRequest();
		String editedElement = webRequest.getQueryParameters().getParameterValue("editedElement").toString();

		try{

			//Mapping JSON String to Objects and Adding to the Element List
			Element element=null;
			if(editedElement.contains("PointFree")){
				element=new PointFree(new JSONObject(editedElement));
			}else if(editedElement.contains("PencilCurve")){
				element=new PencilCurve(new JSONObject(editedElement));

			}else if(editedElement.contains("PencilFreeLine")){
				element=new PencilFreeLine(new JSONObject(editedElement));
			}else if(editedElement.contains("PencilRect")){
				element=new PencilRect(new JSONObject(editedElement));
			}else if(editedElement.contains("PencilPointAtRect")){
				element=new PencilPointAtRect(new JSONObject(editedElement));
			}else if(editedElement.contains("PencilCircle")){
				element=new PencilCircle(new JSONObject(editedElement));
			}else if(editedElement.contains("Text")){
				element=new Text(new JSONObject(editedElement));
			}else if(editedElement.contains("PointAtLine")){
				element=new PointAtLine(new JSONObject(editedElement));
			}else if(editedElement.contains("PointAtCircle")){
				element=new PointAtCircle(new JSONObject(editedElement));
			}else if(editedElement.contains("Point_2l")){
				element=new Point_2l(new JSONObject(editedElement));
			}else if(editedElement.contains("Point_2c")){
				element=new Point_2c(new JSONObject(editedElement));
			}else if(editedElement.contains("Point_lc")){
				element=new Point_lc(new JSONObject(editedElement));
			}else if(editedElement.contains("LineGeneral")){
				element=new LineGeneral(new JSONObject(editedElement));
			}else if(editedElement.contains("Line_2p")){
				element=new Line_2p(new JSONObject(editedElement));
			}else if(editedElement.contains("Segment")){
				element=new Segment(new JSONObject(editedElement));
			}else if(editedElement.contains("CircleGeneral")){
				element=new CircleGeneral(new JSONObject(editedElement));
			}else if(editedElement.contains("Circle_3p")){
				element=new Circle_3p(new JSONObject(editedElement));
			}

			if(element!=null){
				elementMap.put(element.getId(),element);

				IWebSocketConnectionRegistry reg = IWebSocketSettings.Holder.get(Application.get()).getConnectionRegistry();
				for (IWebSocketConnection c : reg.getConnections(Application.get())) {
					try {
						JSONObject jsonObject=new JSONObject(editedElement);
						c.sendMessage(getMessage(jsonObject).toString());
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}

		}catch(JSONException e){
			e.printStackTrace();
		}
	}

	private JSONObject getMessage(JSONObject element) throws JSONException {
		return new JSONObject()
				.put("type", "wbElement")
				.put("json", element);
	}

	public void renderHead(Component component, IHeaderResponse response) {
		super.renderHead(component,response);
		initReferences(response);
		String componentMarkupId=component.getMarkupId();
		String callbackUrl=getCallbackUrl().toString();
		String whiteboardInitializeScript="" +
				"whiteboard = bay.whiteboard.Create();\n" +
				"elementCollection=whiteboard.getMainCollection();"+
				"whiteboard.getMainCollection().onChange = function(element){\n"+
				"changedElement=this.getJson(element);\n"+
				"Wicket.Ajax.get({u:'"+callbackUrl+"',ep:{editedElement:changedElement}});\n};\n"+
				"whiteboard.render(document.getElementById('"+whiteboardId+"'));";

		if(!elementMap.isEmpty()){
			String elementList="[";
			Iterator iterator = elementMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry mapEntry = (Map.Entry) iterator.next();
				Element element=(Element)mapEntry.getValue();

				if(mapEntry.getKey().equals(0)){
					elementList+=element.getJSON();
				}
				else{
					elementList+=","+element.getJSON();
				}
			}
			elementList+="]";
			whiteboardInitializeScript+="elementCollection.parseJson('"+elementList+"');";
		}

		response.render(OnDomReadyHeaderItem.forScript(whiteboardInitializeScript));
	}


	private void initReferences(IHeaderResponse response){
		IWhiteboardLibrarySettings settings = getLibrarySettings();

//Whiteboard.css
		if (settings != null && settings.getWhiteboardStyleSheetReference() != null)
		{
			response.render(new PriorityHeaderItem(CssHeaderItem.forReference(settings.getWhiteboardStyleSheetReference())));
		}
		else
		{
			response.render(new PriorityHeaderItem(CssHeaderItem.forReference(WhiteboardStyleSheetResourceReference.get())));
		}

//Goog.css
		if (settings != null && settings.getGoogStyleSheetReference() != null)
		{
			response.render(new PriorityHeaderItem(CssHeaderItem.forReference(settings.getGoogStyleSheetReference())));
		}
		else
		{
			response.render(new PriorityHeaderItem(CssHeaderItem.forReference(GoogStyleSheetResourceReference.get())));
		}


//Whiteboard.js
		if (settings != null && settings.getWhiteboardJavaScriptReference() != null)
		{
			response.render(new PriorityHeaderItem(JavaScriptHeaderItem.forReference(settings.getWhiteboardJavaScriptReference())));
		}
		else
		{
			response.render(new PriorityHeaderItem(JavaScriptHeaderItem.forReference(WhiteboardJavaScriptResourceReference.get())));
		}

	}

	private static IWhiteboardLibrarySettings getLibrarySettings()
	{
		if (Application.exists() && (Application.get().getJavaScriptLibrarySettings() instanceof IWhiteboardLibrarySettings))
		{
			return (IWhiteboardLibrarySettings) Application.get().getJavaScriptLibrarySettings();
		}

		return null;
	}

	public HashMap<Integer,Element> getElementMap(){
		return elementMap;
	}

	public void setElementMap(HashMap<Integer,Element> elementMap){
		this.elementMap=elementMap;
	}
}
