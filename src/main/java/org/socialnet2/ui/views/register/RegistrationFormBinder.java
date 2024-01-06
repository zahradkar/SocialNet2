package org.socialnet2.ui.views.register;

import org.socialnet2.backend.entities.User;
import org.socialnet2.backend.services.UserService;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.ValueContext;
import org.socialnet2.ui.views.MainView;

public class RegistrationFormBinder {
	private final RegistrationForm registrationForm;
	private boolean enablePasswordValidation; // Flag for disabling first run for password validation

	public RegistrationFormBinder(RegistrationForm registrationForm) {
		this.registrationForm = registrationForm;
	}

	public void addBindingAndValidation() { //Method to add the data binding and validation logics to the registration form
		BeanValidationBinder<User> binder = new BeanValidationBinder<>(User.class);
		binder.bindInstanceFields(registrationForm);

		binder.forField(registrationForm.getPasswordField()).withValidator(this::passwordValidator).bind("password");

		registrationForm.getPasswordConfirmField().addValueChangeListener(e -> {
			enablePasswordValidation = true;
			binder.validate();
		});

		binder.setStatusLabel(registrationForm.getErrorMessageField());

		registrationForm.getSubmitButton().addClickListener(event -> {
			User userBean = new User(); // Create empty bean to store the details into
			binder.writeBeanIfValid(userBean); // Run validators and write the values to the bean

			try {
				MainView.userService.add(userBean.getEmail(), userBean.getPassword());
				showSuccess(userBean);
			} catch (Exception e) {
				showFail(e.getMessage());
			}
		});
	}

	private ValidationResult passwordValidator(String pass1, ValueContext ctx) {
		// todo improve
		final byte MIN_PASS_LENGTH = 4;
		if (pass1.length() < MIN_PASS_LENGTH)
			return ValidationResult.error("Password should be at least " + MIN_PASS_LENGTH + " characters long");

		if (!enablePasswordValidation) {
			// user hasn't visited the field yet, so don't validate just yet, but next time.
			enablePasswordValidation = true;
			return ValidationResult.ok();
		}

		String pass2 = registrationForm.getPasswordConfirmField().getValue();

		if (pass1.equals(pass2))
			return ValidationResult.ok();

		return ValidationResult.error("Passwords do not match");
	}

	private void showSuccess(User userBean) { //We call this method when form submission has succeeded
		Notification notification = Notification.show("Data saved, welcome " + userBean.getEmail());
		notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
		// TODO redirect the user to another view
	}

	private void showFail(String exception) {
		Notification notification = Notification.show(exception);
		notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
	}
}
