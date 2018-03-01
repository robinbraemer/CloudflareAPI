/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare.objects.zone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import eu.roboflax.cloudflare.objects.Identifiable;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Getter
@Setter
public class PlanPending implements Identifiable {
    
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("Frequency")
    @Expose
    private String frequency;
    @SerializedName("legacy_id")
    @Expose
    private String legacyId;
    @SerializedName("is_subscribed")
    @Expose
    private Boolean isSubscribed;
    @SerializedName("can_subscribe")
    @Expose
    private Boolean canSubscribe;
    
    @Override
    public String toString( ) {
        return new ToStringBuilder( this ).append( "id", id ).append( "name", name ).append( "price", price ).append( "currency", currency ).append( "Frequency", frequency ).append( "legacyId", legacyId ).append( "isSubscribed", isSubscribed ).append( "canSubscribe", canSubscribe ).toString();
    }
    
}
