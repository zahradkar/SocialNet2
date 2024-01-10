package org.socialnet2.ui.containers.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.server.VaadinSession;
import org.socialnet2.backend.dtos.PostDTO;
import org.socialnet2.ui.containers.MainContainer;
import org.socialnet2.ui.views.MainView;

public class PostNew extends Dialog {
	// TODO everything
	public PostNew() {
		var content = new VerticalLayout();
		var head = new HorizontalLayout();
		var body = new HorizontalLayout();
		var foot = new HorizontalLayout();

		Image profilePicture = new Image(PresentationPostsView.postData.get(5).image(), "profile picture"); // TODO update number to be random from list range


		System.out.println(VaadinSession.getCurrent().getAttribute("user"));
		System.out.println(VaadinSession.getCurrent().getAttribute("firstName"));


		profilePicture.setHeight("var(--lumo-size-l)");
		profilePicture.setWidth("var(--lumo-size-l)");
		profilePicture.getStyle().setBorderRadius("50%");

		var textarea = new TextArea("", "Here your thoughts...");

		var publishBtn = new Button("Publish...");
		publishBtn.addClickListener(buttonClickEvent -> {
			var postData = new PostDTO("https://randomuser.me/api/portraits/women/76.jpg", "Lidmila Vilensky", "Apr 17", textarea.getValue(), "0", "0", "0"); // todo complete
			if (saveToDB(postData))
				displayOnScreen(postData);
			this.close();
		});

		head.add(profilePicture, new Span("John Smith"));
		body.add(textarea);
		foot.add(publishBtn);
		content.add(head, body, foot);
		add(content);
	}

	private boolean saveToDB(PostDTO data) {
//		var user = MainView.userService.getAuthenticatedUser();
		if (VaadinSession.getCurrent().getAttribute("user") != null) {
			MainView.postService.create(data, VaadinSession.getCurrent().getAttribute("user").toString());
			return true;
		}
		return false;
	}

	private void displayOnScreen(PostDTO data) {
		MainContainer.instance.addToMainColumn(new MediaObject(data));
		Notification.show("Published!");
	}
}
