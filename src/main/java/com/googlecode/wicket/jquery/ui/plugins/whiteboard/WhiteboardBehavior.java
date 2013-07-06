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

import com.googlecode.wicket.jquery.ui.plugins.whiteboard.resource.GoogStyleSheetResourceReference;
import com.googlecode.wicket.jquery.ui.plugins.whiteboard.resource.WhiteboardJavaScriptResourceReference;
import com.googlecode.wicket.jquery.ui.plugins.whiteboard.resource.WhiteboardStyleSheetResourceReference;
import com.googlecode.wicket.jquery.ui.plugins.whiteboard.settings.IWhiteboardLibrarySettings;
import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.PriorityHeaderItem;

public class WhiteboardBehavior extends Behavior{

	public void renderHead(Component component, IHeaderResponse response) {
		 super.renderHead(component,response);
		 initReferences(response);
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
}
