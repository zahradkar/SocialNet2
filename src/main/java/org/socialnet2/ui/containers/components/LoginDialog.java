package org.socialnet2.ui.containers.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import org.socialnet2.backend.services.UserService;
import org.socialnet2.ui.views.register.RegistrationForm;
import org.socialnet2.ui.views.register.RegistrationFormBinder;

public class LoginDialog extends Dialog {
	public LoginDialog(UserService userService) {
		var i18n = LoginI18n.createDefault();
		i18n.getForm().setUsername("E-mail");
		i18n.getErrorMessage().setUsername("E-mail is required!");
		// TODO improve login form

		var loginForm = new LoginForm();
		loginForm.setI18n(i18n);
		loginForm.setAction("login");
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

//		var anchorReg = new Anchor("http://localhost:8080/register", "Registrácia");
//		anchorReg.addClassName("font-weight-bold");
//		i18n.setAdditionalInformation(anchorReg);

		getFooter().add(registerBtn);

		RegistrationFormBinder registrationFormBinder = new RegistrationFormBinder(registerForm, userService);
		registrationFormBinder.addBindingAndValidation();
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