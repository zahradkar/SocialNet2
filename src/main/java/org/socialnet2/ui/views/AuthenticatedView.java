package org.socialnet2.ui.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@Route("auth")
//@PermitAll
@RolesAllowed("USER")
@PageTitle("Auth view")
public class AuthenticatedView extends VerticalLayout {
	public AuthenticatedView() {
		add("Logged in with USER privileges...");
	}
}
