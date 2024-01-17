package org.socialnet2.ui.containers.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.server.VaadinSession;

public class PostCreate extends HorizontalLayout {
	public PostCreate() {
		// TODO update units (do not use magic numbers)
		addClassName("add-post-component");
		getStyle().setAlignItems(Style.AlignItems.CENTER);
		setHeight("60px");
		setWidth("100%");

		getStyle().setBackground("white");
		getStyle().setBorderRadius("30px");
		getStyle().setPadding("0 10px");
		Image profilePicture = new Image("https://randomuser.me/api/portraits/women/24.jpg", "alksdjf"); // TODO update
		profilePicture.setHeight("var(--lumo-size-l)");
		profilePicture.setWidth("var(--lumo-size-l)");
		profilePicture.getStyle().setBorderRadius("50%");
		add(profilePicture);

		var addPostBtn = new Button("Post...");
		addPostBtn.addClassName("add-post__button");
		addPostBtn.addClickListener(buttonClickEvent -> {
//			if (MainView.userService.getAuthenticatedUser().isEmpty())
			if (VaadinSession.getCurrent().getAttribute("user") == null)
				new LoginDialog().open();
			else
				new PostNew().open();
		});
		add(addPostBtn);
	}
}