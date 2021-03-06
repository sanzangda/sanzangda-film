package cn.com.sanzang.controller;

import ch.qos.logback.core.util.FileUtil;
import cn.com.sanzang.vo.FileVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>标题:</p>
 * <p>描述:</p>
 * <p>创建人:wchq</p>
 * <p>日期:2022-03-12</p>
 * <p>时间:13:51</p>
 */
@RestController
@Slf4j
public class FileController {

    @Value("${sanzang.film.rootpath:../../../files}")
    private String rootPath = "../../../files";

    @RequestMapping(value = "/files", method = RequestMethod.GET)
    public Map<String, List<FileVO>> zbyjbsx(@RequestParam(value = "path", required = false) String path) {
        Map<String, List<FileVO>> infos = new HashMap<>();

        // 处理文件路径
        File basePathFile = new File(genUri());
        String basePath = basePathFile.getAbsolutePath() + "/" + rootPath + "/";
        if (StringUtils.isEmpty(path)) {
            path = "./";
        }

        // 读取文件
        List<FileVO> fileVOS = new ArrayList<>();
        List<FileVO> dirVOS = new ArrayList<>();
        File baseDir = new File(basePath + path);

        log.info("读取{}目录下的文件。", baseDir.getAbsolutePath());
        if (!baseDir.isDirectory()){
            log.error("传入路径不正确{}，或者传入路径不是一个文件夹。", path);
            throw new RuntimeException("传入路径不正确，或者传入路径不是一个文件夹。");
        }
        File[] files = baseDir.listFiles();

        if (null == files) {
            //没有文件直接返回
            return new HashMap<>();
        }

        for (File file : files) {
            FileVO fileVO = new FileVO();
            fileVO.setName(file.getName());

            if (file.isDirectory()) {
                // 此处不是用绝对路径，使用文件的相对路径
                fileVO.setPath(path + file.getName() + "/");
                dirVOS.add(fileVO);
            } else {
                // 此处不是用绝对路径，使用文件的相对路径
                fileVO.setPath(path + file.getName());
                fileVOS.add(fileVO);
            }
        }

        infos.put("files", fileVOS);
        infos.put("dirs", dirVOS);

        return infos;
    }

    private static URI genUri() {
        URI uri = null;
        String errmes = "";
        ClassLoader c = FileUtil.class.getClassLoader();
        try {
            uri = c.getResource("").toURI();
        } catch (URISyntaxException e) {
            errmes += e.getMessage() + "\n";
            try {
                uri = c.getResource("/").toURI();
            } catch (Exception e2) {
                log.error(e2.getMessage(),e2);
            }
        }
        if (uri == null) {
            log.error(errmes,new RuntimeException(errmes));
        }
        return uri;
    }

}
