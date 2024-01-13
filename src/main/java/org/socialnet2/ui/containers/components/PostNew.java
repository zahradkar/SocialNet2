package org.socialnet2.ui.containers.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.server.VaadinSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socialnet2.backend.dtos.PostDTO;
import org.socialnet2.ui.containers.MainContainer;
import org.socialnet2.ui.views.MainView;

import java.time.LocalDate;

public class PostNew extends Dialog {
	// TODO everything
	private static final Logger logger = LoggerFactory.getLogger(PostNew.class);

	public PostNew() {
		var content = new VerticalLayout();
		content.setPadding(false); // this works :)
		var head = new HorizontalLayout();
		var body = new HorizontalLayout();
		var foot = new HorizontalLayout();
		foot.setJustifyContentMode(FlexComponent.JustifyContentMode.END); // todo fix: this does not work :(

		if (!VaadinSession.getCurrent().getAttribute(UserInfoForm.PROFILE_PICTURE).toString().isEmpty()) {
			// todo fix loading image (does not work)
			logger.info("Profile picture found - using:" + VaadinSession.getCurrent().getAttribute(UserInfoForm.PROFILE_PICTURE).toString());
			logger.info("profilePictureURL.length(): " + VaadinSession.getCurrent().getAttribute(UserInfoForm.PROFILE_PICTURE).toString().length());
			var profilePicture = new Image(VaadinSession.getCurrent().getAttribute(UserInfoForm.PROFILE_PICTURE).toString(), "profile picture");
			profilePicture.setHeight("var(--lumo-size-l)");
			profilePicture.setWidth("var(--lumo-size-l)");
			profilePicture.getStyle().setBorderRadius("50%");
			head.add(profilePicture);
		} else {
			logger.info("No profile picture found - used default user icon");
			head.add(new UserIcon());
		}

		var nameSpan = new Span("no name");
		var fName = VaadinSession.getCurrent().getAttribute(UserInfoForm.FIRST_NAME).toString();
		var lName = VaadinSession.getCurrent().getAttribute(UserInfoForm.LAST_NAME).toString();
		if (!fName.isEmpty() || !lName.isEmpty())
			nameSpan.setText(fName + " " + lName);
		logger.info("name in the post: " + nameSpan.getText());

		var textarea = new TextArea("", "Here your thoughts...");
		textarea.setWidth("400px");
		textarea.setHeight("200px"); // TODO calculate accordingly and apply responsive design

		var publishBtn = new Button("Publish...");
		publishBtn.addClickListener(buttonClickEvent -> {
			var postData = new PostDTO(
					VaadinSession.getCurrent().getAttribute(UserInfoForm.PROFILE_PICTURE).toString(), nameSpan.getText(), LocalDate.now(), textarea.getValue(), "0", "0", "0"); // todo complete
			if (saveToDB(postData))
				displayOnScreen(postData);
			this.close();
		});

//		head.addComponentAsFirst(Objects.requireNonNullElse(profilePicture, icon)); // wau
		head.add(nameSpan);
		body.add(textarea);
		foot.add(publishBtn);
		content.add(head, body, foot);
		add(content);
	}

	private boolean saveToDB(PostDTO data) {
//		var user = MainView.userService.getAuthenticatedUser();
		if (VaadinSession.getCurrent().getAttribute(UserInfoForm.USER) != null) {
			MainView.postService.create(data, VaadinSession.getCurrent().getAttribute(UserInfoForm.USER).toString());
			return true;
		}
		return false;
	}

	private void displayOnScreen(PostDTO data) {
		MainContainer.instance.addToMainColumn(new MediaObject(data));
		Notification.show("Published!");
	}
}
