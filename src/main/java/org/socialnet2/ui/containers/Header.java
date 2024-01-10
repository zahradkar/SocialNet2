package org.socialnet2.ui.containers;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.server.VaadinSession;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socialnet2.ui.containers.components.LoginDialog;
import org.socialnet2.ui.containers.components.Logo;
import org.socialnet2.ui.containers.components.SearchBar;
import org.socialnet2.ui.containers.components.UserInfoForm;

@Getter
@Setter
public class Header extends Composite<HorizontalLayout> {
	private static final Logger logger = LoggerFactory.getLogger(Header.class);
	private UserInfoForm userInfoForm;

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
//		if (MainView.userService.getAuthenticatedUser().isEmpty())
		if (VaadinSession.getCurrent().getAttribute("user") == null)
			new LoginDialog().open();
		else {
			if (userInfoForm == null)
				userInfoForm = new UserInfoForm();
			if (userInfoForm.getEmailTField().getValue().isBlank())
				userInfoForm.fillValues();
			userInfoForm.open();
		}
	}
}
