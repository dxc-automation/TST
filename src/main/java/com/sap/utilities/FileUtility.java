package com.sap.utilities;

import com.sap.config.GeneralTestConfig;
import com.google.gson.*;
import okhttp3.MediaType;
import org.apache.commons.io.FileUtils;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static com.sap.properties.FilePaths.reportFolder;
import static com.sap.properties.FilePaths.*;
import static org.apache.commons.io.FileUtils.cleanDirectory;


public class FileUtility extends GeneralTestConfig {

    public static final JsonParser parser     = new JsonParser();
    public static final JSONParser jsonParser = new JSONParser();
    public static final Gson gson = new Gson();
    public static final MediaType MediaTypeJSON = MediaType.parse("application/json; charset=utf-8");

    private static String date;


    public static void deleteFolderContent(String folder) throws IOException {
        File dir = new File(folder);

        if (dir.exists() && dir.isDirectory()) {
            FileUtils.deleteDirectory(dir);
            FileUtils.forceMkdir(dir);
        } else {
            FileUtils.forceMkdir(dir);
        }
    }


    //***   Zip folder
    public static void zipReport() throws IOException {
        String sourceDirPath = reportFolder;
        String zipFilePath   = reportZipFile + "/SAPGUIReport.zip";
        Path p = Files.createFile(Paths.get(zipFilePath));
        try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(p))) {
            Path pp = Paths.get(sourceDirPath);
            Files.walk(pp)
                    .filter(path -> !Files.isDirectory(path))
                    .forEach(path -> {
                        ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
                        try {
                            zs.putNextEntry(zipEntry);
                            Files.copy(path, zs);
                            zs.closeEntry();
                        } catch (IOException e) {
                            System.err.println(e);
                        }
                    });
        }
        System.out.println("Zip folder completed");
    }


    public static String getDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        date = dateTimeFormatter.format(localDate);
        return date;
    }


    public static Timestamp getTime(Long timestamp) throws Exception {
        Timestamp time = new Timestamp(timestamp);
        return time;
    }


    public static String getFileName(Method method) throws Exception {
        String fileName = method.getName() + ".json";
        return fileName;
    }


    public void createDebugFile(String responseBody) throws IOException {
            File file = new File(reportFolder + "log.txt");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(responseBody);
            fileWriter.flush();
            fileWriter.close();
    }
}