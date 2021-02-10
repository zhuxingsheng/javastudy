package com.jack.mapstruct.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.StringUtils;

/**
 * @description: user
 * @author: zhuxingsheng@gmail.com
 * @create: 2020-07-25 15:56
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class User extends BaseObject {

    public static final Long SYSTEM_ID = -1L;
    public static final User SYSTEM = User.builder().id(SYSTEM_ID).build();

    private String firstName;
    private String lastName;
    private Integer age;
    private String language;

    public String getFullName() {
        return StringUtils.compareIgnoreCase(getLanguage(), "zh") == 0 ?
                (getLastName() + " " + getFirstName()) : (getFirstName() + " " + getLastName());
    }
}
