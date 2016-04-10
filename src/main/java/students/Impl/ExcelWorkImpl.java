package students.Impl;


import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import students.ExcelWork;
import sun.rmi.runtime.Log;
import sun.security.krb5.internal.crypto.Des;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * Created by aleksejpluhin on 06.04.16.
 */
public class ExcelWorkImpl implements ExcelWork {
    private static Logger logger = LoggerFactory.getLogger(ExcelWorkImpl.class);
    private static final String REPORTS_DIRECTORY = System.getProperty("user.home") + "/reports";

    public ExcelWorkImpl(){

    }

    public XSSFWorkbook getWorkbook(String filePath)
    {
        FileInputStream fileInputStream = null;
        File file = null;
        try {
             file = new File(REPORTS_DIRECTORY, "pars.xlsx");
            if(!file.exists()) {
                file.createNewFile();
                Desktop.getDesktop().open(file);
            }
        } catch (Exception e) {
            logger.debug("Пустой файл, заполните");
        }
        XSSFWorkbook workbook = null;
        try {
            fileInputStream = new FileInputStream(file);
            workbook = new XSSFWorkbook(fileInputStream);
        } catch (IOException e) {
            logger.error( "ошибка чтения файла");
        }
        logger.debug("Файл загружен");
        return workbook;
    }


}
