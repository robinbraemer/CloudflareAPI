/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 *
 * Project cloudflare created by RoboFlax.
 * Class created on 22.12.2017 at 09:58.
 *
 */
package eu.roboflax.cloudflare.objects.user;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import eu.roboflax.cloudflare.objects.Identifiable;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Getter
@Setter
public class UserDetails implements Identifiable {
    
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("telephone")
    @Expose
    private String telephone;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("zipcode")
    @Expose
    private String zipCode;
    @SerializedName("created_on")
    @Expose
    private String createdOn;
    @SerializedName("modified_on")
    @Expose
    private String modifiedOn;
    @SerializedName("two_factor_authentication_enabled")
    @Expose
    private Boolean twoFactorAuthenticationEnabled;
    
    @Override
    public String toString( ) {
        return new ToStringBuilder( this ).append( "id", id ).append( "email", email ).append( "firstName", firstName ).append( "lastName", lastName ).append( "username", username ).append( "telephone", telephone ).append( "country", country ).append( "zipCode", zipCode ).append( "createdOn", createdOn ).append( "modifiedOn", modifiedOn ).append( "twoFactorAuthenticationEnabled", twoFactorAuthenticationEnabled ).toString();
    }
    
}