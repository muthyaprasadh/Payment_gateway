package com.payflow.user.service;

import com.payflow.user.dto.UpdateProfileRequest;
import com.payflow.user.dto.UserProfileResponse;

public interface UserService {

    UserProfileResponse getProfile();

    UserProfileResponse updateProfile(UpdateProfileRequest request);

}