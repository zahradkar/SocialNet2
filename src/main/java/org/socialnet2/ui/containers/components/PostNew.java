package org.socialnet2.ui.containers.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.server.VaadinSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socialnet2.backend.dtos.PostRequestDTO;
import org.socialnet2.backend.dtos.ScraperResponseDTO;
import org.socialnet2.ui.views.MainView;

import java.io.IOException;
import java.time.LocalDate;

public class PostNew extends Dialog {
	// TODO tune
	private static final Logger logger = LoggerFactory.getLogger(PostNew.class);
	private final PostContent content;
	private final VerticalLayout preview;

	public PostNew() {
		var content = new VerticalLayout();
		content.setPadding(false); // this works :)
		var head = new HorizontalLayout();
		var body = new VerticalLayout();
		var foot = new HorizontalLayout();
		body.setPadding(false);
//		foot.setJustifyContentMode(FlexComponent.JustifyContentMode.END); // todo fix: this does not work :(

		if (!VaadinSession.getCurrent().getAttribute(UserInfoForm.PROFILE_PICTURE).toString().isEmpty()) {
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

		this.content = new PostContent("Here your thoughts...");
		this.content.setWidth("400px");
		this.content.setHeight("200px"); // TODO calculate accordingly and apply responsive design

		var publishBtn = getButton(nameSpan);
		preview = new VerticalLayout();
		preview.setMaxWidth("400px"); // todo tune

//		head.addComponentAsFirst(Objects.requireNonNullElse(profilePicture, icon)); // wau
		head.add(nameSpan);
		body.add(this.content, preview);
		foot.add(publishBtn);
		content.add(head, body, foot);
		add(content);
	}

	private Button getButton(Span nameSpan) {
		var publishBtn = new Button("Publish...");
		publishBtn.addClickListener(buttonClickEvent -> {
			if (content.getValue().isEmpty()) {
				Notification.show("Nothing to post!");
				return;
			}

			var postData = new PostRequestDTO(VaadinSession.getCurrent().getAttribute(UserInfoForm.PROFILE_PICTURE).toString(), nameSpan.getText(), LocalDate.now(), content.getValue(), "0", "0", "0"); // todo complete

			var user = VaadinSession.getCurrent().getAttribute(UserInfoForm.USER);
			if (user != null) {
				// todo test
				var postId = MainView.postService.create(postData, user.toString()); // storing to DB; returns postId
				displayOnScreen(postData, postId);
				this.close();
			} else
				Notification.show("You are not logged in!");
		});
		return publishBtn;
	}

	private void displayOnScreen(PostRequestDTO data, long postId) {
		MainColumn.instance.addComponentAtIndex(1, new MediaObject(data, postId));
		Notification.show("Published!");
	}

	private void createPreview(ScraperResponseDTO webPageURL) {
		var image = new Image(webPageURL.image(), "preview image");
		image.setMaxWidth("400px"); // todo tune
		var description = new Span(webPageURL.description());
//		description.setMaxWidth("400px");// todo tune
		preview.add(image, description);
	}

	private class PostContent extends TextArea {
		public PostContent(String placeholder) {
			setPlaceholder(placeholder);
			setValueChangeMode(ValueChangeMode.LAZY);
			addValueChangeListener(event -> {
				try {
					createPreview(MainView.scrapeService.getWebpageInfo(event.getValue()));
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			});
		}
	}
}
