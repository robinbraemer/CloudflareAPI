package eu.roboflax.cloudflare.objects.worker;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import eu.roboflax.cloudflare.objects.Identifiable;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @see <a href="https://api.cloudflare.com/#worker-script-enterprise--properties">https://api.cloudflare.com</a>
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class WorkerScript implements Identifiable {

  @SerializedName("id")
  @Expose
  private String id;

  @SerializedName("script")
  @Expose
  private String script;

  @SerializedName("etag")
  @Expose
  private String etag;

  @SerializedName("size")
  @Expose
  private int size;

  @SerializedName("created_on")
  @Expose
  private int createdOn;

  @SerializedName("modified_on")
  @Expose
  private int modifiedOn;
}
