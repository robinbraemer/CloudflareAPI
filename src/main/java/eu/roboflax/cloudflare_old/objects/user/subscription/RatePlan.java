/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare_old.objects.user.subscription;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import eu.roboflax.cloudflare_old.objects.Identifiable;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Getter
@Setter
public class RatePlan implements Identifiable {
    
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("public_name")
    @Expose
    private String publicName;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("scope")
    @Expose
    private String scope;
    @SerializedName("externally_managed")
    @Expose
    private Boolean externallyManaged;
    
    @Override
    public String toString( ) {
        return new ToStringBuilder( this ).append( "id", id ).append( "publicName", publicName ).append( "currency", currency ).append( "scope", scope ).append( "externallyManaged", externallyManaged ).toString();
    }
    
}
