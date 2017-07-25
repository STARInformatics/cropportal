package bio.knowledge.web.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import bio.knowledge.engine.RequestEngine;
import bio.knowledge.engine.SearchEngine;
import bio.knowledge.models.Crop;
import bio.knowledge.models.Trait;

@SuppressWarnings("serial")
@SpringUI
@Theme("kb2")
public class ApplicationUI extends UI {

	@SuppressWarnings("unused")
	private Navigator applicationNavigator;
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {    	
        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        
        VerticalLayout searchResultLayout = new VerticalLayout();
        
        ComboBox comboBox = new ComboBox();
        for (Crop crop : Crop.values()) {
        	comboBox.addItem(crop);
        }
        comboBox.setValue(Crop.LENTIL);
        
        TextField textField = new TextField();
        textField.setInputPrompt("trait:");
        
        Button button = new Button("Search");
        
        button.addClickListener(event -> {
        	searchResultLayout.removeAllComponents();
        	Crop crop = (Crop) comboBox.getValue();
        	String keywords = textField.getValue();
        	Trait[] traits = SearchEngine.findTraits(crop, keywords);
        	System.out.println(traits);
        	
        	for (Trait trait : traits) {
        		try {
	        		NativeButton label = new NativeButton(trait.name.english);
	        		label.addClickListener(e -> {
	        			Map<String, String> details = SearchEngine.findTraitDetails(trait.id);
	        			Window window = new Window(trait.name.english);
	        			VerticalLayout vlayout = new VerticalLayout();
	        			window.setContent(vlayout);
	        			for (String key : details.keySet()) {
	        				vlayout.addComponent(new Label(key + " : " + details.get(key)));
	        			}
	        			ApplicationUI.getCurrent().addWindow(window);
	        		});
	        		searchResultLayout.addComponent(label);
        		} catch (Exception e) {
        			
        		}
        	}
        });
        
        horizontalLayout.addComponents(comboBox, textField, button);
        
        verticalLayout.addComponents(horizontalLayout, searchResultLayout);
        
        verticalLayout.setComponentAlignment(horizontalLayout, Alignment.MIDDLE_CENTER);
        verticalLayout.setComponentAlignment(searchResultLayout, Alignment.MIDDLE_CENTER);
        horizontalLayout.setMargin(true);
        horizontalLayout.setSpacing(true);
        searchResultLayout.setMargin(true);
        searchResultLayout.setSpacing(true);
        
    	setContent(verticalLayout);
    }
}