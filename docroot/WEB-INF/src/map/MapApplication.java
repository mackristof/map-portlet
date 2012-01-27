package map;



import org.vaadin.vol.GoogleStreetMapLayer;
import org.vaadin.vol.Marker;
import org.vaadin.vol.MarkerLayer;
import org.vaadin.vol.OpenLayersMap;
import org.vaadin.vol.OpenStreetMapLayer;
import org.vaadin.vol.Popup;
import org.vaadin.vol.Popup.CloseEvent;
import org.vaadin.vol.Popup.CloseListener;
import org.vaadin.vol.Popup.PopupStyle;

import com.vaadin.Application;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class MapApplication extends Application {

	private final VerticalLayout layout = new VerticalLayout();
//    private final HorizontalLayout controls = new HorizontalLayout();
    private final Window mainWindow = new Window("Map Application",
            layout);

    @Override
    public void init() {
        setMainWindow(mainWindow);
        final OpenLayersMap map = new OpenLayersMap();
        map.setImmediate(true); // update extent and zoom to server as they
                                // change

        /*
         * Open street maps layer as a base layer. Note importance of the order,
         * OSM layer now sets the projection to Spherical Mercator. If added eg.
         * after markers or vectors, they might render with bad values.
         */
        OpenStreetMapLayer osm = new OpenStreetMapLayer();

        GoogleStreetMapLayer googleStreets = new GoogleStreetMapLayer();
     // add layers
        map.addLayer(osm);
        map.addLayer(googleStreets);
     // Define a Marker Layer
        MarkerLayer markerLayer = new MarkerLayer();

        // Defining a new Marker

        final Marker marker = new Marker(22.30083, 60.452541);
        // URL of marker Icon
        marker.setIcon("http://dev.vaadin.com/chrome/site/vaadin-trac.png", 60,
                20);

        // Add some server side integration when clicking a marker
        marker.addClickListener(new com.vaadin.event.MouseEvents.ClickListener() {
            public void click(com.vaadin.event.MouseEvents.ClickEvent event) {
                final Popup popup = new Popup(marker.getLon(), marker.getLat(),
                        "Vaadin HQ is <em>here</em>!");
                popup.setAnchor(marker);
                popup.setPopupStyle(PopupStyle.FRAMED_CLOUD);
                popup.addListener(new CloseListener() {
                    public void onClose(CloseEvent event) {
                        map.removeComponent(popup);
                    }
                });
                map.addPopup(popup);
            }
        });
     // Add the marker to the marker Layer
        markerLayer.addMarker(marker);
        map.setCenter(22.30, 60.452);
        map.setZoom(15);
        map.addLayer(markerLayer);
        map.setSizeFull();
        layout.setSizeFull();
        layout.addComponent(map);
        layout.addComponent(new Label("hello"));
        }

}