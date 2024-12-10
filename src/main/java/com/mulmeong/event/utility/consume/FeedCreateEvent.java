package com.mulmeong.event.utility.consume;



import com.mulmeong.batchserver.feed.domain.document.FeedRead;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class FeedCreateEvent {

    private String feedUuid;
    private String memberUuid;
    private String title;

}
