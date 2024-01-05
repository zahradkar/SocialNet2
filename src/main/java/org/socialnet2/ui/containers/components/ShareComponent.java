package org.socialnet2.ui.containers.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("share")
@AnonymousAllowed
public class ShareComponent extends HorizontalLayout {
    public ShareComponent() {
        addClassName("shares");
        setAlignItems(Alignment.CENTER);
        var shareIco = VaadinIcon.CONNECT.create();
        shareIco.addClassName("vote-icon"); // TODO rename
        var shareBtn = new Button(shareIco);
        shareBtn.addClassName("media-object-button");
        var shareCount = new Span("31");
        add(shareBtn, shareCount);
    }
}
