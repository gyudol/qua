package com.mulmeong.reward.point.application;

import com.mulmeong.event.member.MemberCreateEvent;
import com.mulmeong.reward.common.exception.BaseException;
import com.mulmeong.reward.point.dto.out.MemberPointDto;
import com.mulmeong.reward.point.infrastructure.MemberPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.mulmeong.reward.common.response.BaseResponseStatus.NO_POINT;

@RequiredArgsConstructor
@Service
public class MemberPointServiceImpl implements MemberPointService {

    private final MemberPointRepository memberPointRepository;

    @Override
    public MemberPointDto getMemberPoint(String memberUuid) {

        return MemberPointDto.from(memberPointRepository.findByMemberUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_POINT)));
    }

    @Override
    public void createMemberPointDocument(MemberCreateEvent event) {
        memberPointRepository.save(event.toEntity());
    }
}
