package com.net.wx.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

/**
 * List of followers
 */
public class FollowList {

    /**
     * Pay attention to the total number of users of the public account
     */
	private int total;

    /**
     * Draw offOPENIDNumber，The maximum value is10000
     */
	private int count;

    /**
     * List data，OPENIDList of
     */
	private List<String> openIds = new ArrayList<String>();

    /**
     * After pulling the list of a user'sOPENID
     */
    @JSONField(name = "next_openid")
	private String nextOpenId;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<String> getOpenIds() {
        return openIds;
    }

    public void setOpenIds(List<String> openIds) {
        this.openIds = openIds;
    }

    public String getNextOpenId() {
        return nextOpenId;
    }

    public void setNextOpenId(String nextOpenId) {
        this.nextOpenId = nextOpenId;
    }

    @Override
    public String toString() {
        return "FollowList{" +
                "total=" + total +
                ", count=" + count +
                ", openIds=" + (openIds.size()) +
                ", nextOpenId='" + nextOpenId + '\'' +
                '}';
    }
}