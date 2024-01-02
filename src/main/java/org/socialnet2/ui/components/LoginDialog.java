package org.socialnet2.ui.components;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.login.LoginForm;

public class LoginDialog extends Dialog {
	public LoginDialog() {
//		var form = new LoginForm();
		add(new LoginForm());
	}
}
