/*
 * Copyright (c) RoboFlax. All rights reserved.
 * Use is subject to license terms.
 */
package eu.roboflax.cloudflare.objects.user.billing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import eu.roboflax.cloudflare.objects.Identifiable;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @see <a href="https://api.cloudflare.com/#user-billing-profile-billing-profile">https://api.cloudflare.com</a>
 */
@Getter
@Setter
public class BillingProfile implements Identifiable {
    
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("address2")
    @Expose
    private String address2;
    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("zipCode")
    @Expose
    private String zipcode;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("telephone")
    @Expose
    private String telephone;
    @SerializedName("card_number")
    @Expose
    private String cardNumber;
    @SerializedName("card_expiry_year")
    @Expose
    private Integer cardExpiryYear;
    @SerializedName("card_expiry_month")
    @Expose
    private Integer cardExpiryMonth;
    @SerializedName("vat")
    @Expose
    private String vat;
    @SerializedName("edited_on")
    @Expose
    private String editedOn;
    @SerializedName("created_on")
    @Expose
    private String createdOn;
    
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("firstName", firstName).append("lastName", lastName).append("address", address).append("address2", address2).append("company", company).append("city", city).append("state", state).append("zipCode", zipcode).append("country", country).append("telephone", telephone).append("cardNumber", cardNumber).append("cardExpiryYear", cardExpiryYear).append("cardExpiryMonth", cardExpiryMonth).append("vat", vat).append("editedOn", editedOn).append("createdOn", createdOn).toString();
    }
}
