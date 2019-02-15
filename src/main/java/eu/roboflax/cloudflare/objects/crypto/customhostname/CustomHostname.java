package eu.roboflax.cloudflare.objects.crypto.customhostname;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import eu.roboflax.cloudflare.objects.Identifiable;

import java.util.Map;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class CustomHostname implements Identifiable {

  @SerializedName("id")
  @Expose
  private String id;

  @SerializedName("hostname")
  @Expose
  private String hostname;

  @SerializedName("ssl")
  @Expose
  private Ssl ssl;

  @SerializedName("custom_origin_server")
  @Expose
  private String customOriginServer;

  @SerializedName("custom_metadata")
  @Expose
  private Map<String, Object> customMetadata;
}
