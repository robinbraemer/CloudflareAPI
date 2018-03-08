/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare_old.objects.user.invite;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import eu.roboflax.cloudflare_old.objects.Identifiable;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

@Getter
@Setter
public class Invite implements Identifiable {
    
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("invited_member_id")
    @Expose
    private String invitedMemberId;
    @SerializedName("invited_member_email")
    @Expose
    private String invitedMemberEmail;
    @SerializedName("organization_id")
    @Expose
    private String organizationId;
    @SerializedName("organization_name")
    @Expose
    private String organizationName;
    @SerializedName("roles")
    @Expose
    private List<Role> roles = null;
    @SerializedName("invited_by")
    @Expose
    private String invitedBy;
    @SerializedName("invited_on")
    @Expose
    private String invitedOn;
    @SerializedName("expires_on")
    @Expose
    private String expiresOn;
    @SerializedName("Status")
    @Expose
    private String status;
    
    @Override
    public String toString( ) {
        return new ToStringBuilder( this ).append( "id", id ).append( "invitedMemberId", invitedMemberId ).append( "invitedMemberEmail", invitedMemberEmail ).append( "organizationId", organizationId ).append( "organizationName", organizationName ).append( "roles", roles ).append( "invitedBy", invitedBy ).append( "invitedOn", invitedOn ).append( "expiresOn", expiresOn ).append( "Status", status ).toString();
    }
    
}
