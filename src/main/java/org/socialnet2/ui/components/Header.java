package org.socialnet2.ui.components;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.server.StreamResource;
import org.socialnet2.backend.services.UserService;

public class Header extends Composite<HorizontalLayout> {
	// TODO in development

	public Header(UserService userService) {
		StreamResource imageResource = new StreamResource("logo.svg", () -> getClass().getResourceAsStream("/images/logo.svg"));
		var logoIco = new Image(imageResource, "logo image");
		logoIco.setWidth("40px"); // TODO use variable instead of magic number

		var loginBtn = new Button();
		loginBtn.addThemeVariants(ButtonVariant.LUMO_ICON);
		loginBtn.setWidth("40px");
		loginBtn.setHeight("40px");
		loginBtn.getStyle().setBorderRadius("50%");
		loginBtn.setIcon(VaadinIcon.USER.create());
		loginBtn.addClickListener(buttonClickEvent -> new LoginDialog(userService).open());

//		getContent().setPadding(false);
		getContent().setWidth("100%");
		getContent().setHeight("50px");
		getContent().setAlignItems(FlexComponent.Alignment.CENTER);
		getStyle().setBackground("white").setJustifyContent(Style.JustifyContent.SPACE_BETWEEN).set("position", "fixed").setZIndex(1).set("padding", "0 10px");
		getContent().add(logoIco, new SearchBar(), loginBtn);
	}

	public String getHeight() {
		return this.getContent().getHeight();
	}
}
