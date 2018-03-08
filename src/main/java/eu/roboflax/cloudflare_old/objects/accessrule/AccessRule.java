/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare_old.objects.accessrule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import eu.roboflax.cloudflare_old.objects.Identifiable;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

@Getter
@Setter
public class AccessRule implements Identifiable {
    
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("allowed_modes")
    @Expose
    private List<String> allowedModes = null;
    @SerializedName("Mode")
    @Expose
    private String mode;
    @SerializedName("configuration")
    @Expose
    private Configuration configuration;
    @SerializedName("scope")
    @Expose
    private Scope scope;
    @SerializedName("created_on")
    @Expose
    private String createdOn;
    @SerializedName("modified_on")
    @Expose
    private String modifiedOn;
    
    @Override
    public String toString( ) {
        return new ToStringBuilder( this ).append( "id", id ).append( "notes", notes ).append( "allowedModes", allowedModes ).append( "Mode", mode ).append( "configuration", configuration ).append( "scope", scope ).append( "createdOn", createdOn ).append( "modifiedOn", modifiedOn ).toString();
    }
    
}
