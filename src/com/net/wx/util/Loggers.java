package com.net.wx.util;

import org.apache.log4j.Logger;

public abstract interface Loggers {
    public static final Logger performance = Logger.getLogger("performance");

    public static final Logger business = Logger.getLogger("business");

    public static final Logger security = Logger.getLogger("security");
}