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
import org.socialnet2.backend.entities.Post;
import org.socialnet2.ui.views.MainView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class PostNew extends Dialog {
	// TODO tune
	private static final Logger logger = LoggerFactory.getLogger(PostNew.class);
	private final PostContent content;
	private final VerticalLayout body;
	private final Preview preview = new Preview();
	@Deprecated(forRemoval = true)
	private VerticalLayout preview2; // todo after confirming that preview works well, delete

	public PostNew() {
		var content = new VerticalLayout();
		content.setPadding(false); // this works :)
		var head = new HorizontalLayout();
		body = new VerticalLayout();
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

		var publishBtn = getButton();

//		head.addComponentAsFirst(Objects.requireNonNullElse(profilePicture, icon)); // wau
		head.add(nameSpan);
//		body.add(this.content, preview);
		body.add(this.content);
		foot.add(publishBtn);
		content.add(head, body, foot);
		add(content);
	}

	private Button getButton() {
		var publishBtn = new Button("Publish...");
		publishBtn.addClickListener(buttonClickEvent -> {
			if (content.getValue().isEmpty()) {
				Notification.show("Nothing to post!");
				return;
			}

			var postData = new PostRequestDTO(content.getValue(), preview.image.getSrc(), preview.titleSpn.getText(), preview.descriptionSpn.getText());

			var user = VaadinSession.getCurrent().getAttribute(UserInfoForm.USER);
			if (user != null) {
				// todo test
				displayOnScreen(MainView.postService.create(postData, user.toString())); // storing to DB; returns Post entity
				this.close();
			} else
				Notification.show("You are not logged in!");
		});
		return publishBtn;
	}

	private void displayOnScreen(Post post) {
		MainColumn.instance.addComponentAtIndex(1, new MediaObject(post));
		Notification.show("Published!");
	}

	@Deprecated(forRemoval = true) // todo delete after deleting preview2 filed
	private void createPreview(ScraperResponseDTO webPageURL) {
		if (body.getComponentCount() > 1)
			body.remove(body.getComponentAt(1));
		var image = new Image(webPageURL.image(), "preview image");
		var title = new Span(webPageURL.title());
		var description = new Span(webPageURL.description());
		preview2 = new VerticalLayout();
		preview2.setPadding(false);
//		preview2.setMaxWidth("400px");
		image.setMaxWidth("400px"); // todo tune
		image.setMaxHeight("300px");
		title.getStyle().setFontWeight("bold");
		title.setMaxWidth("400px");
		description.setMaxWidth("400px");// todo tune
		content.setHeight("min-content");
//		preview.add(image, description);
		preview2.add(image, title, description);
		body.add(preview2);
	}

	boolean isValidURL(String url) {
		try {
//			new URL(url).toURI();
			new URI(url).toURL();
			logger.info("input is valid URL");
			return true;
		} catch (MalformedURLException e) {
			logger.info("Malformed URL");
			return false;
		} catch (URISyntaxException e) {
			logger.info("URI syntax exception");
			return false;
		} catch (IllegalArgumentException e) {
			logger.info("URL not complete so far");
			return false;
		}
	}

	private class PostContent extends TextArea {
		public PostContent(String placeholder) {
			setPlaceholder(placeholder);
			setValueChangeMode(ValueChangeMode.LAZY);
			addValueChangeListener(event -> {
				try {
					if (isValidURL(event.getValue()))
						preview.createPreview(MainView.scrapeService.getWebpageInfo(event.getValue()));
//					createPreview(MainView.scrapeService.getWebpageInfo(event.getValue()));
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			});
		}
	}

	private class Preview extends VerticalLayout {
		private final Image image = new Image();
		private final Span titleSpn = new Span();
		private final Span descriptionSpn = new Span();

		public Preview() {
			setPadding(false);
//			setMaxWidth("400px");
			image.setMaxWidth("400px"); // todo tune
			image.setMaxHeight("300px");
			image.setAlt("URLs presentation picture");
			titleSpn.getStyle().setFontWeight("bold");
			titleSpn.setMaxWidth("400px");
			descriptionSpn.setMaxWidth("400px");// todo tune
		}

		public void createPreview(ScraperResponseDTO webPageURL) {
			// todo add to preview close button
			if (body.getComponentCount() > 1)
				body.remove(body.getComponentAt(1));
			image.setSrc(webPageURL.image());
			titleSpn.setText(webPageURL.title());
			descriptionSpn.setText(webPageURL.description());
			if (!image.getSrc().isEmpty())
				add(image);
			if (!titleSpn.getText().isEmpty())
				add(titleSpn);
			if (!descriptionSpn.getText().isEmpty())
				add(descriptionSpn);
			body.add(this);
			content.setHeight("min-content");
		}
	}
}
