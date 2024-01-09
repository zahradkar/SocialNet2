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
import org.socialnet2.ui.views.MainView;

@Route("info")
@AnonymousAllowed
@Getter
public class UserInfo extends Dialog {
	// todo update class (provisional made)
	private final TextField firstName;
	private final TextField lastName;
	private final TextField email;
	private final TextField profilePictureURL;
	private final DatePicker birthday;
	private final TextField location;

	public UserInfo() {
		firstName = new TextField("first name");
		lastName = new TextField("last name");
		email = new TextField("e-mail");
//		email.setValue("kenaa@example.com");
		email.setReadOnly(true);
//		email.setEnabled(false);
		profilePictureURL = new TextField("URL to profile picture");
		birthday = new DatePicker("birthday");
		location = new TextField("location");

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
		logoutBtn.addClickListener(buttonClickEvent -> MainView.userService.logout());

		var form = new VerticalLayout();
		form.setAlignItems(FlexComponent.Alignment.CENTER);
		form.add(new Logo("80px"), firstName, lastName, email, profilePictureURL, birthday, location, bottomBar);
		add(form);
		this.open();
	}

	private void save() {
		MainView.userService.update(firstName.getValue(), lastName.getValue(), email.getValue(), profilePictureURL.getValue(), birthday.getValue(), location.getValue());
		Notification.show("Saved!");
	}
}
