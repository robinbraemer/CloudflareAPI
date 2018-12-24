package eu.roboflax.cloudflare.objects.spectrum;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DNS {

  @SerializedName("type")
  @Expose
  private DNSType type;

  @SerializedName("name")
  @Expose
  private String name;
}
