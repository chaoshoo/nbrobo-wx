package com.net.wx.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.net.wx.Constants;
import com.net.wx.common.WxMsgType;
import com.net.wx.service.ArticleService;
import com.net.wx.util.AesException;
import com.net.wx.util.SHA1;
import com.net.wx.vo.OutPutMsg;
import com.net.wx.vo.PicInfo;
import com.net.wx.vo.ReceiveMsg;


/**
 * Default WeChat message processor
 */
public class WxDefaultHandler implements WxHandler {

    private static final Logger log = LoggerFactory.getLogger(WxDefaultHandler.class);
    
    
    @Override
    public boolean check(String token,
                        String signature,
                        String timestamp,
                        String nonce) throws AesException {

        if (null == signature
                || signature.length() > 128
                || null == timestamp
                || timestamp.length() > 128
                || null == nonce
                || nonce.length() > 128) {
            log.error("Verify signature parameter failed!!!");
            return false;
        }

        if (log.isInfoEnabled()) {
           log.info("WeChat accessURLVerify success...");
        }
        String s = SHA1.calculate(token, timestamp, nonce);
        if (s.equals(signature)){
            return true;
        }

        return false;
    }


    @Override
    public OutPutMsg def(ReceiveMsg rm) {
        OutPutMsg om = new OutPutMsg(rm);
        //进入未识别关键字
//    	WxUnknowKetword wxUnknowKetword = getWxUnknowKeword();
//    	if(null != wxUnknowKetword){
//    		setOutPutMsg(wxUnknowKetword.getUwReplyType(),wxUnknowKetword.getUwReplyContent(),om);
//    	}else{
//	        om.setMsgType(WxMsgType.text.name());
//	        om.setContent("系统正在维护中,请稍候再试[微笑].");
//    	}
//        if (log.isInfoEnabled()) {
//            log.info("微信新类型消息!!!");
//        }
        return om;
    }

 

    @Override
    public OutPutMsg image(ReceiveMsg rm) {
        OutPutMsg om = new OutPutMsg(rm);
        om.setMsgType(rm.getMsgType());
        om.setMediaId(rm.getMediaId());
        if (log.isInfoEnabled()) {
            log.info("Receive a message from WeChat image...");
        }
        return om;
    }

    @Override
    public OutPutMsg voice(ReceiveMsg rm) {
        OutPutMsg om = new OutPutMsg(rm);
        if (null != rm.getRecognition()) {
            om.setMsgType(WxMsgType.text.name());
            om.setContent("Your voice message has been received..[Smile]\nContent as："+rm.getRecognition());
        } else {
            om.setMsgType(rm.getMsgType());
            om.setMediaId(rm.getMediaId());
        }
        if (log.isInfoEnabled()) {
            log.info("Receive audio message...");
        }
        return om;
    }

    @Override
    public OutPutMsg video(ReceiveMsg rm) {
        OutPutMsg om = new OutPutMsg(rm);
        om.setMsgType(rm.getMsgType());
        om.setMediaId(rm.getMediaId());
        if (log.isInfoEnabled()) {
            log.info("Receive video message...");
        }
        return om;
    }

    @Override
    public OutPutMsg location(ReceiveMsg rm) {
        OutPutMsg om = new OutPutMsg(rm);
        om.setMsgType(WxMsgType.text.name());
        om.setContent("Your current location:"+rm.getLabel()+
                ",coordinate:["+rm.getLatitude()+","+rm.getLongitude()+
                "],Map zoom level:"+rm.getScale());
        if (log.isInfoEnabled()) {
            log.info("Receive location messages...");
        }
        return om;
    }

    @Override
    public OutPutMsg link(ReceiveMsg rm) {
        OutPutMsg om = new OutPutMsg(rm);
        om.setMsgType(WxMsgType.text.name());
        om.setContent(rm.getTitle()+"\n<a href=\""+rm.getUrl()+"\">Click to open</a>");
        if (log.isInfoEnabled()) {
            log.info("Receive link message...");
        }
        return om;
    }

