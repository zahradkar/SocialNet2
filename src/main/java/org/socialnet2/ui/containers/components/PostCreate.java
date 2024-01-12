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
		Image profilePicture = new Image(PresentationPostsView.postData.get(5).imageURL(), "profile picture"); // TODO update number to be random from list range
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


/*
    <div id="temp-container" style="background: tomato">
      <div class="create-post">
        <picture>
          <source srcset="images/martin-50px.webp 1x, images/martin-100px.webp 2x, images/martin-200px.webp 3x"
                  type="image/webp">
          <img alt="martin's profile photo" class="profile-photo" src="images/martin-200px.jpg"/>
        </picture>
        <button class="create-post__input check-login">Post...</button>
      </div>
    </div>
*/