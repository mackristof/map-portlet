package map;

import com.vaadin.Application;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

public class MapApplication extends Application {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8695622759872012095L;

	public void init() {
		Window window = new Window();

		setMainWindow(window);

		Label label = new Label("Hello Map2!");

		window.addComponent(label);
	}

}