/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare_old.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import eu.roboflax.cloudflare_old.objects.zone.ZoneSetting;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class ZoneSettingDeserializer implements JsonDeserializer<ZoneSetting> {
    
    @Override
    public ZoneSetting deserialize( JsonElement json, Type typeOfT, JsonDeserializationContext context ) throws JsonParseException {
        ZoneSetting zoneSetting = new ZoneSetting();
        
        Map<String, String> map = new HashMap<>();
        String value;
        for ( Map.Entry<String, JsonElement> entry : json.getAsJsonObject().entrySet() ) {
            value = entry.getValue().toString();
            // remove " symbol at start and end
            if ( value.charAt( 0 ) == '"' )
                value = value.substring( 1 );
            if ( value.charAt( value.length() - 1 ) == '"' )
                value = value.substring( 0, value.length() - 1 );
            map.put( entry.getKey(), value );
        }
        
        zoneSetting.setId( map.get( "id" ) );
        zoneSetting.setModifiedOn( map.getOrDefault( "modified_on", null ) );
        zoneSetting.setEditable( Boolean.valueOf( map.get( "editable" ) ) );
        zoneSetting.setValue( map.get( "value" ) );
        
        map.remove( "id" );
        map.remove( "modified_on" );
        map.remove( "editable" );
        map.remove( "value" );
        zoneSetting.setAdditional( map );
        
        return zoneSetting;
    }
}
