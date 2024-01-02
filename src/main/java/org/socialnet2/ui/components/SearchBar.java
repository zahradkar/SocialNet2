package org.socialnet2.ui.components;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class SearchBar extends HorizontalLayout {
	// TODO use variable instead of magic number
	public SearchBar() {
		getStyle().setBackground("white").setPadding("3px 10px").setBorderRadius("30px").setBorder("1px solid black");

		setHeight("40px");
		var lupa = new Image("icons/lupa.svg", "lupa");
		lupa.setWidth("25px");
		var searchInput = new Input();
		searchInput.setPlaceholder("Search...");
		searchInput.getStyle().setBorder("none").setOutline("none");
		add(lupa, searchInput);
	}
}

/*
<div class="search">
  <svg class="search__icon" viewBox="0 0 512 512" xml:space="preserve" xmlns="http://www.w3.org/2000/svg">
        <path d="M333 291.92c52.2-71.9 45.94-173.34-18.84-238.13-71.73-71.72-188.46-71.72-260.2 0-71.74 71.75-71.74 188.46 0 260.2 64.78 64.78 166.23 71.04 238.11 18.85l14.22 14.2 40.92-40.91L333 291.92zm-54.51-13.59c-52.15 52.14-136.7 52.15-188.85 0-52.16-52.15-52.16-136.71 0-188.86 52.15-52.14 136.7-52.14 188.85 0 52.15 52.15 52.15 136.71 0 188.86z"/>
    <path d="M109.3 119.22c-27.07 34.78-29.32 82.64-6.75 119.61a7.43 7.43 0 0 0 12.67-7.74v.01c-19.39-31.7-17.45-72.96 5.78-102.77a7.4 7.4 0 0 0-1.3-10.4 7.4 7.4 0 0 0-10.4 1.29zM501.5 438.6 363.34 315.17l-47.98 47.98 123.4 138.17c12.55 16.23 35.15 13.84 55.45-6.46 20.3-20.3 23.53-43.73 7.29-56.28z"/>
      </svg>
  <input placeholder="type here..." type="text">
</div>
*/