package com.cowcloud.placeconfig.client;

import com.google.gwt.event.shared.EventHandler;

public interface PlaceEventHandler extends EventHandler {
  void onPlaceRequested(PlaceEvent event);
}
