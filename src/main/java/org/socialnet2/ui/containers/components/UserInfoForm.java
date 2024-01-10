package org.socialnet2.ui.containers.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socialnet2.backend.security.SecurityUtils;
import org.socialnet2.ui.containers.Header;
import org.socialnet2.ui.views.MainView;

import java.time.LocalDate;

@Route("info")
@AnonymousAllowed
@Getter
public class UserInfoForm extends Dialog {
	private static final Logger logger = LoggerFactory.getLogger(UserInfoForm.class);
	// todo update class (provisional made)
	private final TextField firstNameTField = new TextField("first name");
	private final TextField lastNameTField = new TextField("last name");
	private final TextField emailTField = new TextField("e-mail");
	private final TextField profilePictureUrlTField = new TextField("URL to profile picture");
	private final DatePicker birthdayDPicker = new DatePicker("birthday");
	private final TextField locationTField = new TextField("location");

	public UserInfoForm() {
		emailTField.setReadOnly(true);

		var logoutBtn = new Button("Log out");
		logoutBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		var saveBtn = new Button("Save");
		saveBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		saveBtn.addClickListener(buttonClickEvent -> save());
		var cancelBtn = new Button("Cancel");
		cancelBtn.addClickListener(buttonClickEvent -> this.close());
		var bottomBar = new HorizontalLayout();
		bottomBar.add(saveBtn, cancelBtn, logoutBtn);
		logoutBtn.addClickListener(buttonClickEvent -> logout());

		var form = new VerticalLayout();
		form.setAlignItems(FlexComponent.Alignment.CENTER);
		form.add(new Logo("80px"), firstNameTField, lastNameTField, emailTField, profilePictureUrlTField, birthdayDPicker, locationTField, bottomBar);
		add(form);
	}

	private void logout() {
		SecurityUtils.logout(); // invalidates session
//		MainView.userService.logout(); // "desauthenticate" user :D
		Notification.show("Logged out!");
	}

	public void fillValues() {
		var session = VaadinSession.getCurrent();
		emailTField.setValue(session.getAttribute("user").toString());

		Object temp = session.getAttribute("firstName");
		if (temp != null)
			firstNameTField.setValue(temp.toString());
		else
			firstNameTField.setValue("");

		temp = session.getAttribute("lastName");
		if (temp != null)
			lastNameTField.setValue(temp.toString());
		else
			lastNameTField.setValue("");

		temp = session.getAttribute("profilePictureURL");
		if (temp != null)
			profilePictureUrlTField.setValue(temp.toString());
		else
			profilePictureUrlTField.setValue("");

		if (session.getAttribute("birthday") != null)
			birthdayDPicker.setValue((LocalDate) session.getAttribute("birthday"));
//		else
//			birthday.setValue(null); // todo test if value can be null

		temp = session.getAttribute("location");
		if (temp != null)
			locationTField.setValue(temp.toString());
		else
			locationTField.setValue("");
	}

	private void save() {
		MainView.userService.update(firstNameTField.getValue(), lastNameTField.getValue(), emailTField.getValue(), profilePictureUrlTField.getValue(), birthdayDPicker.getValue(), locationTField.getValue());
		Notification.show("Saved!");
		this.close();
	}
}
