package eu.roboflax.cloudflare.objects.user.subscription;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import eu.roboflax.cloudflare.objects.Identifiable;
import eu.roboflax.cloudflare.objects.zone.Zone;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

@Getter
@Setter
public class Subscription implements Identifiable {
    
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("component_values")
    @Expose
    private List<ComponentValue> componentValues = null;
    @SerializedName("zone")
    @Expose
    private Zone zone;
    @SerializedName("Frequency")
    @Expose
    private String frequency;
    @SerializedName("rate_plan")
    @Expose
    private RatePlan ratePlan;
    @SerializedName("current_period_end")
    @Expose
    private String currentPeriodEnd;
    @SerializedName("current_period_start")
    @Expose
    private String currentPeriodStart;
    
    @Override
    public String toString( ) {
        return new ToStringBuilder( this ).append( "id", id ).append( "state", state ).append( "price", price ).append( "currency", currency ).append( "componentValues", componentValues ).append( "zone", zone ).append( "Frequency", frequency ).append( "ratePlan", ratePlan ).append( "currentPeriodEnd", currentPeriodEnd ).append( "currentPeriodStart", currentPeriodStart ).toString();
    }
    
}
