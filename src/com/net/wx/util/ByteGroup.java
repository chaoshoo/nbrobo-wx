package com.net.wx.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Byte processing tool
 */
public class ByteGroup {

    // 定义一个字节容器
	private List<Byte> byteContainer = new ArrayList<Byte>();

    /**
     * The container is converted into an array of bytes
     *
     * @return Byte array
     */
	public byte[] toBytes() {
		byte[] bytes = new byte[byteContainer.size()];
		for (int i = 0; i < byteContainer.size(); i++) {
			bytes[i] = byteContainer.get(i);
		}
		return bytes;
	}

    /**
     * Add byte
     *
     * @param bytes Byte array
     * @return com.qq.weixin.mp.aes.ByteGroup
     */
	public ByteGroup addBytes(byte[] bytes) {
		for (byte b : bytes) {
			byteContainer.add(b);
		}
		return this;
	}

    /**
     * Gets the length of the byte container
     *
     * @return  Container length
     */
	public int size() {
		return byteContainer.size();
	}
}