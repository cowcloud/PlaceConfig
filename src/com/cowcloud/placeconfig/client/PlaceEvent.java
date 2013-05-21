package com.cowcloud.placeconfig.client;

import com.google.gwt.event.shared.GwtEvent;

public class PlaceEvent extends GwtEvent<PlaceEventHandler> {
  
  public static Type<PlaceEventHandler> TYPE = new Type<PlaceEventHandler>();
	
  private final String placeName;
 
  public PlaceEvent(String placeName) {
	this.placeName = placeName;
  }

  
public String getPlaceName() {
	return placeName;
}


@Override
  public Type<PlaceEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(PlaceEventHandler handler) {
    handler.onPlaceRequested(this);
  }
}
