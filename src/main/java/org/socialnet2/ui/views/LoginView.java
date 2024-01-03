package org.socialnet2.ui.views;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("login")
@PageTitle("SocialNet | Login")
@AnonymousAllowed
public class LoginView extends LoginOverlay implements BeforeEnterObserver {

	public LoginView() {
		setTitle("SocialNet");
		setDescription("Welcome to best social network ever!");

		var anchorReg = new Anchor("http://localhost:8080/register", "Sign up here.");
		anchorReg.addClassName("font-weight-bold");

		getFooter().add(new Span("Don't have account? "), anchorReg);
		setOpened(true);
		setAction("login");
	}

	@Override
	public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
		if (beforeEnterEvent.getLocation()
				.getQueryParameters()
				.getParameters()
				.containsKey("error")) {
			this.setError(true);
		}
	}
}
