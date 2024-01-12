package org.socialnet2.ui.containers.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
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
import org.socialnet2.ui.views.MainView;

import java.time.LocalDate;

@Route("info")
@AnonymousAllowed
@Getter
public class UserInfoForm extends Dialog {
	// todo update class (provisional made)
	public static final String USER = "user";
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String PROFILE_PICTURE = "profilePictureURL";
	public static final String BIRTHDAY = "birthday";
	public static final String LOCATION = "location";
	private static final Logger logger = LoggerFactory.getLogger(UserInfoForm.class);

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
		emailTField.setValue(session.getAttribute(USER).toString());
		firstNameTField.setValue(session.getAttribute(FIRST_NAME).toString());
		lastNameTField.setValue(session.getAttribute(LAST_NAME).toString());
		profilePictureUrlTField.setValue(session.getAttribute(PROFILE_PICTURE).toString());
		if (session.getAttribute(BIRTHDAY) != null) // TODO test if is necessary this condition
			birthdayDPicker.setValue((LocalDate) session.getAttribute(BIRTHDAY));
		locationTField.setValue(session.getAttribute(LOCATION).toString());
	}

	private void save() {
		// todo consider update userService.update() to use PostDTO
		MainView.userService.update(firstNameTField.getValue(), lastNameTField.getValue(), emailTField.getValue(), profilePictureUrlTField.getValue(), birthdayDPicker.getValue(), locationTField.getValue());
		updateCookies();
		Notification not = Notification.show("Saved!");
		not.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
		this.close();
	}

	private void updateCookies() {
		VaadinSession session = VaadinSession.getCurrent();
		session.setAttribute(FIRST_NAME, firstNameTField.getValue());
		session.setAttribute(LAST_NAME, lastNameTField.getValue());
		session.setAttribute(PROFILE_PICTURE, profilePictureUrlTField.getValue());
		session.setAttribute(BIRTHDAY, birthdayDPicker.getValue()); // todo test this row
		session.setAttribute(LOCATION, locationTField.getValue());
	}
}
