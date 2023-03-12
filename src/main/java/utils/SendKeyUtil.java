package utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendKeyUtil {
    public static String getKey(String path,String token) throws IOException {
        URL url = new URL(path);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");//请求post方式
        con.setUseCaches(false); // Post请求不能使用缓存
        con.setDoInput(true);// 设置是否从HttpURLConnection输入，默认值为 true
        con.setDoOutput(true);// 设置是否使用HttpURLConnection进行输出，默认值为 false
        //设置header内的参数 connection.setRequestProperty("健, "值");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("isTree", "true");
        con.setRequestProperty("isLastPage", "true");
        // 建立实际的连接
        con.connect();
        // 得到请求的输出流对象
        OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream(),"UTF-8");
        writer.write(token);
        writer.flush();
        // 获取服务端响应，通过输入流来读取URL的响应
        InputStream is = con.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        StringBuffer sbf = new StringBuffer();
        String strRead = null;
        while ((strRead = reader.readLine()) != null) {
            sbf.append(strRead);
        }
        reader.close();
        // 关闭连接
        con.disconnect();
        return sbf.toString();
    }

    public static void main(String[] args) {

    }
}
