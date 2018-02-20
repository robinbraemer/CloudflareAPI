package eu.roboflax.cloudflare.objects.zone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import eu.roboflax.cloudflare.objects.Identifiable;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

@Getter
@Setter
public class Zone implements Identifiable {
    
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("development_mode")
    @Expose
    private Integer developmentMode;
    @SerializedName("original_name_servers")
    @Expose
    private List<String> originalNameServers = null;
    @SerializedName("original_registrar")
    @Expose
    private String originalRegistrar;
    @SerializedName("original_dnshost")
    @Expose
    private String originalDnshost;
    @SerializedName("created_on")
    @Expose
    private String createdOn;
    @SerializedName("modified_on")
    @Expose
    private String modifiedOn;
    @SerializedName("name_servers")
    @Expose
    private List<String> nameServers = null;
    @SerializedName("owner")
    @Expose
    private Owner owner;
    @SerializedName("permissions")
    @Expose
    private List<String> permissions = null;
    @SerializedName("plan")
    @Expose
    private Plan plan;
    @SerializedName("plan_pending")
    @Expose
    private PlanPending planPending;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("paused")
    @Expose
    private Boolean paused;
    @SerializedName("type")
    @Expose
    private String type;
    
    @Override
    public String toString( ) {
        return new ToStringBuilder( this ).append( "id", id ).append( "name", name ).append( "developmentMode", developmentMode ).append( "originalNameServers", originalNameServers ).append( "originalRegistrar", originalRegistrar ).append( "originalDnshost", originalDnshost ).append( "createdOn", createdOn ).append( "modifiedOn", modifiedOn ).append( "nameServers", nameServers ).append( "owner", owner ).append( "permissions", permissions ).append( "plan", plan ).append( "planPending", planPending ).append( "Status", status ).append( "paused", paused ).append( "type", type ).toString();
    }
    
}
