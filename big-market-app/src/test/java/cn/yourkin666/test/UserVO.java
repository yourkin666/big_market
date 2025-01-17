package cn.yourkin666.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

    private String userId;
    private String userName;
    private Integer userAge;

    @Slf4j
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    static class xx {
        private String userId;
    }

}
