package com.net.wx.vo;

/**
 * Pop up WeChat photo albums or photo photo
 */
public class PicInfo {

    /**
     * PictureMD5value，If developers need，Can be used to verify the reception to the picture
     */
    private String picMd5Sum;

    public String getPicMd5Sum() {
        return picMd5Sum;
    }

    public void setPicMd5Sum(String picMd5Sum) {
        this.picMd5Sum = picMd5Sum;
    }

    @Override
    public String toString() {
        return "PicInfo{" +
                "picMd5Sum='" + picMd5Sum + '\'' +
                '}';
    }
}