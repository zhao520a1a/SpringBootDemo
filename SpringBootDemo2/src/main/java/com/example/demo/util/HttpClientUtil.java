package com.example.demo.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/** 
 * HTTP 请求工具类 
 */  
public class HttpClientUtil {
	private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	private static RequestConfig requestConfig = 
			RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();
	// 默认编码
	private static String chaset = "UTF-8";
    /** 
     * 发送 GET 请求（HTTP），不带输入数据 
     * @param url 
     * @return 
     */  
    public static String doGet(String url) {  
        return doGet(url,new HashMap<String, Object>(),chaset);  
    } 
    /** 
     * 发送 GET 请求（HTTP），不带输入数据 
     * @param url 
     * @param params 
     * @return 
     */  
    public static String doGet(String url,Map<String, Object> params) {  
        return doGet(url,params,chaset);  
    } 
    /** 
     * 发送 GET 请求（HTTP），K-V形式 
     * @param url 
     * @param params 
     * @param encoding
     * @return 
     */  
    public static String doGet(String url,Map<String, Object> params,String encoding) {
    	String apiUrl = url;  
        StringBuffer param = new StringBuffer();  
        int i = 0; 
        if(params!=null)
        for (String key : params.keySet()) {  
            if (i == 0)  
                param.append("?");  
            else  
                param.append("&");  
            param.append(key).append("=").append(params.get(key));  
            i++;  
        }  
        apiUrl += param;  
        
		String result = null;
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse httpResp = null;
		try {
			HttpGet httpGet = new HttpGet(apiUrl);
			// 设置请求和传输超时时间
			httpGet.setConfig(requestConfig);
			httpclient = HttpClients.createDefault();
			httpResp = httpclient.execute(httpGet);
		
			int statusCode = httpResp.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				result = EntityUtils.toString(httpResp.getEntity(), encoding);
				logger.info("HttpGet方式请求成功！返回结果：{}", result);
			} else {
				logger.info("HttpGet方式请求失败！状态码:" + httpResp.getStatusLine().getStatusCode());
			}
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				if(httpResp != null) httpResp.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
			try {
				if(httpclient != null) httpclient.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return result;
	}
    
    
	public static String doPost(String url, String encoding, Map<String, Object> params) {
		String result = null;
		CloseableHttpResponse httpResp = null;
		CloseableHttpClient httpclient = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			if(params != null && params.size()>0) {
				List<NameValuePair> paramList = new ArrayList<NameValuePair>(params.size());
				for(Entry<String, Object> entry : params.entrySet()) {
					paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(paramList, encoding));
			}
			//设置请求和传输超时时间
			httpPost.setConfig(requestConfig);
			httpclient = HttpClients.createDefault();
			httpResp = httpclient.execute(httpPost);
			int statusCode = httpResp.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				result = EntityUtils.toString(httpResp.getEntity(), encoding);
				logger.info("HttpGet方式请求成功！返回结果：{}", result);
			} else {
				logger.info("HttpGet方式请求失败！状态码:" + httpResp.getStatusLine().getStatusCode());
			}
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				if(httpResp != null) httpResp.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
			try {
				if(httpclient != null) httpclient.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return result;
	}
	/** 
     * 发送 GET 请求（HTTPS），K-V形式 
     * @param url 
     * @param params 
     * @return 
     */  
    public static String doGetSSL(String url, Map<String, Object> params) {
    	return doGetSSL(url,params,chaset);
    }
    /** 
     * 发送 GET 请求（HTTPS），K-V形式 
     * @param url 
     * @param params 
     * @return 
     */  
    public static String doGetSSL(String url, Map<String, Object> params,String encoding) {  
    	String apiUrl = url;  
        StringBuffer param = new StringBuffer();  
        int i = 0;  
        if(params!=null)
        for (String key : params.keySet()) {  
            if (i == 0)  
                param.append("?");  
            else  
                param.append("&");  
            param.append(key).append("=").append(params.get(key));  
            i++;  
        }  
        apiUrl += param;  
        String result = null; 
        CloseableHttpResponse httpResp = null;
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setDefaultRequestConfig(requestConfig).build();    
        try {  
            HttpGet httpPost = new HttpGet(apiUrl);  
            httpResp = httpClient.execute(httpPost);  
            int statusCode = httpResp.getStatusLine().getStatusCode();  
            if (statusCode == HttpStatus.SC_OK) {
				result = EntityUtils.toString(httpResp.getEntity(),encoding);
				logger.info("HttpGet方式请求成功！返回结果：{}", result);
			} else {
				logger.info("HttpGet方式请求失败！状态码:" + httpResp.getStatusLine().getStatusCode());
			}
        } catch (IOException e) {  
        	logger.error(e.getMessage(), e);
        }finally {
			try {
				if(httpResp != null) httpResp.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
			try {
				if(httpClient != null) httpClient.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}  
        return result;  
    }  
    /** 
     * 发送 GET 请求（HTTP），K-V形式 
     * @param url 
     * @param params 
     * @return 
     */  
    public static String doGetEasySSL(String url, Map<String, Object> params) {  
    	String apiUrl = url;  
        StringBuffer param = new StringBuffer();  
        int i = 0;  
        if(params!=null)
        for (String key : params.keySet()) {  
            if (i == 0)  
                param.append("?");  
            else  
                param.append("&");  
            param.append(key).append("=").append(params.get(key));  
            i++;  
        }  
        apiUrl += param;  
        String result = null; 
        CloseableHttpResponse httpResp = null;
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setDefaultRequestConfig(requestConfig).build();    
        try {  
            HttpGet httpPost = new HttpGet(apiUrl);  
            httpResp = httpClient.execute(httpPost);  
            int statusCode = httpResp.getStatusLine().getStatusCode();  
            if (statusCode == HttpStatus.SC_OK) {
				result = EntityUtils.toString(httpResp.getEntity());
				logger.info("HttpGet方式请求成功！返回结果：{}", result);
			} else {
				logger.info("HttpGet方式请求失败！状态码:" + httpResp.getStatusLine().getStatusCode());
			}
        } catch (IOException e) {  
        	logger.error(e.getMessage(), e);
        }finally {
			try {
				if(httpResp != null) httpResp.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
			try {
				if(httpClient != null) httpClient.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}  
        return result; 
    }  
    /** 
     * 发送 GET 请求（HTTPS），K-V形式 
     * 下载文件
     * @param url 
     * @param params 
     * @return 
     */  
    public static byte[] doGetByteEasySSL(String url, Map<String, Object> params) {  
    	String apiUrl = url;  
        StringBuffer param = new StringBuffer();  
        int i = 0;  
        if(params!=null)
        for (String key : params.keySet()) {  
            if (i == 0)  
                param.append("?");  
            else  
                param.append("&");  
            param.append(key).append("=").append(params.get(key));  
            i++;  
        }  
        apiUrl += param;  
        byte result[] = null; 
        CloseableHttpResponse httpResp = null;
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setDefaultRequestConfig(requestConfig).build();    
        try {  
            HttpGet httpPost = new HttpGet(apiUrl);  
            httpResp = httpClient.execute(httpPost);  
            int statusCode = httpResp.getStatusLine().getStatusCode();  
            if (statusCode == HttpStatus.SC_OK) {
				result = EntityUtils.toByteArray(httpResp.getEntity());
				logger.info("HttpGet方式请求成功！返回结果：{}", result);
			} else {
				logger.info("HttpGet方式请求失败！状态码:" + httpResp.getStatusLine().getStatusCode());
			}
        } catch (IOException e) {  
        	logger.error(e.getMessage(), e);
        }finally {
			try {
				if(httpResp != null) httpResp.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
			try {
				if(httpClient != null) httpClient.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}  
        return result; 
    }  
    /** 
     * 发送 POST 请求（HTTP），不带输入数据 
     * @param apiUrl 
     * @return 
     */  
    public static String doPost(String apiUrl) {  
        return doPost(apiUrl, new HashMap<String, Object>());  
    }  
  
    /** 
     * 发送 POST 请求（HTTP），K-V形式 
     * @param apiUrl API接口URL 
     * @param params 参数map 
     * @return 
     */  
    public static String doPost(String apiUrl, Map<String, Object> params) {  
        CloseableHttpClient httpClient = HttpClients.createDefault();  
        String httpStr = null;  
        HttpPost httpPost = new HttpPost(apiUrl);  
        CloseableHttpResponse response = null;  
  
        try {  
            httpPost.setConfig(requestConfig);  
            if(params!=null){
            	 List<NameValuePair> pairList = new ArrayList<>(params.size());  
                 for (Entry<String, Object> entry : params.entrySet()) {
                     NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry  
                             .getValue().toString());  
                     pairList.add(pair);  
                 }  
                 httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));  

            }
            response = httpClient.execute(httpPost);  
            System.out.println(response.toString());  
            HttpEntity entity = response.getEntity();  
            httpStr = EntityUtils.toString(entity, "UTF-8");  
        } catch (IOException e) {  
            logger.error(e.getMessage(), e);  
        } finally {  
            if (response != null) {  
                try {  
                    EntityUtils.consume(response.getEntity());  
                } catch (IOException e) {  
                    logger.error(e.getMessage(), e);  
                }  
            }  
            try {
				if(httpClient != null) httpClient.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
        }  
        return httpStr;  
    }  
    /** 
     * 发送 POST 请求（HTTP），K-V形式 
     * @param url API接口URL
     * @param params 参数map 
     * @return 
     */  
    public static String doPostUpload(String url,String fileName, Map<String, Object> params,byte[] buffer) {
        
        if(buffer==null || StringUtils.isEmpty(url)){
			return null;
		}
		
		String result = null;
		CloseableHttpResponse httpresponse = null;
		CloseableHttpClient httpClient = null;
        try {  
        	HttpPost httpPost = new HttpPost(url);
			//设置上传的文件信息
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody("file", buffer, ContentType.MULTIPART_FORM_DATA, fileName);// 文件流
            if(params != null){
                for(Entry<String, Object> entry : params.entrySet()){
                	builder.addTextBody(entry.getKey(), (String) entry.getValue());
                }
            }
            httpPost.setEntity(builder.build());
            //设置请求和传输超时时间
			httpPost.setConfig(requestConfig);
			httpClient = HttpClients.createDefault();  
			//执行提交
			httpresponse = httpClient.execute(httpPost);
			int statusCode = httpresponse.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
            	HttpEntity responseEntity = httpresponse.getEntity();
	            if (responseEntity != null) {
	                result = EntityUtils.toString(responseEntity);
	            }
	            logger.info("HttpPost方式请求成功！返回结果：{}", result);
            }else{
            	logger.info("HttpPost方式请求失败！状态码：{}，错误原因：{}", 
            			statusCode, httpresponse.getStatusLine().getReasonPhrase());
            }
             
        } catch (IOException e) {  
            logger.error(e.getMessage(), e);  
        } finally {  
        	try {
				if(httpresponse != null) httpresponse.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			} 
            try {
				if(httpClient != null) httpClient.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
        }  
        return result;  
    }  
    /** 
     * 发送 POST 请求（HTTP），JSON形式 
     * @param apiUrl 
     * @param json json对象 
     * @return 
     */  
    public static String doPost(String apiUrl, Object json) {  
        CloseableHttpClient httpClient = HttpClients.createDefault();  
        String httpStr = null;  
        HttpPost httpPost = new HttpPost(apiUrl);  
        CloseableHttpResponse response = null;  
  
        try {  
            httpPost.setConfig(requestConfig);  
            StringEntity stringEntity = new StringEntity(json.toString(),"UTF-8");//解决中文乱码问题  
            stringEntity.setContentEncoding("UTF-8");  
            stringEntity.setContentType("application/json");  
            httpPost.setEntity(stringEntity);  
            response = httpClient.execute(httpPost);  
            HttpEntity entity = response.getEntity();  
            System.out.println("状态码：" + response.getStatusLine().getStatusCode());
            httpStr = EntityUtils.toString(entity, "UTF-8");  
        } catch (IOException e) {  
            logger.error(e.getMessage(), e);  
        } finally {  
            if (response != null) {  
                try {  
                    EntityUtils.consume(response.getEntity());  
                } catch (IOException e) {  
                    logger.error(e.getMessage(), e);  
                }  
            }  
            try {
				if(httpClient != null) httpClient.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
        }  
        return httpStr;  
    }  
  
    /** 
     * 发送 SSL POST 请求（HTTPS），K-V形式 
     * @param apiUrl API接口URL 
     * @param params 参数map 
     * @return 
     */  
    public static String doPostSSL(String apiUrl, Map<String, Object> params) {  
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setDefaultRequestConfig(requestConfig).build();  
        HttpPost httpPost = new HttpPost(apiUrl);  
        CloseableHttpResponse response = null;  
        String httpStr = null;  
  
        try {  
            httpPost.setConfig(requestConfig);  
            if(params!=null){
            	List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());  
                for (Entry<String, Object> entry : params.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry  
                            .getValue().toString());  
                    pairList.add(pair);  
                }  
                httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("utf-8")));  
            }
            response = httpClient.execute(httpPost);  
            int statusCode = response.getStatusLine().getStatusCode();  
            if (statusCode != HttpStatus.SC_OK) {  
                return null;  
            }  
            HttpEntity entity = response.getEntity();  
            if (entity == null) {  
                return null;  
            }  
            httpStr = EntityUtils.toString(entity, "utf-8");  
        } catch (Exception e) {  
            logger.error(e.getMessage(), e);  
        } finally {  
            if (response != null) {  
                try {  
                    EntityUtils.consume(response.getEntity());  
                } catch (IOException e) {  
                    logger.error(e.getMessage(), e);  
                }  
            } 
            try {
				if(httpClient != null) httpClient.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
        }  
        return httpStr;  
    }  
  
    /** 
     * 发送 SSL POST 请求（HTTPS），JSON形式 
     * @param apiUrl API接口URL 
     * @param json JSON对象 
     * @return 
     */  
    public static String doPostSSL(String apiUrl, Object json) {  
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setDefaultRequestConfig(requestConfig).build();  
        HttpPost httpPost = new HttpPost(apiUrl);  
        CloseableHttpResponse response = null;  
        String httpStr = null;  
  
        try {  
            httpPost.setConfig(requestConfig);  
            StringEntity stringEntity = new StringEntity(json.toString(),"UTF-8");//解决中文乱码问题  
            stringEntity.setContentEncoding("UTF-8");  
            stringEntity.setContentType("application/json");  
            httpPost.setEntity(stringEntity);  
            response = httpClient.execute(httpPost);  
            int statusCode = response.getStatusLine().getStatusCode();  
            if (statusCode != HttpStatus.SC_OK) {  
                return null;  
            }  
            HttpEntity entity = response.getEntity();  
            if (entity == null) {  
                return null;  
            }  
            httpStr = EntityUtils.toString(entity, "utf-8");  
        } catch (Exception e) {  
            logger.error(e.getMessage(), e);  
        } finally {  
            if (response != null) {  
                try {  
                    EntityUtils.consume(response.getEntity());  
                } catch (IOException e) {  
                    logger.error(e.getMessage(), e);  
                }  
            }  
            try {
				if(httpClient != null) httpClient.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
        }  
        return httpStr;  
    }  
  
    /** 
     * 创建SSL安全连接 
     * 
     * @return 
     */  
    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {  
    	SSLContext sslContext = null;
		try {
			sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {
				//忽略掉对服务器端证书的校验
				@Override
				public boolean isTrusted(X509Certificate[] ax509certificate, String s) throws CertificateException {
					return true;
				}
 
			}).build();
		} catch (KeyManagementException e) {
			logger.error(e.getMessage(), e);
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage(), e);
		} catch (KeyStoreException e) {
			logger.error(e.getMessage(), e);
		}
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
        return sslsf;  
    }  
}