package eu.roboflax.cloudflare.objects.crypto.customhostname;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import eu.roboflax.cloudflare.objects.Identifiable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Ssl implements Identifiable {

  @SerializedName("id")
  @Expose
  private String id;

  @SerializedName("method")
  @Expose
  private Method method;

  @SerializedName("type")
  @Expose
  private Type type;

  @SerializedName("settings")
  @Expose
  private Settings settings;

  @SerializedName("status")
  @Expose
  private SslStatus status;

  @SerializedName("http_url")
  @Expose
  private String httpUrl;

  @SerializedName("http_body")
  @Expose
  private String httpBody;
}
