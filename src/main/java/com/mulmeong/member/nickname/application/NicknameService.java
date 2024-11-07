package com.mulmeong.member.nickname.application;

import com.mulmeong.member.nickname.dto.in.UpdateNicknameRequestDto;

public interface NicknameService {

    public String getNickname(String memberUuid);

    public void updateNickname(UpdateNicknameRequestDto updateNicknameRequestDto);

    public boolean checkNickname(String nickname);
}
