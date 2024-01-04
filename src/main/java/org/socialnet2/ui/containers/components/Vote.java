package org.socialnet2.ui.containers.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("votes")
@PageTitle("SocialNet | vote component presentation")
@AnonymousAllowed
public class Vote extends HorizontalLayout {
	public Vote() {
		var upvoteIco = VaadinIcon.THUMBS_UP.create();
		upvoteIco.addClassNames("vote-icon", "voteup");
		var upvoteBtn = new Button(upvoteIco);
		upvoteBtn.addThemeVariants(ButtonVariant.LUMO_ICON);
		upvoteBtn.addClassName("vote-button");
//		upvoteBtn.setWidth("40px");
//		upvoteBtn.setHeight("40px");
//		upvoteBtn.getStyle().setBorderRadius("50%");
//		upvoteBtn.getStyle().setPadding("0");
//		upvoteBtn.getStyle().setMargin("0");

		var downvoteIco = VaadinIcon.THUMBS_DOWN.create();
		downvoteIco.addClassNames("vote-icon", "votedown");
		var downvoteBtn = new Button(downvoteIco);
		downvoteBtn.addClassName("vote-button");
//		downvoteBtn.setIcon(VaadinIcon.THUMBS_DOWN.create());

		var votes = new Span("10k");
		setAlignItems(Alignment.CENTER);
//		setJustifyContentMode(JustifyContentMode.CENTER);

		addClassName("votes");
		getStyle().set("border-radius", "20px");
		setWidth("min-content");
		setHeight("40px");
//		setHeight("min-content"); // toto tu nefunguje
		add(upvoteBtn, votes, downvoteBtn);
	}
}


/*
<div class="likes-container color--primary">
              <button class="btn-upvote btn--round color--primary check-login" onclick="vote(33, 'up')">
                <svg class="icon thumb-up" viewBox="0 0 1792 1792" xmlns="http://www.w3.org/2000/svg">
                  <path d="M320 1344q0-26-19-45t-45-19q-27 0-45.5 19t-18.5 45q0 27 18.5 45.5T256 1408q26 0 45-18.5t19-45.5zm160-512v640q0 26-19 45t-45 19H128q-26 0-45-19t-19-45V832q0-26 19-45t45-19h288q26 0 45 19t19 45zm1184 0q0 86-55 149 15 44 15 76 3 76-43 137 17 56 0 117-15 57-54 94 9 112-49 181-64 76-197 78h-129q-66 0-144-15.5t-121.5-29T766 1580q-123-43-158-44-26-1-45-19.5t-19-44.5V831q0-25 18-43.5t43-20.5q24-2 76-59t101-121q68-87 101-120 18-18 31-48t17.5-48.5T945 310q7-39 12.5-61t19.5-52 34-50q19-19 45-19 46 0 82.5 10.5t60 26 40 40.5 24 45 12 50 5 45 .5 39q0 38-9.5 76t-19 60-27.5 56q-3 6-10 18t-11 22-8 24h277q78 0 135 57t57 135z"></path>
                </svg>
              </button>
              <span class="likes-count" id="a33">-2</span>
              <button class="btn-downvote btn--round color--primary check-login" onclick="vote(33, 'down')">
                <svg class="icon thumb-down" viewBox="0 0 1792 1792" xmlns="http://www.w3.org/2000/svg">
                  <path d="M320 1344q0-26-19-45t-45-19q-27 0-45.5 19t-18.5 45q0 27 18.5 45.5T256 1408q26 0 45-18.5t19-45.5zm160-512v640q0 26-19 45t-45 19H128q-26 0-45-19t-19-45V832q0-26 19-45t45-19h288q26 0 45 19t19 45zm1184 0q0 86-55 149 15 44 15 76 3 76-43 137 17 56 0 117-15 57-54 94 9 112-49 181-64 76-197 78h-129q-66 0-144-15.5t-121.5-29T766 1580q-123-43-158-44-26-1-45-19.5t-19-44.5V831q0-25 18-43.5t43-20.5q24-2 76-59t101-121q68-87 101-120 18-18 31-48t17.5-48.5T945 310q7-39 12.5-61t19.5-52 34-50q19-19 45-19 46 0 82.5 10.5t60 26 40 40.5 24 45 12 50 5 45 .5 39q0 38-9.5 76t-19 60-27.5 56q-3 6-10 18t-11 22-8 24h277q78 0 135 57t57 135z"></path>
                </svg>
              </button>
            </div>

*/