package com.net.wx.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

/**
 * 下载工具类
 * 
 */
public class DownloadUtils {
    public static final String DOWNLOAD_MIME = "application/octet-stream;charset=UTF-8";

    private static final String DISPOSITION = "Content-Disposition";

    private static final String CONTENT_LENGTH = "Content-Length";

    private static final String ATTACH_FILE = "attachment;filename=";

    private static final String INLINE_FILE = "inline;filename=";

    public static String encodeFilename(String filename,
                                        HttpServletRequest request) {
        /*
         * String agent = request.getHeader("USER-AGENT"); try { if(null !=
         * agent && -1 != agent.indexOf("MSIE")) { String newFileName =
         * URLEncoder.encode(filename, "UTF-8"); newFileName =
         * StringUtils.replace(newFileName, "+", "%20"); if(newFileName.length()
         * > 150) { newFileName = new String(filename.getBytes("UTF-8"),
         * "ISO8859-1"); newFileName = StringUtils.replace(newFileName, " ",
         * "%20"); } return newFileName; } else if(null != agent && -1 !=
         * agent.indexOf("Mozilla")) { String result = "=?UTF-8?B?" +
         * String.valueOf(Base64.encode(filename.getBytes("UTF-8"))) + "?=";
         * return result; } else { return filename; } } catch(Exception ex) {
         * return filename; }
         */
        try {
            // 文件名不能有\/:*?"<>|
            filename = filename.replace("\\", "");
            filename = filename.replace("/", "");
            filename = filename.replace(":", "");
            filename = filename.replace("*", "");
            filename = filename.replace("?", "");
            filename = filename.replace("\"", "");
            filename = filename.replace("<", "");
            filename = filename.replace(">", "");
            filename = filename.replace("|", "");
            filename = filename.replace("&", "");
            filename = filename.replace("#", "");
            filename = filename.replace("~", "");
            filename = filename.replace(";", "");
            filename = filename.replace("；", "");
            filename = filename.replace("。", "");
            filename = filename.replace(" ", "");
            filename = filename.replace(" ", "");
            return new String(filename.getBytes(), "ISO8859-1");
        }
        catch (Exception e) {
            return filename;
        }
    }

    public static void download(HttpServletRequest request,
                                HttpServletResponse response,
                                String filename,
                                InputStream is) throws IOException {
        setResponseHeader(request, response, filename);

        OutputStream out = null;
        try {
            out = response.getOutputStream();
            if (is != null && out != null) {
                IOUtils.copy(is, out);
            }
        }
        catch (IOException ex) {
            Loggers.performance.error(ex);
        }
        finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(out);
        }
    }

    public static void downloadInline(HttpServletRequest request,
                                      HttpServletResponse response,
                                      String filename,
                                      byte[] datas) throws IOException {
        setResponseHeaderInline(request, response, filename);
        response.addHeader(CONTENT_LENGTH, String.valueOf(datas.length));
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            out.write(datas, 0, datas.length);
            out.flush();
        }
        catch (IOException ex) {
            Loggers.performance.error(ex);
        }
        finally {
            IOUtils.closeQuietly(out);
        }
    }

    public static void download(HttpServletRequest request,
                                HttpServletResponse response,
                                String filename,
                                byte[] datas) throws IOException {
        setResponseHeader(request, response, filename);
        response.addHeader(CONTENT_LENGTH, String.valueOf(datas.length));
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            out.write(datas, 0, datas.length);
            out.flush();
        }
        catch (IOException ex) {
            Loggers.performance.error(ex);
        }
        finally {
            IOUtils.closeQuietly(out);
        }
    }

    private static void setResponseHeader(HttpServletRequest request,
                                          HttpServletResponse response,
                                          String filename) {
        response.setHeader("Pragma", "public");
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control",
                           "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Cache-Control", "public");
        response.setHeader("Content-Description", "File Transfer");
        String downfileName = encodeFilename(filename, request);
        response.setContentType(DOWNLOAD_MIME);
        response.setHeader(DISPOSITION, ATTACH_FILE
                                        + "\""
                                        + downfileName
                                        + "\"");
    }

    private static void setResponseHeaderInline(HttpServletRequest request,
                                                HttpServletResponse response,
                                                String filename) {
        response.setHeader("Pragma", "public");
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control",
                           "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Cache-Control", "public");
        response.setHeader("Content-Description", "File Transfer");
        String downfileName = encodeFilename(filename, request);
        response.setContentType(DOWNLOAD_MIME);
        response.setHeader(DISPOSITION, INLINE_FILE
                                        + "\""
                                        + downfileName
                                        + "\"");
    }
}