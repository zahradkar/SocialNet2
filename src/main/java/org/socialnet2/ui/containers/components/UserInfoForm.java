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
import com.vaadin.flow.server.auth.AnonymousAllowed;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socialnet2.ui.containers.Header;
import org.socialnet2.ui.views.MainView;

@Route("info")
@AnonymousAllowed
@Getter
public class UserInfoForm extends Dialog {
	private static final Logger logger = LoggerFactory.getLogger(UserInfoForm.class);
	// todo update class (provisional made)
	private final TextField firstName = new TextField("first name");
	private final TextField lastName = new TextField("last name");
	private final TextField email = new TextField("e-mail");
	private final TextField profilePictureURL = new TextField("URL to profile picture");
	private final DatePicker birthday = new DatePicker("birthday");
	private final TextField location = new TextField("location");

	public UserInfoForm() {
		email.setReadOnly(true);

		var logoutBtn = new Button("Log out");
		logoutBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		var saveBtn = new Button("Save");
		saveBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		saveBtn.addClickListener(buttonClickEvent -> save());
		var cancelBtn = new Button("Cancel");
		cancelBtn.addClickListener(buttonClickEvent -> this.close());
//		cancelBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		var bottomBar = new HorizontalLayout();
		bottomBar.add(saveBtn, cancelBtn, logoutBtn);
		logoutBtn.addClickListener(buttonClickEvent -> logout());

		var form = new VerticalLayout();
		form.setAlignItems(FlexComponent.Alignment.CENTER);
		form.add(new Logo("80px"), firstName, lastName, email, profilePictureURL, birthday, location, bottomBar);
		add(form);
		readValuesFromDB();
//		this.open();
	}

	public void readValuesFromDB() {
		if (MainView.userService.getAuthenticatedUser().isEmpty())
			return;
		String userId = MainView.userService.getAuthenticatedUser().get().getEmail();
		var user = MainView.userService.readUser(userId);
		logger.info(userId);

		email.setValue(userId);
		if (user.firstName() != null)
			firstName.setValue(user.firstName());
		if (user.lastName() != null)
			lastName.setValue(user.lastName());
		if (user.profilePictureURL() != null)
			profilePictureURL.setValue(user.profilePictureURL());
		if (user.birthday() != null)
			birthday.setValue(user.birthday());
		if (user.location() != null)
			location.setValue(user.location());
		Header.valuesLoaded = true;
	}

	private void logout() {
		Header.valuesLoaded = false;
		MainView.userService.logout();
	}

	private void save() {
		MainView.userService.update(firstName.getValue(), lastName.getValue(), email.getValue(), profilePictureURL.getValue(), birthday.getValue(), location.getValue());
		Notification.show("Saved!");
		this.close();
	}
}
