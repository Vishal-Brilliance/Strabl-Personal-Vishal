package com.strabl.sdk.common.dto.request;

import com.strabl.sdk.common.error.ResponseType;
import com.strabl.sdk.common.exception.ValidationException;
import lombok.Data;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

@Data
public class SendEmailRequest extends BaseRequest {

  @NotBlank
  private String to;

  private String from;
  private Map<String, String> dynamicData;

  public static class Builder {

    private SendEmailRequest request;

    public Builder(String to) {
      this.request = new SendEmailRequest();
      this.request.to = to;
      this.request.dynamicData = new HashMap<>();
    }

    public Builder(String to, String from) {
      this.request = new SendEmailRequest();
      this.request.to = to;
      this.request.from = from;
      this.request.dynamicData = new HashMap<>();
    }

    public Builder setDynamicValueForTemplate(String key, String value) {
      if (StringUtils.isEmpty(key)) {
        throw ValidationException.of(ResponseType.TEMPLATE_PLACEHOLDER_KEY_EMPTY);
      }

      this.request.dynamicData.put(key, value);

      return this;
    }

    public SendEmailRequest build() {
      return this.request;
    }
  }
}
