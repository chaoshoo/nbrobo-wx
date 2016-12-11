package com.net.wx.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DefaultHandler2;

import com.net.wx.vo.PicInfo;
import com.net.wx.vo.ReceiveMsg;


/**
 * WeChat message content processor
 */
public class XMLHandler extends DefaultHandler2 {

    private static final Logger log = LoggerFactory.getLogger(XMLHandler.class);

	/**
	 * Message entity definition
	 */
	private ReceiveMsg msg = new ReceiveMsg();

    /**
     * Picture information
     */
    private PicInfo picInfo;

    private List<PicInfo> picList;

    /**
     * Node attribute value
     */
    private String attrVal;

	/**
	 * Get message entity object
     *
     * @return Message entity with data
	 */
	public ReceiveMsg getMsgVO() {
		return this.msg;
	}

    @Override
    public void startElement(String uri,
                             String localName,
                             String qName,
                             Attributes attributes) throws SAXException {
    	if(StringUtils.equals(qName, "PicList")){
    		this.picList = new ArrayList<PicInfo>();
    	}else if(StringUtils.equals(qName, "item")){
    		this.picInfo = new PicInfo();
    	}
    }

    @Override
    public void endElement(String uri,
                           String localName,
                           String qName) throws SAXException {

        if (log.isInfoEnabled()) {
            if (!"xml".equals(qName)) {
                log.info("Current node value[{}]: {}", qName, attrVal);
            }
        }
        if(StringUtils.equals(qName, "MsgID") || StringUtils.equals(qName, "MsgId")){
        	msg.setMsgId(Long.valueOf(attrVal));
        }else if(StringUtils.equals(qName, "CreateTime")){
        	msg.setCreateTime(Long.valueOf(attrVal));
        }else if(StringUtils.equals(qName, "MsgType")){
        	msg.setMsgType(attrVal);
        }else if(StringUtils.equals(qName, "Event")){
        	msg.setMsgType(attrVal);
        }else if(StringUtils.equals(qName, "ToUserName")){
        	msg.setToUserName(attrVal);
        }else if(StringUtils.equals(qName, "FromUserName")){
        	msg.setFromUserName(attrVal);
        }else if(StringUtils.equals(qName, "Content")){
        	msg.setContent(attrVal);
        }else if(StringUtils.equals(qName, "PicUrl")){
        	msg.setPicUrl(attrVal);
        }else if(StringUtils.equals(qName, "MediaId")){
        	msg.setMediaId(attrVal);
        }else if(StringUtils.equals(qName, "Format")){
        	msg.setFormat(attrVal);
        }else if(StringUtils.equals(qName, "Recognition")){
        	msg.setRecognition(attrVal);
        }else if(StringUtils.equals(qName, "ThumbMediaId")){
        	msg.setThumbMediaId(attrVal);
        }else if(StringUtils.equals(qName, "Latitude") || StringUtils.equals(qName, "Location_X")){
        	msg.setLatitude(Double.valueOf(attrVal));
        }else if(StringUtils.equals(qName, "Longitude") || StringUtils.equals(qName, "Location_Y")){
        	msg.setLongitude(Double.valueOf(attrVal));
        }else if(StringUtils.equals(qName, "Scale")){
        	msg.setScale(Integer.parseInt(attrVal));
        }else if(StringUtils.equals(qName, "Label")){
        	msg.setLabel(attrVal);
        }else if(StringUtils.equals(qName, "Title")){
        	msg.setTitle(attrVal);
        }else if(StringUtils.equals(qName, "Description")){
        	msg.setDescription(attrVal);
        }else if(StringUtils.equals(qName, "Url")){
        	msg.setUrl(attrVal);
        }else if(StringUtils.equals(qName, "EventKey")){
        	msg.setEventKey(attrVal);
        }else if(StringUtils.equals(qName, "ComponentVerifyTicket") || StringUtils.equals(qName, "Ticket")){
        	msg.setTicket(attrVal);
        }else if(StringUtils.equals(qName, "Precision")){
        	msg.setPrecision(Double.valueOf(attrVal));
        }else if(StringUtils.equals(qName, "ScanType")){
        	msg.setScanType(attrVal);
        }else if(StringUtils.equals(qName, "ScanResult")){
        	msg.setScanResult(attrVal);
        }else if(StringUtils.equals(qName, "Count")){
        	msg.setCount(Integer.parseInt(attrVal));
        }else if(StringUtils.equals(qName, "PicMd5Sum")){
        	picInfo.setPicMd5Sum(attrVal);
        }else if(StringUtils.equals(qName, "item")){
        	picList.add(picInfo);
        }else if(StringUtils.equals(qName, "PicList")){
        	msg.setPicList(picList);
        }else if(StringUtils.equals(qName, "Poiname")){
        	msg.setPoiName(attrVal);
        }else if(StringUtils.equals(qName, "Status")){
        	msg.setStatus(attrVal);
        }else if(StringUtils.equals(qName, "TotalCount")){
        	msg.setTotalCnt(Integer.parseInt(attrVal));
        }else if(StringUtils.equals(qName, "FilterCount")){
        	msg.setFilterCnt(Integer.parseInt(attrVal));
        }else if(StringUtils.equals(qName, "SentCount")){
        	msg.setSentCnt(Integer.parseInt(attrVal));
        }else if(StringUtils.equals(qName, "ErrorCount")){
        	msg.setErrorCnt(Integer.parseInt(attrVal));
        }else if(StringUtils.equals(qName, "AppId")){
        	msg.setAppId(attrVal);
        }else if(StringUtils.equals(qName, "InfoType")){
        	msg.setInfoType(attrVal);
        }else if(StringUtils.equals(qName, "AuthorizerAppid")){
        	msg.setUnAuthAppid(attrVal);
        }
        
        /*switch (qName) {
            case "MsgId":
            case "MsgID":
                msg.setMsgId(Long.valueOf(attrVal));
                break;
            case "CreateTime":
                msg.setCreateTime(Long.valueOf(attrVal));
                break;
            case "MsgType":
                msg.setMsgType(attrVal);
                break;
            case "Event":
                msg.setEvent(attrVal);
                break;
            case "ToUserName":
                msg.setToUserName(attrVal);
                break;
            case "FromUserName" :
                msg.setFromUserName(attrVal);
                break;
            case "Content":
                msg.setContent(attrVal);
                break;
            case "PicUrl":
                msg.setPicUrl(attrVal);
                break;
            case "MediaId":
                msg.setMediaId(attrVal);
                break;
            case "Format":
                msg.setFormat(attrVal);
                break;
            case "Recognition":
                msg.setRecognition(attrVal);
                break;
            case "ThumbMediaId":
                msg.setThumbMediaId(attrVal);
                break;
            case "Location_X":
            case "Latitude":
                msg.setLatitude(Double.valueOf(attrVal));
                break;
            case "Location_Y":
            case "Longitude":
                msg.setLongitude(Double.valueOf(attrVal));
                break;
            case "Scale":
                msg.setScale(Integer.parseInt(attrVal));
                break;
            case "Label":
                msg.setLabel(attrVal);
                break;
            case "Title":
                msg.setTitle(attrVal);
                break;
            case "Description":
                msg.setDescription(attrVal);
                break;
            case "Url":
                msg.setUrl(attrVal);
                break;
            case "EventKey":
                msg.setEventKey(attrVal);
                break;
            case "Ticket":
            case "ComponentVerifyTicket":
                msg.setTicket(attrVal);
                break;
            case "Precision":
                msg.setPrecision(Double.valueOf(attrVal));
                break;
            case "ScanType":
                msg.setScanType(attrVal);
                break;
            case "ScanResult":
                msg.setScanResult(attrVal);
                break;
            case "Count":
                msg.setCount(Integer.parseInt(attrVal));
                break;
            case "PicMd5Sum":
                picInfo.setPicMd5Sum(attrVal);
                break;
            case "item":
                picList.add(picInfo);
                break;
            case "PicList":
                msg.setPicList(picList);
                break;
            case "Poiname":
                msg.setPoiName(attrVal);
                break;
            case "Status":
                msg.setStatus(attrVal);
                break;
            case "TotalCount":
                msg.setTotalCnt(Integer.parseInt(attrVal));
                break;
            case "FilterCount":
                msg.setFilterCnt(Integer.parseInt(attrVal));
                break;
            case "SentCount":
                msg.setSentCnt(Integer.parseInt(attrVal));
                break;
            case "ErrorCount":
                msg.setErrorCnt(Integer.parseInt(attrVal));
                break;
            case "AppId":
                msg.setAppId(attrVal);
                break;
            case "InfoType":
                msg.setInfoType(attrVal);
                break;
            case "AuthorizerAppid":
                msg.setUnAuthAppid(attrVal);
                break;
            default:
                break;
        }*/
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        this.attrVal = new String(ch, start, length);
    }

    /**
     * Clear currentVOObject cache data
     */
    public void clear() {
        this.picInfo = null;
        this.msg = null;
        this.msg = new ReceiveMsg();
    }
}