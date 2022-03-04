package com.strabl.sdk.common.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDTO implements Serializable {

    private static final long serialVersionUID = -8786111240585105921L;

    private Integer id;
    private String userUuid;
    private String email;
    private String password;
    private String fullName;
    private String phone_number;
    private Boolean isEnabled;
    private String status;
    private Instant createdAt;
    private Boolean isseller;
    private String profilePicture;

}
