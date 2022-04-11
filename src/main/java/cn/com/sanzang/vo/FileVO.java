package cn.com.sanzang.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>标题:</p>
 * <p>描述:</p>
 * <p>创建人:wchq</p>
 * <p>日期:2022-03-12</p>
 * <p>时间:14:13</p>
 */
@Data
public class FileVO implements Serializable {

    private String name;

    private String path;

}
