package com.kuretru.web.navigation.schedule;

import com.kuretru.api.common.configuration.CommonProperties;
import com.kuretru.web.navigation.configuration.SystemConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author 呉真 Kuretru < kuretru@gmail.com >
 */
@Component
@Slf4j
public class CleanTemporarySchedule {

    private final CommonProperties commonProperties;

    @Autowired
    public CleanTemporarySchedule(CommonProperties commonProperties) {
        this.commonProperties = commonProperties;
    }

    /**
     * 每天凌晨2点03分，清理网站图标下载目录
     */
    @Scheduled(cron = "3 2 0 * * ? ")
    public void cleanFaviconDirectory() {
        String path = commonProperties.getFileUploadRoot() + SystemConstants.TEMPORARY_DIRECTORY + File.separator;
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
            return;
        }
        File[] files = directory.listFiles();
        if (files == null || files.length == 0) {
            log.info("No file in {} .", path);
            return;
        }
        int cleanedCount = 0;
        for (File file : files) {
            if (file.delete()) {
                cleanedCount++;
            }
        }
        log.info("Cleaned {}/{} files in {} .", cleanedCount, files.length, path);
    }

}