    @Override
    public OutPutMsg eClick(ReceiveMsg rm, String dpid) {
    	OutPutMsg om = new OutPutMsg(rm);
    	int articleId = Integer.parseInt(rm.getEventKey());
		JSONObject obj = new JSONObject();
		obj.put("fromUserName", rm.getFromUserName());
		obj.put("toUserName", rm.getToUserName());
		obj.put("dpid", dpid);
		om = ArticleService.getCunjiankang(articleId,obj);
        //om.setMsgType(WxMsgType.text.name());
        //om.setContent("MENU_CLICK:"+rm.getEventKey());
        if (log.isInfoEnabled()) {
            log.info("Receive menu click message...");
        }
        System.out.println("om---->"+om);
        return om;
    }

    @Override
    public void eView(ReceiveMsg rm) {
        log.info("Receive menu view jump message...");
    }

    @Override
    public OutPutMsg eSub(ReceiveMsg rm) {
        OutPutMsg om = new OutPutMsg(rm);
    	
	        om.setMsgType(WxMsgType.text.name());
	        om.setContent("Welcome platform system.");
        
        if (log.isInfoEnabled()) {
            log.info("Receive a subscription message...");
        }
        return om;
    }

    @Override
    public void eUnSub(ReceiveMsg rm) {
        if (log.isInfoEnabled()) {
            log.info("Unsubscribe message received...");
        }
    }

    @Override
    public OutPutMsg eScan(ReceiveMsg rm) {
        if (log.isInfoEnabled()) {
            log.info("Receive scan messages...");
        }
        return null;
    }

    @Override
    public OutPutMsg eLocation(ReceiveMsg rm) {
        if (log.isInfoEnabled()) {
            log.info("Receive location messages...");
        }
        return null;
    }

    @Override
    public OutPutMsg eScanCodePush(ReceiveMsg rm) {
        OutPutMsg om = new OutPutMsg(rm);
        om.setMsgType(WxMsgType.text.name());
        String content = "You use QR code scanning menu["+rm.getEventKey()+"],Scan results for: "+rm.getScanResult();
        om.setContent(content);
        if (log.isInfoEnabled()) {
            log.info("Receive two-dimensional code scanning event messages...");
        }
        return om;
    }

    @Override
    public OutPutMsg eScanCodeWait(ReceiveMsg rm) {
        OutPutMsg om = new OutPutMsg(rm);
        om.setMsgType(WxMsgType.text.name());
        String content = "You use the scan to wait for the menu["+rm.getEventKey()+"],Scan results for: "+rm.getScanResult();
        om.setContent(content);
        if (log.isInfoEnabled()) {
            log.info("Receive sweep push events and pop“Message reception”Prompt message...");
        }
        return om;
    }

    @Override
    public OutPutMsg ePicSysPhoto(ReceiveMsg rm) {
        OutPutMsg om = new OutPutMsg(rm);
        om.setMsgType(WxMsgType.text.name());
        String content = "You use the system to take pictures["+rm.getEventKey()+"]Co hair"+rm.getCount()+"Zhang pictures,PictureMD5Value: ";
        for (PicInfo pic : rm.getPicList()) {
            content += pic.getPicMd5Sum() + ", ";
        }
        om.setContent(content.substring(0, content.lastIndexOf(",")));
        if (log.isInfoEnabled()) {
            log.info("Receive menu pop-up system to take pictures of the message...");
        }
        return om;
    }

    @Override
    public OutPutMsg ePicPhotoOrAlbum(ReceiveMsg rm) {
        OutPutMsg om = new OutPutMsg(rm);
        om.setMsgType(WxMsgType.text.name());
        String content = "You used to take pictures or album["+rm.getEventKey()+"]Co hair"+rm.getCount()+"Zhang pictures,PictureMD5Value: ";
        for (PicInfo pic : rm.getPicList()) {
            content += pic.getPicMd5Sum() + ", ";
        }
        om.setContent(content.substring(0, content.lastIndexOf(",")));
        if (log.isInfoEnabled()) {
            log.info("Receive a pop-up menu to take pictures or photo album message...");
        }
        return om;
    }

