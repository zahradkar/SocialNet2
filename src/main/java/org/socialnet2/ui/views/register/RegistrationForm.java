package org.socialnet2.ui.views.register;

import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

import java.util.stream.Stream;

public class RegistrationForm extends FormLayout {

	private final TextField email = new TextField("Email");
	private final PasswordField password = new PasswordField("Password");
	private final PasswordField passwordConfirm = new PasswordField("Confirm password");
	private final Span errorMessageField = new Span();
	private final Button submitButton = new Button("Register!");

	public RegistrationForm() {
		setRequiredIndicatorVisible(password, passwordConfirm);

		submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

		H3 title = new H3("Sign up");
		add(title, email, password, passwordConfirm, errorMessageField, submitButton);

		// Max width of the Form
		setMaxWidth("500px");

		setResponsiveSteps(new ResponsiveStep("0", 1, ResponsiveStep.LabelsPosition.TOP), new ResponsiveStep("490px", 2, ResponsiveStep.LabelsPosition.TOP));

		setColspan(title, 2);
		setColspan(email, 2);
		setColspan(errorMessageField, 2);
		setColspan(submitButton, 2);
	}

	public TextField getEmail() {
		return email;
	}

	public PasswordField getPasswordField() {
		return password;
	}

	public PasswordField getPasswordConfirmField() {
		return passwordConfirm;
	}

	public Span getErrorMessageField() {
		return errorMessageField;
	}

	public Button getSubmitButton() {
		return submitButton;
	}

	private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
		Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
	}
}