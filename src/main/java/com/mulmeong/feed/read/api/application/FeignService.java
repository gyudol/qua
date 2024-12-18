package com.mulmeong.feed.read.api.application;

import com.mulmeong.feed.read.api.dto.client.CategoryDto;
import com.mulmeong.feed.read.api.dto.client.CategoryVo;
import com.mulmeong.feed.read.api.dto.client.HashtagDto;
import com.mulmeong.feed.read.common.client.AdminClient;
import com.mulmeong.feed.read.common.client.MemberClient;
import com.mulmeong.feed.read.common.client.UtilityClient;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
public class FeignService {

    private final MemberClient memberClient;
    private final UtilityClient utilityClient;
    private final AdminClient adminClient;

    List<CategoryDto> getInterestCategory(String memberUuid) {
        return memberClient.getInterestCategory((memberUuid)).result();
    }

    List<HashtagDto> getInterestHashtag(String memberUuid) {
        return memberClient.getInterestHashtag((memberUuid)).result();
    }

    List<String> getAllFollowings(String memberUuid) {
        return utilityClient.getAllFollowings(memberUuid).result();
    }

    CategoryVo getCategory(Long categoryId) {
        return adminClient.getCategory(categoryId).result();
    }

}
