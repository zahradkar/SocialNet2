package org.socialnet2.ui.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import jakarta.annotation.security.RolesAllowed;
//import org.vaadin.crudui.crud.impl.GridCrud;

@Route("books")
//@RouteAlias("")
//@PermitAll
@RolesAllowed("USER")
@PageTitle("Auth view")
public class AuthenticatedView extends VerticalLayout {
	public AuthenticatedView() {
		add("Logged in with USER privileges...");
	}
}
