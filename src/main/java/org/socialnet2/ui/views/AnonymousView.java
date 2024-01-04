package org.socialnet2.ui.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.socialnet2.ui.containers.components.PostCreate;

@Route("anonym")
@AnonymousAllowed
public class AnonymousView extends VerticalLayout {
	public AnonymousView() {
		var btn = new Button("open addPosComponent");
		btn.addClickListener(buttonClickEvent -> new PostCreate());
		add(new PostCreate());
		add(new H1("You're logged out!"));
	}
}
