/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.googlecode.wicket.jquery.ui.plugins.whiteboard;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.protocol.ws.api.WebSocketBehavior;
import org.apache.wicket.protocol.ws.api.message.ClosedMessage;
import org.apache.wicket.protocol.ws.api.message.ConnectedMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Whiteboard extends Panel{
	public  static final Logger log =   LoggerFactory.getLogger(Whiteboard.class);
	private static final long serialVersionUID = 1L;

	public Whiteboard(String id){
		super(id);

		this.add(new WebSocketBehavior(){
			private static final long serialVersionUID=-3311970325911992958L;

			@Override
			protected void onConnect(ConnectedMessage message){
				super.onConnect(message);
				log.debug("Connecting :"+message.toString());
			}

			@Override
			protected void onClose(ClosedMessage message){
				super.onClose(message);
				log.debug("Disconnecting :"+message.toString());
			}
		});

		WebMarkupContainer whiteboard=new WebMarkupContainer("whiteboard");
		this.add(whiteboard);

		WhiteboardBehavior whiteboardBehavior=new WhiteboardBehavior("whiteboard");
		this.add(whiteboardBehavior);
	}
}
