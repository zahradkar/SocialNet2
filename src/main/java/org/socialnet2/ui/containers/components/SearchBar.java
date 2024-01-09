package org.socialnet2.ui.containers.components;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class SearchBar extends HorizontalLayout {
	// TODO use variable instead of magic number
	public SearchBar() {
		var lupaIco = new Image("images/lupa.svg", "lupa");
		lupaIco.setWidth("25px");

		var searchInput = new Input();
		searchInput.setPlaceholder("Search...");
		searchInput.getStyle().setBorder("none").setOutline("none");

		getStyle().setBackground("white").setPadding("3px 10px").setBorderRadius("30px").setBorder("1px solid black");
		setHeight("40px");
		add(lupaIco, searchInput);
	}
}
