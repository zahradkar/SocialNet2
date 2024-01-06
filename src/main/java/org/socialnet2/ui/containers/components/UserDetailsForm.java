package org.socialnet2.ui.containers.components;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("user")
@AnonymousAllowed
public class UserDetailsForm extends VerticalLayout {
	public UserDetailsForm() {
		add(
				new TextField(),
				new TextField(),
				new TextField(),
				new TextField(),
				new TextField()
		);
	}
}
