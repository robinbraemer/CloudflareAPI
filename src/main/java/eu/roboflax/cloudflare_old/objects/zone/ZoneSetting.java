/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare_old.objects.zone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import eu.roboflax.cloudflare_old.objects.Identifiable;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Map;

@Getter
@Setter
public class ZoneSetting implements Identifiable {
    
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("modified_on")
    @Expose
    private String modifiedOn;
    @SerializedName("editable")
    @Expose
    private Boolean editable;
    @SerializedName("value")
    @Expose
    private String value;
    @Expose
    private Map<String, String> additional;
    
    @Override
    public String toString( ) {
        return new ToStringBuilder( this ).append( "id", id ).append( "modifiedOn", modifiedOn ).append( "editable", editable ).append( "value", value ).append( "additional", additional ).toString();
        
    }
    
}