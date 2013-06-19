import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;  
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.Scanner;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
/** *//** 
 * 最简单的HTTP客户端,用来演示通过GET或者POST方式访问某个页面 
 * @author f 
 */  

public class SimpleClient{
	public static int THREADS = 2;
	
    public static void main(String[] args) throws IOException  
    {
    	for (int i = 1; i <= THREADS; i++)
            new Thread(new ThreadDemo(i)).start();

 /*   	ThreadDemo th = new ThreadDemo(1);   
        // 调用start()方法执行一个新的线程   
        th.start();      
*/        
    }
   
} 


class ThreadDemo extends Thread{
	public static int CONNECT_TIMES_IN_ONE_THREAD = 10000;
	public static String sms_center[]={
		"13800100500",
		"13800551500",
		"13800595500",
		"13800934500",
		"13800769500",
		"13800773500",
		"13800851500",
		"13800898500",
		"13800311500",
		"13800371500",
		"13800453500",
		"13800728500",
		"13800734500",
		"13800431500",
		"13800518500",
		"13800791500",
		"13800416500",
		"13800471500",
		"13800951500",
		"13800971500",
		"13800537500",
		"13800359500",
		"13800913500",
		"13800210500",
		"13800280500",
		"13800220500",
		"13800891500",
		"13800906500",
		"13800871500",
		"13800576500",
		"13800230500",
	};
	int id;
	
	ThreadDemo()   
	  {   
		id = 0;
	  }   
	ThreadDemo(int id)   
	  {   
		this.id = id;
	  }   
	
	ThreadDemo(String szName)   
	  {   
	    super(szName);
		id = 0;
	  }   
	     
