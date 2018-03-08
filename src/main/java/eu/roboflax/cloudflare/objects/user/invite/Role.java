/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare.objects.user.invite;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import eu.roboflax.cloudflare.objects.Identifiable;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * @see <a href="https://api.cloudflare.com/#user-s-invites-properties">https://api.cloudflare.com</a>
 */
@Getter
@Setter
public class Role implements Identifiable {
    
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("permissions")
    @Expose
    private List<String> permissions = null;
    
    
    @Override
    public String toString( ) {
        return new ToStringBuilder( this ).append( "id", id ).append( "name", name ).append( "description", description ).append( "permissions", permissions ).toString();
    }
    
}
