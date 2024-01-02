package org.socialnetvaadin2.ui.views;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("login")
//@CssImport("./login-view.css")
@PageTitle("Login | Vaadin library")
public class LoginView extends Composite<Div> implements BeforeEnterObserver {
	private final LoginOverlay loginOverlay = new LoginOverlay();

	public LoginView() {
		loginOverlay.setTitle("Vaadin's library ;)");
		loginOverlay.setDescription("Basic CRUD ops with books example");
		loginOverlay.setForgotPasswordButtonVisible(false);

		var anchorReg = new Anchor("http://localhost:8080/register", "Sign up here.");
		anchorReg.addClassName("font-weight-bold");


		loginOverlay.getFooter().add(new Span("Don't have account? "), anchorReg);
		getContent().add(loginOverlay);
		loginOverlay.setOpened(true);
		loginOverlay.setAction("login");
	}

	@Override
	public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
		if (beforeEnterEvent.getLocation()
				.getQueryParameters()
				.getParameters()
				.containsKey("error")) {
			loginOverlay.setError(true);
		}
	}
}
