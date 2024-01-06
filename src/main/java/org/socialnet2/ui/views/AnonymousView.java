package org.socialnet2.ui.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("anonym")
@AnonymousAllowed
public class AnonymousView extends VerticalLayout {
	public AnonymousView() {
		add("You're logged out!");
	}
}
