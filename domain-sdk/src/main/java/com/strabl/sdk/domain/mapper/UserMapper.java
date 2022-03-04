package com.strabl.sdk.domain.mapper;

import com.strabl.sdk.common.dto.request.RegisterRequest;
import com.strabl.sdk.common.dto.response.UserResponseDTO;
import com.strabl.sdk.domain.entity.User;
import com.strabl.sdk.domain.entity.enums.columns.UserStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class UserMapper extends BaseMapper {

  public static User toEntity(RegisterRequest request) {

    User user = BaseMapper.toEntity(request, User.class);

    user.setEmail(request.getEmail());
    user.setPassword(request.getPassword());
    user.setFullName(request.getFullName());
    user.setPhone_number(request.getPhone_number());
    user.setUserUuid(UUID.randomUUID().toString());
    user.setProfilePicture(request.getProfilePicture());
    user.setIsseller(Boolean.FALSE);
    user.setIsEnabled(Boolean.FALSE);
    user.setStatus(UserStatus.UNVERIFIED);

    return user;
  }

  public static UserResponseDTO toUserResponseDTO(User entity) {
    return UserResponseDTO.builder()
            .id(entity.getId())
            .userUuid(entity.getUserUuid())
            .email(entity.getEmail())
            .fullName(entity.getFullName())
            .phone_number(entity.getPhone_number())
            .profilePicture(entity.getProfilePicture())
            .isEnabled(entity.getIsEnabled())
            .isseller(entity.getIsseller())
            .status(entity.getStatus().getEntityCode())
            .createdAt(entity.getCreatedAt())
            .build();
  }
}
