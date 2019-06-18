package com.eports.test;

import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.builder.HCB;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpHeader;
import com.arronlong.httpclientutil.common.HttpResult;
import com.arronlong.httpclientutil.common.SSLs.SSLProtocolVersion;
import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.testng.annotations.Test;
import utils.AssertJSON;
import utils.CsvUtil2;


public class TestDemo {

    @Test
    public void apiTestCase() throws Exception {
        String result = null; // 返回值
        String urlLink = null; // url
        String params = null; // 入参
        String requestmethod = null; // 请求方式
        Object[][] data = CsvUtil2.readCSV("case/api_testdata.csv");
        for(Object[] td : data) {
            urlLink = td[1].toString();
            params = td[2].toString();
            requestmethod = td[9].toString();
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("urlLink:" + urlLink);
            System.out.println("params:" + params);
            System.out.println("requestmethod:" + requestmethod);
            //插件式配置Header（各种header信息、自定义header）
            Header[] headers = HttpHeader.custom()
                    .userAgent("javacl")
                    .other("Content-Type", "application/json")
                    .build();

            //插件式配置生成HttpClient时所需参数（超时、连接池、ssl、重试）
            HCB hcb = HCB.custom()
                    .timeout(1000) //超时
                    .pool(100, 10) //启用连接池，每个路由最大创建10个链接，总连接数限制为100个
                    .sslpv(SSLProtocolVersion.TLSv1_2) 	//设置ssl版本号，默认SSLv3，也可以调用sslpv("TLSv1.2")
                    .ssl()  	  	//https，支持自定义ssl证书路径和密码，ssl(String keyStorePath, String keyStorepass)
                    .retry(5)		//重试5次
                    ;

            HttpClient client = hcb.build();

//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("key1", "value1");
//			map.put("key2", "value2");

            //插件式配置请求参数（网址、请求参数、编码、client）
            HttpConfig config = HttpConfig.custom()
                    .headers(headers)	//设置headers，不需要时则无需设置
                    .url(urlLink)	          //设置请求的url
                    //.map(map)	          //设置请求参数，没有则无需设置
                    .encoding("utf-8") //设置请求和返回编码，默认就是Charset.defaultCharset()
                    .client(client)    //如果只是简单使用，无需设置，会自动获取默认的一个client对象
                    //.inenc("utf-8")  //设置请求编码，如果请求返回一直，不需要再单独设置
                    //.inenc("utf-8")	//设置返回编码，如果请求返回一直，不需要再单独设置
                    .json(params)     //json方式请求的话，就不用设置map方法，当然二者可以共用。
                    //.context(HttpCookies.custom().getContext()) //设置cookie，用于完成携带cookie的操作
                    //.out(new FileOutputStream("保存地址"))       //下载的话，设置这个方法,否则不要设置
                    //.files(new String[]{"d:/1.txt","d:/2.txt"}) //上传的话，传递文件路径，一般还需map配置，设置服务器保存路径
                    ;


            //HttpClientUtil.down(config);                   //下载，需要调用config.out(fileOutputStream对象)
            //HttpClientUtil.upload(config);                 //上传，需要调用config.files(文件路径数组)

            if (requestmethod.equals("Post")) {
                result = HttpClientUtil.post(config); // post请求
            } else if (requestmethod.equals("Get")) {
                result = HttpClientUtil.get(config); // get请求
            } else {
                result = "false";
            }
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("result: " + result);
            AssertJSON.assertThat(result).jsonPathAsString("$msg").isNotEmpty();
            AssertJSON.assertThat(result).jsonPathAsInteger("$code").isEqualTo(0).as("比较code的内容");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++");

            //如果指向看是否访问正常
            //String result3 = HttpClientUtil.head(config); // 返回Http协议号+状态码
            //int statusCode = HttpClientUtil.status(config);//返回状态码

            //[新增方法]sendAndGetResp，可以返回原生的HttpResponse对象，
            //同时返回常用的几类对象：result、header、StatusLine、StatusCode
            HttpResult respResult = HttpClientUtil.sendAndGetResp(config);
            System.out.println("返回结果：\n"+respResult.getResult());
            System.out.println("返回resp-header："+respResult.getRespHeaders());//可以遍历
            System.out.println("返回具体resp-header："+respResult.getHeaders("Date"));
            System.out.println("返回StatusLine对象："+respResult.getStatusLine());
            System.out.println("返回StatusCode："+respResult.getStatusCode());
            System.out.println("返回HttpResponse对象）（可自行处理）："+respResult.getResp());


        }


//
        // ID是否为空
//		AssertJSON.assertThat(result).jsonPathAsString("$msg").isNotEmpty();
//		AssertJSON.assertThat(result).jsonPathAsString("code").isEqualTo("0").as("比较code的内容");
//		AssertJSON.assertThat(result).jsonPathAsString("$.name").isEqualTo("lovelywonderful").as("比较code的内容");
//		AssertJSON.assertThat(result).jsonPathAsInteger("$.score").isEqualTo(4).as("比较score的值");
//		AssertJSON.assertThat(result).jsonPathAsString("$.insensitive").isEqualTo("lovelywonderful").as("比较insensitive的内容");
//		AssertJSON.assertThat(result).jsonPathAsInteger("$._id").isEqualTo("5b5e75572a22f708003a9aa2").as("比较_id的值");

    }
}


