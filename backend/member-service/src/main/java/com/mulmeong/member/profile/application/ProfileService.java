package com.mulmeong.member.profile.application;

import com.mulmeong.member.profile.dto.in.NicknameUpdateRequestDto;
import com.mulmeong.member.profile.dto.in.ProfileImgUpdateRequestDto;

public interface ProfileService {

    public String getNickname(String memberUuid);

    public void updateNickname(NicknameUpdateRequestDto nicknameUpdateRequestDto);

    public boolean checkNickname(String nickname);

    public void updateProfileImage(ProfileImgUpdateRequestDto profileImgUpdateRequestDto);
}
