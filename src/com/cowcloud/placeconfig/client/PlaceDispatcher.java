package com.cowcloud.placeconfig.client;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public abstract class PlaceDispatcher   implements   ValueChangeHandler<String> {
	
 
  private HasWidgets container;
   
   
  public void onModuleLoad( EventBus eventBus , HasWidgets container, String defaultPlace) {
	
	    this.container = container; 
	    
	    
	    History.addValueChangeHandler(this);
	    eventBus.addHandler(PlaceEvent.TYPE,
	        new PlaceEventHandler() {
	          public void onPlaceRequested(PlaceEvent event) {
	        	  History.newItem(event.getPlaceName()); 
	          }
	        }); 
	    
	    

	    if ("".equals(History.getToken())) {
	    	History.newItem(PlaceDispatcher.buildUrl(defaultPlace,null));
	    }
	    else {
	    	History.fireCurrentHistoryState();
	    }
  
  }

  public String[] getConfigAndPlace(ValueChangeEvent<String> event) {
	  String token = event.getValue();
	  if (token == null)  return null;
	  

	  final String [] urlSections = token.split("\\&");
	  
	  return urlSections[0].split("\\.");
    
      
 
	 
  } 
  
  public  Map<String, String>   getParams(ValueChangeEvent<String> event) {
	  String token = event.getValue();
	  if (token == null)  return null;
	  
	  final Map<String, String> params = new HashMap<String, String>();
	  final String [] urlSections = token.split("\\&");
	  if ( urlSections.length > 1) {
		  for ( int i =1; i < urlSections.length; i++) {
			  String keyAndValue[] = urlSections[i].split("\\=");
			  if ( keyAndValue.length == 2) {
				  params.put(keyAndValue[0],keyAndValue[1]);
			  }
		  }  
	  }
	
	  return params;
      
 
	 
  } 
  
  
  public static String buildUrl(String placeString,  Map<String,String> params) {

	  String paramsString = "";
	  if ( params != null ) {
		  Iterator iter = params.keySet().iterator();
		  while (iter.hasNext() ) {
			  String key = (String)iter.next();
			  paramsString += "&" + key + "=" + params.get(key);
		  }
	  }
	 
	  return placeString + paramsString;
  }

protected HasWidgets getContainer() {
	return container;
}



public static void gotoPlace(String placeString, Map<String, String> params, EventBus eventBus) {
	String urlString = PlaceDispatcher.buildUrl(placeString, params);
	eventBus.fireEvent(new PlaceEvent(urlString));	
	
}
  
 
}
