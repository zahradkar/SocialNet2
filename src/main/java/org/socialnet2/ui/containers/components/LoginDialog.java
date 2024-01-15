package org.socialnet2.ui.containers.components;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.server.VaadinSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socialnet2.backend.security.SecurityUtils;
import org.socialnet2.ui.views.MainView;
import org.socialnet2.ui.views.register.RegistrationForm;
import org.socialnet2.ui.views.register.RegistrationFormBinder;

public class LoginDialog extends Dialog implements BeforeEnterObserver, ComponentEventListener<AbstractLogin.LoginEvent> {
	private static final String LOGIN_SUCCESS_URL = "/";
	private static final Logger logger = LoggerFactory.getLogger(LoginDialog.class);
	private LoginForm loginForm;

	public LoginDialog() {
		var i18n = LoginI18n.createDefault();
		i18n.getForm().setUsername("E-mail");
		i18n.getErrorMessage().setUsername("E-mail is required!");
		// TODO improve login form

		loginForm = new LoginForm();
		loginForm.setI18n(i18n);
//		loginForm.setAction("login");
		loginForm.addLoginListener(this);
		add(loginForm);

		var registerForm = new RegistrationForm();

		var registerBtn = new Button("Sign up here");
		var loginBtn = new Button("Login");
		registerBtn.addClickListener(buttonClickEvent -> {
			remove(loginForm);
			getFooter().remove(registerBtn);
			add(registerForm);
			getFooter().add(loginBtn);
		});

		loginBtn.addClickListener(buttonClickEvent -> {
			remove(registerForm);
			getFooter().remove(loginBtn);
			add(loginForm);
			getFooter().add(registerBtn);
		});

		getFooter().add(registerBtn);

		RegistrationFormBinder registrationFormBinder = new RegistrationFormBinder(registerForm);
		registrationFormBinder.addBindingAndValidation();
	}

	@Override
	public void onComponentEvent(AbstractLogin.LoginEvent loginEvent) {
		boolean authenticated = SecurityUtils.authenticate(loginEvent.getUsername(), loginEvent.getPassword());
		if (!authenticated)
			loginForm.setError(true);
		else {
			loadUserData(loginEvent.getUsername());
			this.close();
			Notification.show("Welcome!");
//			UI.getCurrent().getPage().fetchCurrentURL(url -> Notification.show("Current URL: " + url));
//			UI.getCurrent().getPage().setLocation(LOGIN_SUCCESS_URL);
		}
	}

	private void loadUserData(String userId) {
		// todo update retrieving user from DB (in this method user is retrieved twice - update to retrieve it just once)
		var user = MainView.userService.getUserDTO(userId);
		var session = VaadinSession.getCurrent();
		session.setAttribute(UserInfoForm.USER, userId);
		session.setAttribute(UserInfoForm.FIRST_NAME, user.firstName());
		session.setAttribute(UserInfoForm.LAST_NAME, user.lastName());
		session.setAttribute(UserInfoForm.PROFILE_PICTURE, user.profilePictureURL());
		session.setAttribute(UserInfoForm.BIRTHDAY, user.birthday());
		session.setAttribute(UserInfoForm.LOCATION, user.location());

		// code below loads colors of vote icons after successful login
		int offset = 0;
		if (MainColumn.instance.getComponentAt(MainColumn.instance.getComponentCount() - 1) instanceof PresentationPostsView) // checks if at the bottom of main column is added PresentationPostsView
			offset = 1; // offset is 1 if there is PresentationPostsView added in main column else 0
		int index = MainColumn.instance.getLoadedPostsFromDB().size();
		var user1 = MainView.userService.getUserEntity(userId);
//		logger.info("count of loaded posts: " + MainColumn.instance.getLoadedPostsFromDB().size());
//		logger.info("component count: " + MainColumn.instance.getComponentCount());
		for (int i = 1; i < MainColumn.instance.getComponentCount() - offset; i++) {
			var postBackend = MainColumn.instance.getLoadedPostsFromDB().get(--index);
			var postFrontend = (MediaObject) MainColumn.instance.getComponentAt(i);
//			logger.info(postBackend.getId() + "");
//			logger.info(MainColumn.instance.getComponentAt(i).getClass().getName());
			if (postBackend.getDislikedByUsers().contains(user1))
				postFrontend.getVotesComponent().getBtnSecondary().getIcon().addClassName("thumbdown-red");
			if (postBackend.getLikedByUsers().contains(user1))
				postFrontend.getVotesComponent().getBtnPrimary().getIcon().addClassName("thumbup-green");
		}
	}

	@Override
	public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
		if (beforeEnterEvent.getLocation()
				.getQueryParameters()
				.getParameters()
				.containsKey("error")) {
			loginForm.setError(true);
		}
	}


	/*public LoginDialog() {
		var i18n = LoginI18n.createDefault();
		i18n.getForm().setTitle("Prihlásenie");
		i18n.getForm().setUsername("E-pošta");
		i18n.getForm().setPassword("Heslo");
		i18n.getForm().setSubmit("Prihlásiť");
		i18n.getForm().setForgotPassword("Zabudol/la som heslo");
		i18n.getErrorMessage().setUsername("Zadaj adresu e-pošty!");
		i18n.getErrorMessage().setPassword("Zadaj heslo!");
//		i18n.setAdditionalInformation("nejake doplnujuce info");

		var login = new LoginForm();
		login.setI18n(i18n);
		add(login);

		var anchorReg = new Anchor("http://localhost:8080/register", "Registrácia");
		anchorReg.addClassName("font-weight-bold");
		getFooter().add(anchorReg);
	}*/
}