package com.euprava.izradadokumenata.model.dto.user;

import com.euprava.izradadokumenata.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User from(UserSetupDto userSetupDto);

    LoggedUserDto toLoggedUserDto(User user);

    void updateUserFromLoggedUser(@MappingTarget User user, UserSetupDto updateEntity);

    SimpleUserDto toSimpleDto(User user);

    User from(SimpleUserDto simpleUserDto);
}
