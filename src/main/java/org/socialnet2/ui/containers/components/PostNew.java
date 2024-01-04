package org.socialnet2.ui.containers.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import org.socialnet2.ui.containers.MainContainer;
import org.socialnet2.ui.views.posts.Person;
import org.socialnet2.ui.views.posts.PresentationPostsView;

public class PostNew extends Dialog {
	// TODO everything
	public PostNew() {
		var content = new VerticalLayout();
		var head = new HorizontalLayout();
		var body = new HorizontalLayout();
		var foot = new HorizontalLayout();

		Image profilePicture = new Image(PresentationPostsView.persons.get(5).image(), "profile picture"); // TODO update number to be random from list range
		profilePicture.setHeight("var(--lumo-size-l)");
		profilePicture.setWidth("var(--lumo-size-l)");
		profilePicture.getStyle().setBorderRadius("50%");

		var textarea = new TextArea("", "Here your thoughts...");

		var publishBtn = new Button("Publish...");
		publishBtn.addClickListener(buttonClickEvent -> {
			MainContainer.instance.addToMainColumn(new MediaObject(new Person("https://randomuser.me/api/portraits/women/76.jpg", "Lidmila Vilensky", "Apr 17",
					textarea.getValue(), "1K", "500", "20"))); // TODO complete
			Notification.show("Published!");
			this.close();
		});

		head.add(profilePicture, new Span("John Smith"));
		body.add(textarea);
		foot.add(publishBtn);
		content.add(head, body, foot);
		add(content);
	}
}
