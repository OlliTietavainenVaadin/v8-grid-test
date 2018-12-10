package org.vaadin;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    public static class Bean {
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name;

        public Bean(String name) {
            this.name = name;
        }
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        Grid<Bean> grid = new Grid<>();
        List<Bean> items = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Bean bean = new Bean(i+"");
            items.add(bean);
        }
        ListDataProvider<Bean> ldp = new ListDataProvider<>(items);
        grid.setDataProvider(ldp);
        grid.addColumn(bean -> bean.getName() + "a" ).setCaption("name a").setHidable(true);
        grid.addColumn(bean -> bean.getName() + "b").setCaption("name b").setHidable(true);


        layout.addComponent(grid);





        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
