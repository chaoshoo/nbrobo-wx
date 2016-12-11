package com.net.wx.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;

import java.io.File;
import java.io.IOException;

/**
 * HTTPRequest implementation
 */
public class SimpleHttpReq {

    public static final String TEXT_PLAIN = "text/plain";

    public static final String TEXT_HTML = "text/html";

    public static final String TEXT_XML = "text/xml";

    public static final String APPLICATION_XML = "application/xml";

    public static final String APPLICATION_JSON = "application/json";

    private static final StrResponseHandler respHandler = new StrResponseHandler();

    /**
     * GETrequest
     *
     * @param url Request address
     * @return Response content
     * @throws java.io.IOException
     */
    public static String get(String url) throws IOException {
        String content = Request.Get(url).execute().handleResponse(respHandler);
        return content;
    }

    /**
     * POSTrequest
     *
     * @param url Request address
     * @param contentType Type of request body[text, xml, json, html]
     * @param body Request body
     * @return Response content
     * @throws java.io.IOException
     */
    public static String post(String url,
                              String contentType,
                              String body) throws IOException {
        String content = Request.Post(url)
                .bodyString(body, ContentType.create(contentType,"utf-8"))
                .execute().handleResponse(respHandler);
        return content;
    }

    /**
     * Upload files
     *
     * @param url Request address
     * @param file Upload files
     * @return Response content
     * @throws java.io.IOException
     */
    public static String upload(String url, File file) throws IOException {
        HttpEntity media = MultipartEntityBuilder.create()
                .addPart("media", new FileBody(file)).build();
        String content = Request.Post(url).body(media)
                .execute().handleResponse(respHandler);
        return content;
    }

    /**
     * Download File
     *
     * @param url   Request address
     * @param file File save location
     * @throws java.io.IOException
     */
    public static void download(String url, File file) throws IOException {
        Request.Get(url).execute().saveContent(file);
    }
}