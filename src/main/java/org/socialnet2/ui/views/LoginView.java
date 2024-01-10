package org.socialnet2.ui.views;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.socialnet2.backend.security.SecurityUtils;

@Route("login")
@PageTitle("SocialNet | Login")
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver, ComponentEventListener<AbstractLogin.LoginEvent> {

	private static final String LOGIN_SUCCESS_URL = "/";

	private LoginForm login = new LoginForm();

	public LoginView() {
		addClassName("login-view");
		setSizeFull();

		setJustifyContentMode(JustifyContentMode.CENTER);
		setAlignItems(Alignment.CENTER);

		login.addLoginListener(this);

		add(new H1("Test Application"), login);
	}

	@Override
	public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
		if (beforeEnterEvent.getLocation()
				.getQueryParameters()
				.getParameters()
				.containsKey("error")) {
			login.setError(true);
		}
	}

	@Override
	public void onComponentEvent(AbstractLogin.LoginEvent loginEvent) {
		boolean authenticated = SecurityUtils.authenticate(
				loginEvent.getUsername(), loginEvent.getPassword());
		if (authenticated) {
			UI.getCurrent().getPage().setLocation(LOGIN_SUCCESS_URL);
		} else {
			login.setError(true);
		}
	}
}
