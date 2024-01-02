package org.socialnet2.ui.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import org.socialnet2.ui.views.posts.PresentationPosts;

public class NewPost extends Dialog {
	// TODO everything
	public NewPost() {
		var content = new VerticalLayout();
		var head = new HorizontalLayout();
		var body = new HorizontalLayout();
		var foot = new HorizontalLayout();

		Image profilePicture = new Image(PresentationPosts.persons.get(5).getImage(), "profile picture"); // TODO update number to be random from list range
		profilePicture.setHeight("var(--lumo-size-l)");
		profilePicture.setWidth("var(--lumo-size-l)");
		profilePicture.getStyle().setBorderRadius("50%");

		var textarea = new TextArea("", "Here your thoughts...");

		var publishBtn = new Button("Publish...");
		publishBtn.addClickListener(buttonClickEvent -> {
			Notification notification = Notification.show("Not implemented yet!");
//			notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
//			Notification.show("Post published!");
			this.close();
		});


		head.add(profilePicture, new Span("John Smith"));
		body.add(textarea);
		foot.add(publishBtn);
		content.add(head, body, foot);
		add(content);
	}
}
