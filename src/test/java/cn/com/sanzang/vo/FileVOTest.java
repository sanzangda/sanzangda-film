package cn.com.sanzang.vo;

import junit.framework.TestCase;
import net.minidev.json.JSONValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileVOTest extends TestCase {

    public void testVo(){
        List<FileVO> fileVOS = new ArrayList<>();
        List<FileVO> dirVOS = new ArrayList<>();

        FileVO fileVO1 = new FileVO();
        fileVO1.setPath("/base/file1");
        fileVO1.setName("file1");
        FileVO fileVO2 = new FileVO();
        fileVO2.setPath("/base/file2");
        fileVO2.setName("file2");
        fileVOS.add(fileVO1);
        fileVOS.add(fileVO2);

        FileVO dirVO1 = new FileVO();
        dirVO1.setPath("/base/dir1");
        dirVO1.setName("dir1");
        FileVO dirVO2 = new FileVO();
        dirVO2.setPath("/base/dir2");
        dirVO2.setName("dir2");
        dirVOS.add(dirVO1);
        dirVOS.add(dirVO2);

        Map<String, List<FileVO>> infos = new HashMap<>();
        infos.put("files", fileVOS);
        infos.put("dirs", dirVOS);

        System.out.println(JSONValue.toJSONString(infos));
    }
}