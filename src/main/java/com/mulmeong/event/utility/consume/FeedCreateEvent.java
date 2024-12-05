package com.mulmeong.event.utility.consume;



import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@NoArgsConstructor
public class FeedCreateEvent {

    private String feedUuid;
    private String memberUuid;
    private String title;

}
