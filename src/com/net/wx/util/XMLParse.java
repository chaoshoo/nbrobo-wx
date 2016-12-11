package com.net.wx.util;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides an interface for extracting the cipher text in the message format and generating a reply message format.
 */
class XMLParse {

    /**
     * ExtractxmlEncrypted messages in a packet
     *
     * @param xmltext To be extractedxmlCharacter string
     * @return Encrypted message string
     * @throws com.qq.weixin.mp.aes.AesException
     */
    public static Object[] extract(String xmltext) throws AesException {
        try {
            SAXParserFactory sax = SAXParserFactory.newInstance();
            SAXParser parser = sax.newSAXParser();
            final Map<String,Object[]> map = new HashMap<String,Object[]>();
            DefaultHandler handler = new DefaultHandler(){
                private Object[] result =  new Object[3];
                private String temp;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    super.startElement(uri, localName, qName, attributes);
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (qName.equalsIgnoreCase("Encrypt")){
                        result[1] = temp;
                        return ;
                    }

                    if (qName.equalsIgnoreCase("ToUserName")){
                        result[2] = temp;
                        return ;
                    }

                    if (qName.equalsIgnoreCase("xml")) {
                        result[0] = 0;
                        map.put("result", result);
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    temp = new String(ch, start, length);
                }
            };

            InputStream is = new ByteArrayInputStream(xmltext.getBytes());
            parser.parse(is, handler);
            return map.get("result");
        } catch (Exception e) {
            throw new AesException(AesException.ParseXmlError);
        }

    }
}