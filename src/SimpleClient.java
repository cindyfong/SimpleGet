import java.io.File;
import java.io.FileWriter;
import java.io.IOException;  
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
	public static int THREADS = 20;
	
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
		    	
		    	for(;cnt< CONNECT_TIMES_IN_ONE_THREAD ;cnt++)
		    	{
			    	rand = (int) (Math.random() * 30);
			    	//System.out.println("rand = " + rand);  
			    	smsp = sms_center[rand %30];
			    	System.out.println("rand = " + rand); 
			    	System.out.println("cent  = " + smsp); 
			    	
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
				    System.out.println(method.getStatusLine());  
				       //打印返回的信息  
				     //System.out.print(""+id);
				     System.out.println("th_id = " + this.id + "; count =" + cnt);  
				     //System.out.println("url =" + url);       
				     //System.out.println(method.getResponseBodyAsString());  
				     appendLog(this.id, "th_id = " + this.id + "count :" + cnt);
				     appendLog(this.id,url);
				     appendLog(this.id,method.getResponseBodyAsString());
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

