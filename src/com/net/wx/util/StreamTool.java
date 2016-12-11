package com.net.wx.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * 输入流与字符串处理工具
 */
public class StreamTool {

    private static final Logger log = LoggerFactory.getLogger(StreamTool.class);

    /**
     * 将字符串转换成输入流
     *
     * @param str 字符串
     * @return 输入流
     */
    public static InputStream toStream(String str) {
        InputStream stream = null;
        try {
            // UTF-8 解决网络传输中的字符集问题
            stream = new ByteArrayInputStream(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            log.error("转换输出Stream异常,不支持的字符集!!!");
            log.error(e.getLocalizedMessage(), e);
        }
        return stream;
    }

    /**
     * 将输入流转换成字符串
     *
     * @param is 输入流
     * @return 字符串
     * @throws IOException
     */
    public static String toString(InputStream is) {
        StringBuffer str = new StringBuffer();
        byte[] b = new byte[1024];

        try {
            for (int n; (n = is.read(b)) != -1; ) {
                str.append(new String(b, 0, n));
            }
        } catch (IOException e) {
            log.error("读取输入流出现异常!!!");
            log.error(e.getLocalizedMessage(), e);
        }
        return str.toString();
    }
    
    
   static int[] t = new int[20];
   static int[] s = new int[20];
    
    public static void dataInit(int a, int b){
    	t[0] = 1;
    	s[0] = 0;
    	for(int i = 1; i<20; i++){
    		t[i] = t[i-1] * 3;
    		s[i] = s[i-1] + t[i];
    	}
    }
    
    public static int getParent(int a){
    	if(a <= 3){
    		return 0;
    	}else{
    		int i ;
            for(i = 0; s[i]<a;i++);  
            i --;  
            int tmp = (2+a-s[i]) /3;  
            a = s[i] - tmp + 1; 
            return a;
    	}
    }
    
    public static int getCloeset(int a, int b){
    	while(a != b){
    		if(a > b){
    			a = getParent(a);
    		}else{
    			b = getParent(b);
    		}
    	}
    	return a;
    }
    
    
    public static void main(String[] args) throws IOException{
    	int a = 13;
    	int b = 9;
    	dataInit(a, b);
    	System.out.println(getCloeset(a, b));
    	
//        Scanner in = new Scanner(System.in);
//        final String fileName = System.getenv("OUTPUT_PATH");
//        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
//        int res;
//        int _node1;
//        _node1 = Integer.parseInt(in.nextLine());
//        
//        int _node2;
//        _node2 = Integer.parseInt(in.nextLine());
//        
//        System.out.println("---:" + _node1 + ", >>>>>>>:" + _node2);
        
//        res = getClosestCommonAncestor(_node1, _node2);
//        bw.write(String.valueOf(res));
//        bw.newLine();
//        
//        bw.close();
    }
}