    @Override
    public OutPutMsg ePicWeixin(ReceiveMsg rm) {
        OutPutMsg om = new OutPutMsg(rm);
        om.setMsgType(WxMsgType.text.name());
        String content = "You use WeChat album["+rm.getEventKey()+"]Co hair"+rm.getCount()+"Zhang pictures,PictureMD5Value: ";
        for (PicInfo pic : rm.getPicList()) {
            content += pic.getPicMd5Sum() + ", ";
        }
        om.setContent(content.substring(0, content.lastIndexOf(",")));
        if (log.isInfoEnabled()) {
            log.info("Received a menu WeChat photo album message...");
        }
        return om;
    }

    @Override
    public OutPutMsg eLocationSelect(ReceiveMsg rm) {
        OutPutMsg om = new OutPutMsg(rm);
        om.setMsgType(WxMsgType.text.name());
        om.setContent("Menu value:"+rm.getEventKey()+",Your current location:"+rm.getLabel()+
                ",coordinate:["+rm.getLatitude()+","+rm.getLongitude()+
                "],Map zoom level:"+rm.getScale()+",Moments:"+rm.getPoiName());
        if (log.isInfoEnabled()) {
            log.info("Receive menu location message...");
        }
        return om;
    }

    @Override
    public void eTemplateFinish(ReceiveMsg rm) {
       if (log.isInfoEnabled()) {
            log.info("Receive template push messages...");
        }
    }

    @Override
    public void eSendJobFinish(ReceiveMsg rm) {
        if (log.isInfoEnabled()) {
            log.info("Receiving mass push messages...");
        }
    }

    @Override
    public void eComponentVerifyTicket(ReceiveMsg rm) {
        if (log.isDebugEnabled()) {
            log.info("Receive component of WeChat open platform pushTicketMessage...");
        }
    }

    @Override
    public void eUnAuthorizerMP(ReceiveMsg rm) {
        if (log.isDebugEnabled()) {
            log.info("Receive the WeChat open platform to push the public number to cancel the authorization message...");
        }
    }
    
    //设置回复的消息内容 
    public void setOutPutMsg(String msgType,String msgContent,OutPutMsg om){
    	if(Constants.SEND_TEXT_TYPE.equals(msgType)){
    		om.setMsgType(WxApi.TEXT);
    		om.setContent(msgContent);
    	}else if(Constants.SEND_MPNEWS_TYPE.equals(msgType)){
    		om.setMsgType(WxApi.NEWS);
    		//获取图文消息的数据
//    		WxPicTxtMsgArticle wxPtma = getWxptma(msgContent);
//    		if(null != wxPtma){
//    			WxPicMsg wxPm = getWxpm(wxPtma.getPicMediaId());
//    			if(null != wxPm){
//    				Article art = new Article(wxPtma.getPtmaTitle(),wxPtma.getPtmaSummary(),wxPm.getMediaUrl(),wxPtma.getPtmaUrl());
//    				om.getArticles().add(art);
//    			}
//    		}
    	}else if(Constants.SEND_IMAGE_TYPE.equals(msgType)){
    		om.setMsgType(WxApi.IMAGE);
    		om.setMediaId(msgContent);
    	}
    }


	@Override
	public OutPutMsg text(ReceiveMsg rm,String dpid) {
		// TODO Auto-generated method stub
		
		OutPutMsg om = new OutPutMsg(rm);
    	int articleId = Integer.parseInt(rm.getContent());
		JSONObject obj = new JSONObject();
		obj.put("fromUserName", rm.getFromUserName());
		obj.put("toUserName", rm.getToUserName());
		obj.put("dpid", dpid);
		om = ArticleService.getCunjiankang(articleId,obj);
        //om.setMsgType(WxMsgType.text.name());
        //om.setContent("MENU_CLICK:"+rm.getEventKey());
        if (log.isInfoEnabled()) {
            log.info("Receive menu click message...");
        }
        System.out.println("om---->"+om);
        return om;
	}
    
    //获取关注欢迎语消息的单条记录 
    
   
}