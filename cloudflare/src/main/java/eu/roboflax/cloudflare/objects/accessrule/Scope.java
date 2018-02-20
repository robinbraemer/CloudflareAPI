
package eu.roboflax.cloudflare.objects.accessrule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import eu.roboflax.cloudflare.objects.Identifiable;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Getter
@Setter
public class Scope implements Identifiable {
    
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("type")
    @Expose
    private String type;
    
    @Override
    public String toString( ) {
        return new ToStringBuilder( this ).append( "id", id ).append( "email", email ).append( "type", type ).toString();
    }
}