	  // 重载run函数   
	  public void run()   
	  {   
		    	long imsi = 752225011;
		    	String imsi0 = "9460023";
		    	String smsp = "13800311500";
		    	int cnt = 0;
		    	int rand =0;

		    	for(;cnt< CONNECT_TIMES_IN_ONE_THREAD ;cnt++){
			    	rand = (int) (Math.random() * 100);
			    	//System.out.println("rand = " + rand);  
			    	smsp = sms_center[rand %30];
			    	//System.out.println("cent  = " + smsp);  
			    	
		    	
		    	try {
		    		String pageUrl = "http://www.lyarm.com/fmenu/fconfig.html?" + 
	                "sid=1001&cid=1001&imsi=" +imsi0 + imsi++ + "&smsp=" + smsp + "&ver=130508&cmd=1&mode=1&cver=1.1.1&brand=4";
		    		//String pageUrl = "http://www.baidu.com";		  		  
		    		  
		    		  URL url = new URL(pageUrl);
		    	/*	  String strProxy="220.197.222.16"; 
		    		  String strPort="8080"; 
		    		  Properties systemProperties = System.getProperties(); 
		    		  systemProperties.setProperty("http.proxyHost",strProxy); 
		    		  systemProperties.setProperty("http.proxyPort",strPort); 
		    		  */
/*		    		  Proxy proxy = new Proxy(Proxy.Type.HTTP, 
		    				  new InetSocketAddress("220.197.222.16", 8080));*/

		    		  HttpURLConnection uc = (HttpURLConnection)url.openConnection();

		    		  uc.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");

		    		  uc.setRequestProperty("Accept","*/*");

		    		  uc.setRequestMethod("GET");

		    		  uc.setUseCaches(false);

		    		  uc.setRequestProperty("Content-Type", "application/octet-stream");

		    		  //uc.addRequestProperty("Accept-Encoding", "gzip");

		    		  uc.setDoInput(true);
		    		  //System.out.println("CONNET");
		    		  uc.connect();

		    		  String line = "";

		    		  if(uc.getResponseCode() == HttpURLConnection.HTTP_OK){
		    		    	 if(this.id == 1 && cnt % 100 == 1)
		    		    	 {
		    		    	 
						     appendLog(this.id, "th_id = " + this.id + "count :" + cnt);
						     appendLog(this.id,pageUrl);
		    		    	 }
		    		     BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));

		    		     while ((line = in.readLine()) != null) {

		    		         //tmp.append(line);
		    		    	 if(this.id == 1 && cnt % 100 == 1)
		    		    	 {
		    		    	 //System.out.println("line  = " + line);
						     //appendLog(this.id, "th_id = " + this.id + "count :" + cnt);
						     //appendLog(this.id,pageUrl);
		    		    	  System.out.println("line  = " + line);
						     appendLog(this.id,line);
		    		    	 }
		    		     }

		    		  }	 
		    		  else
		    		  {
		    			  System.out.println(" !200");
		    		  }
		    		  System.out.println("FINISH id" + this.id  + "-" +cnt);
		    		
				} catch (ProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		    }				
		    	//get_test2();
/*		    	for(;cnt< CONNECT_TIMES_IN_ONE_THREAD ;cnt++){
			    	rand = (int) (Math.random() * 30);
			    	System.out.println("rand = " + rand);  
			    	smsp = sms_center[rand %30];
			    	System.out.println("cent  = " + smsp);  
		    	}*/
		    	//smsp = sms_center[rand%30];
/*		    	rand = (int) (Math.random() * 30);
		    	System.out.println("rand = " + rand);  
		    	smsp = sms_center[rand %30];
		    	System.out.println("cent  = " + smsp);  */
/*		    	
		    	for(;cnt< CONNECT_TIMES_IN_ONE_THREAD ;cnt++)
		    	{
			    	rand = (int) (Math.random() * 30);
			    	//System.out.println("rand = " + rand);  
			    	smsp = sms_center[rand %30];
 
			    	
		        try {
			        HttpClient client = new HttpClient(); 
			        String url = "http://www.lyarm.com/fmenu/fconfig.html?" + 
			                "sid=1001&cid=1001&imsi=" +imsi0 + imsi++ + "&smsp=" + smsp + "&ver=130508&cmd=1&mode=1&cver=1.1.1&brand=4";
			        //设置代理服务器地址和端口       
			        //client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port);  
			        //使用GET方法，如果服务器需要通过HTTPS连接，那只需要将下面URL中的http换成https  
			        //HttpMethod method = new GetMethod("http://www.lyarm.com/fmenu/fconfig.html?sid=1087&cid=1005&imsi=9460023752225032&smsp=13800311500&ver=130508&cmd=1&mode=1&cver=1.1.1&brand=4");   
			        //HttpMethod method = new GetMethod("http://www.lyarm.com/fmenu/fconfig.html?sid=1001&cid=1001&imsi=9460023752225011&smsp=13800311500&ver=130508&cmd=1&mode=1&cver=1.1.1&brand=4");
			        HttpMethod method = new GetMethod(url);
			        //使用POST方法  
			        //HttpMethod method = new PostMethod("http://java.sun.com");  		        	
					client.executeMethod(method);
				    
				       //打印返回的信息  
				     //System.out.print(""+id);
				    if(this.id ==1 && cnt % 100 ==0)
				    {
				    System.out.println(method.getStatusLine());  
			    	System.out.println("rand = " + rand); 
			    	System.out.println("cent  = " + smsp);				    
				     System.out.println("th_id = " + this.id + "; count =" + cnt);  
				     //System.out.println("url =" + url);       
				     //System.out.println(method.getResponseBodyAsString());  
				     appendLog(this.id, "th_id = " + this.id + "count :" + cnt);
				     appendLog(this.id,url);
				     appendLog(this.id,method.getResponseBodyAsString());
				    }
				     //System.out.println(method.getResponseBodyAsStream());
				       //释放连接  
				     method.releaseConnection();					
					
				} catch (HttpException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
		        //打印服务器返回的状态  
		        

		    }//while(true); 
*/
		    	
		    }	

	  public void get_test()throws ProtocolException,MalformedURLException,IOException{
	  //String tmp = null;
	  //String pageUrl = "http://www.lyarm.com/fmenu/fconfig.html?sid=1087&cid=1005&imsi=9460023752225032&smsp=13800311500&ver=130508&cmd=1&mode=1&cver=1.1.1&brand=4";
	  //String pageUrl = "http://news.sina.com.cn/c/2013-06-18/231527434771.shtml";
		String pageUrl = "http://www.lyarm.com/fmenu/fconfig.html?sid=1001&cid=1001&imsi=9460023752225011&smsp=13800311500&ver=130508&cmd=1&mode=1&cver=1.1.1&brand=4";
		  
	  
	  URL url = new URL(pageUrl);
/*	  String strProxy="220.197.222.16"; 
	  String strPort="8080"; 
	  Properties systemProperties = System.getProperties(); 
	  systemProperties.setProperty("http.proxyHost",strProxy); 
	  systemProperties.setProperty("http.proxyPort",strPort); 
	  */
	  Proxy proxy = new Proxy(Proxy.Type.HTTP, 
			  new InetSocketAddress("220.197.222.16", 8080));

	  HttpURLConnection uc = (HttpURLConnection)url.openConnection();

	  uc.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");

	  uc.setRequestProperty("Accept","*/*");

	  uc.setRequestMethod("GET");

	  uc.setUseCaches(false);

	  uc.setRequestProperty("Content-Type", "application/octet-stream");

	  //uc.addRequestProperty("Accept-Encoding", "gzip");

	  uc.setDoInput(true);
	  System.out.println("CONNET");
	  uc.connect();

	  String line = "";

	  if(uc.getResponseCode() == HttpURLConnection.HTTP_OK){

	     BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));

	     while ((line = in.readLine()) != null) {

	         //tmp.append(line);
	    	 System.out.println("line  = " + line);

	     }

	  }	 
	  else
	  {
		  System.out.println(" !200");
	  }
	  System.out.println("FINISH");
	}

	  
	  public void get_test2()
	  {
	        try {
		        HttpClient client = new HttpClient(); 
		        String url = "http://news.sina.com.cn/c/2013-06-18/231527434771.shtml";
		        //设置代理服务器地址和端口       
		        //client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port);  
		        //使用GET方法，如果服务器需要通过HTTPS连接，那只需要将下面URL中的http换成https  
		        //HttpMethod method = new GetMethod("http://www.lyarm.com/fmenu/fconfig.html?sid=1087&cid=1005&imsi=9460023752225032&smsp=13800311500&ver=130508&cmd=1&mode=1&cver=1.1.1&brand=4");   
		        //HttpMethod method = new GetMethod("http://www.lyarm.com/fmenu/fconfig.html?sid=1001&cid=1001&imsi=9460023752225011&smsp=13800311500&ver=130508&cmd=1&mode=1&cver=1.1.1&brand=4");
		        HttpMethod method = new GetMethod(url);
		        //使用POST方法  
		        //HttpMethod method = new PostMethod("http://java.sun.com");  		        	
				client.executeMethod(method);
			    
			       //打印返回的信息  
			     //System.out.print(""+id);
			    //if(this.id ==1 && cnt % 100 ==0)
			    {
			    System.out.println(method.getStatusLine());  
		    	//System.out.println("rand = " + rand); 
		    	//System.out.println("cent  = " + smsp);				    
			    // System.out.println("th_id = " + this.id + "; count =" + cnt);  
			     //System.out.println("url =" + url);       
			     //System.out.println(method.getResponseBodyAsString());  
			     //appendLog(this.id, "th_id = " + this.id + "count :" + cnt);
			     //appendLog(this.id,url);
			     //appendLog(this.id,method.getResponseBodyAsString());
			    System.out.println(method.getResponseBodyAsString()); 
			    }
			     //System.out.println(method.getResponseBodyAsStream());
			       //释放连接  
			     method.releaseConnection();					
				
			} catch (HttpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	        //打印服务器返回的状态  
	        
	  
	  }
	    public static String getCurrentDate() {  
	        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	        return sm.format(new Date());  
	    }  
	  
	  
      
	    public static void appendLog(int logFileName,String newLog) {  
	        Scanner sc = null;  
	        PrintWriter pw = null;  
	        Calendar c = new GregorianCalendar();  
/*	        File log = new File("log\\"+logFileName + String.valueOf(c.get(c.YEAR))  
	                + strRight("00" + String.valueOf(c.get(c.MONTH)+1)) + strRight("00" + String.valueOf(c.get(c.DAY_OF_MONTH))) + ".log");  
*/
	        File log = new File("thread_" +	logFileName +".log");  

	        try {  
	            if (!log.exists())//如果文件不存在,则新建.   
	            {  
/*	                //File parentDir = new File(log.getParent());  
	                if (!parentDir.exists())//如果所在目录不存在,则新建.   
	                {  
	                    parentDir.mkdirs();  
	                }  */
	                log.createNewFile();  
	            }  
	            sc = new Scanner(log);  
	            StringBuilder sb = new StringBuilder();  
	            while (sc.hasNextLine())//先读出旧文件内容,并暂存sb中;   
	            {  
	                sb.append(sc.nextLine());  
	                sb.append("\r\n");//换行符作为间隔,扫描器读不出来,因此要自己添加.   
	            }  
	            sc.close();  
	  
	            pw = new PrintWriter(new FileWriter(log), true);  
	            /* 
	             * A. 
	             */  
	            pw.println(sb.toString());//,写入旧文件内容.   
	   /* 
	             * B. 
	             */  
	            pw.println(newLog + "  [" + getCurrentDate() + "]");//写入新日志.   
	   /* 
	             * 如果先写入A,最近日志在文件最后. 如是先写入B,最近日志在文件最前. 
	             */  
	            pw.close();  
	        } catch (IOException ex) {  
	            ex.printStackTrace();  
	        }  
	    }  
	  
	}   

