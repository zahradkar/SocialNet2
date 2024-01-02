package org.socialnetvaadin2.ui.views;

import org.socialnetvaadin2.backend.security.AuthenticatedUser;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import jakarta.annotation.security.RolesAllowed;
//import org.vaadin.crudui.crud.impl.GridCrud;

@Route("books")
@RouteAlias("")
//@PermitAll
@RolesAllowed("USER")
@PageTitle("Vaadin library")
public class AuthenticatedView extends VerticalLayout {
	/*public AuthenticatedView(BookService bookService, AuthenticatedUser authenticatedUser) {
		var btnLogout = new Button("logout");
		btnLogout.addClickListener(buttonClickEvent -> authenticatedUser.logout());

		var crud = new GridCrud<>(Book.class, bookService);
		crud.getGrid().setColumns("title", "author", "published", "rating");
		crud.getCrudFormFactory().setVisibleProperties("title", "author", "published", "rating");
		crud.getCrudFormFactory().setUseBeanValidation(true);

		add(btnLogout, new H1("Books in library..."), crud);
	}*/
}
