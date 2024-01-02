package org.socialnet2.ui.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
public class AnonymousView extends VerticalLayout {
	public AnonymousView() {
		add(new H1("You're logged out!"));
	}
}
