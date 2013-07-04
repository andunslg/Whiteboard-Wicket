package com.aslg;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.util.template.PackageTextTemplate;

import java.util.HashMap;
import java.util.Map;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);


		final AbstractDefaultAjaxBehavior behave = new AbstractDefaultAjaxBehavior() {
			protected void respond(final AjaxRequestTarget target) {

				System.out.println(getRequest().getRequestParameters().getParameterValue("json"));

			}

			public void renderHead(Component component,IHeaderResponse response){
				super.renderHead(component,response);
				String componentMarkupId = component.getMarkupId();
				String callbackUrl = getCallbackUrl().toString();

				System.out.println(componentMarkupId);
				System.out.println(callbackUrl);

				response.render(JavaScriptHeaderItem.forScript("var callbackUrl='"+callbackUrl+"';","urlHolder"));
			}
		};

		add(behave);

    }

}
