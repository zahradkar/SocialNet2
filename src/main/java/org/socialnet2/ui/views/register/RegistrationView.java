package org.socialnet2.ui.views.register;

import org.socialnet2.backend.services.UserService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("register")
@AnonymousAllowed
@PageTitle("Register | SocialNet")
public class RegistrationView extends VerticalLayout {

	public RegistrationView() {
		RegistrationForm registrationForm = new RegistrationForm();
		// Center the RegistrationForm
		setSizeFull();
		setHorizontalComponentAlignment(Alignment.CENTER, registrationForm);
		setJustifyContentMode(JustifyContentMode.CENTER);

		add(registrationForm);

		RegistrationFormBinder registrationFormBinder = new RegistrationFormBinder(registrationForm);
		registrationFormBinder.addBindingAndValidation();
	}
}
