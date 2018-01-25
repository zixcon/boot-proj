package com.holdon.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by wd on 2018/1/24.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResult<T> {

    private Boolean success = true;

    private String code = "0";

    private String message;

    private T data;
}
