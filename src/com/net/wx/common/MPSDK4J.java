package com.net.wx.common;

/**
 * WeChat public platformJAVA SDKVersion number declaration
 */
public class MPSDK4J {

    /**
     * Obtain mpsdk4j Version number，Name specification for version number
     *
     * <pre>
     * [Major version number].[Quality number].[Release the serial number]
     * </pre>
     *
     * @return mpsdk4j Version number of the project
     */
    public static String version() {
        return String.format("%d.%s.%d", majorVersion(), releaseLevel(), minorVersion());
    }

    public static int majorVersion() {
        return 1;
    }

    public static int minorVersion() {
        return 24;
    }

    public static String releaseLevel() {
        //a: 内部测试品质, b: 公测品质, r: 最终发布版
        return "a";
    }
}