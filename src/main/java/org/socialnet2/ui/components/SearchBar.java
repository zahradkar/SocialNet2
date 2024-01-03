package org.socialnet2.ui.components;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.server.StreamResource;

public class SearchBar extends HorizontalLayout {
	// TODO use variable instead of magic number
	public SearchBar() {
		StreamResource imageResource = new StreamResource("lupa.svg", () -> getClass().getResourceAsStream("/images/lupa.svg"));
		var lupaIco = new Image(imageResource, "lupa");
		lupaIco.setWidth("25px");

		var searchInput = new Input();
		searchInput.setPlaceholder("Search...");
		searchInput.getStyle().setBorder("none").setOutline("none");

		getStyle().setBackground("white").setPadding("3px 10px").setBorderRadius("30px").setBorder("1px solid black");
		setHeight("40px");
		add(lupaIco, searchInput);
	}
}
