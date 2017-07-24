package bio.knowledge.web.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import bio.knowledge.web.view.ApplicationLayout;

@SpringUI
@Theme("kb2")
public class ApplicationUI extends UI {

	private Navigator applicationNavigator;
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	
    	ApplicationLayout layout = new ApplicationLayout();
		registerView(layout);

		
//        final VerticalLayout layout = new VerticalLayout();
//
//        final TextField name = new TextField();
//        name.setCaption("Type your name here:");
//
//        Button button = new Button("Click Me");
//        button.addClickListener(e ->
//            layout.addComponent(new Label("Thanks " + name.getValue()
//                    + ", it works!")));
//
//        layout.addComponents(name, button);
    	
    	setContent(layout);
    }
    
	private void registerView(ApplicationLayout applicationLayout) {
		applicationNavigator = applicationLayout.getNavigator();
	}
   
}