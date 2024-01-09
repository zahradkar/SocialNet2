package org.socialnet2.ui.containers;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.dom.Style;
import org.socialnet2.ui.containers.components.LoginDialog;
import org.socialnet2.ui.containers.components.Logo;
import org.socialnet2.ui.containers.components.SearchBar;
import org.socialnet2.ui.containers.components.UserInfo;
import org.socialnet2.ui.views.MainView;

public class Header extends Composite<HorizontalLayout> {
	private UserInfo userInfo;

	public Header() {
		var loginBtn = new Button();
		loginBtn.addThemeVariants(ButtonVariant.LUMO_ICON);
		loginBtn.setWidth("40px");
		loginBtn.setHeight("40px");
		loginBtn.getStyle().setBorderRadius("50%");
		loginBtn.setIcon(VaadinIcon.USER.create());
		loginBtn.addClickListener(buttonClickEvent -> loginAndRegister());

		getContent().setWidth("100%");
		getContent().setHeight("50px");
		getContent().setAlignItems(FlexComponent.Alignment.CENTER);
		getStyle().setBackground("white").setJustifyContent(Style.JustifyContent.SPACE_BETWEEN).set("position", "fixed").setZIndex(1).set("padding", "0 10px");
		getContent().add(new Logo("40px"), new SearchBar(), loginBtn); // TODO width: consider using variable instead of magic number
	}

	public String getHeight() {
		return this.getContent().getHeight();
	}

	private void loginAndRegister() {
		if (MainView.userService.getAuthenticatedUser().isEmpty())
			new LoginDialog().open();
		else {
			if (userInfo == null) {
				userInfo = new UserInfo();
				String userId = MainView.userService.getAuthenticatedUser().get().getEmail();
				var user = MainView.userService.readUser(userId);

				userInfo.getEmail().setValue(userId);
				if (user.firstName() != null)
					userInfo.getFirstName().setValue(user.firstName());
				if (user.lastName() != null)
					userInfo.getLastName().setValue(user.lastName());
				if (user.profilePictureURL() != null)
					userInfo.getProfilePictureURL().setValue(user.profilePictureURL());
				if (user.birthday() != null)
					userInfo.getBirthday().setValue(user.birthday());
				if (user.location() != null)
					userInfo.getLocation().setValue(user.location());
			}
			userInfo.open();
		}
		// TODO complete. it seems that user stays logged in even after restart of the app
	}
}
