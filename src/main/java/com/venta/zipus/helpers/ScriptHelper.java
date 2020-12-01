package com.venta.zipus.helpers;

import com.venta.zipus.controllers.user.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.venta.zipus.helpers.FileSystemPaths.PATH_TO_SCRIPT_CHANGE_PAGE_SIZE;

public class ScriptHelper {

    public static String getScriptChangePageSize() {
        try {
            return Files.readString(Path.of(PATH_TO_SCRIPT_CHANGE_PAGE_SIZE));
        } catch (IOException e) {
            Logger logger = LoggerFactory.getLogger(ScriptHelper.class);
            logger.error(e.getMessage());
        }
        return null;
    }
}
