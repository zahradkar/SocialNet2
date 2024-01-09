package org.socialnet2.ui.containers;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.dom.Style;
import org.socialnet2.ui.containers.components.*;
import org.socialnet2.ui.views.MainView;

public class Header extends Composite<HorizontalLayout> {
	private UserInfoForm userInfoForm2;
	public static boolean valuesLoaded;

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
		if (MainView.userService.getAuthenticatedUser().isEmpty()) {
			new LoginDialog().open();
		}
		else {
			/*
			existuju 3 scenare:
			- userInfoForm2 == null > vytvorit novu instanciu a nacitat hodnoty (toto mam zatial vyriesene)
			- userInfoForm je po logout, takze != null, ale nema nacitane (spravne) hodnoty > tu treba nacitat spravne hodnoty, ale bez vytovenia novej instancie
			- userInfoForm je closed, takze ma nacitane spravne hodnoty > tu netreba urobit nic
			* */
			if (userInfoForm2 == null)
				userInfoForm2 = new UserInfoForm();
			else if (!valuesLoaded)
				userInfoForm2.readValuesFromDB();
			userInfoForm2.open();
		}
		// TODO user stays logged in even after restart of the app
	}
}
