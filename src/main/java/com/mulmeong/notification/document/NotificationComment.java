package com.mulmeong.notification.document;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NotificationComment {

    FEED_COMMENT_CREATE("님이 회원님의 피드에 댓글을 남겼습니다."), //피드 작성자
    SHORTS_COMMENT_CREATE("님이 회원님의 쇼츠에 댓글을 남겼습니다."), //쇼츠 작성자
    FEED_LIKE_CREATE("님이 회원님의 피드를 좋아합니다."), //피드 작성자
    SHORTS_LIKE_CREATE("님이 회원님의 쇼츠를 좋아합니다."), //쇼츠 작성자
    COMMENT_LIKE_CREATE("님이 회원님의 댓글을 좋아합니다."), //댓글 작성자
    RECOMMENT_CREATE("님이 회원님의 댓글에 대댓글을 남겼습니다."), //댓글 작성자
    FOLLOW_CREATE("님이 회원님을 팔로우했습니다."), //팔로우 대상자
    FEED_CREATE("님이 새로운 피드를 생성했습니다."), //피드 생성자의 팔로워
    SHORTS_CREATE("님이 새로운 쇼츠를 생성했습니다."), //쇼츠 생성자의 팔로워
    GRADE_UPDATE("회원님의 등급이 변경되었습니다."),
    WIN_CONTEST("콘테스트 결과가 나왔습니다. 등수를 확인하세요."),
    REPORT_APPROVE("회원님이 접수한 신고가 승인되었습니다."),
    CHAT_CREATE("님이 메세지를 전송했습니다."); //채팅 받은 사람

    private final String comment;

}
