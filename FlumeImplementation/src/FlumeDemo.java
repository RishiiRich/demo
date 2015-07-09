import java.io.FileWriter;
import java.io.IOException;
import org.apache.log4j.*;
import org.apache.*;
import org.apache.log4j.BasicConfigurator;
import org.apache.flume.*;
import org.apache.flume.event.*;
import org.apache.flume.source.*;
import org.apache.flume.api.RpcClient;

public class FlumeDemo {
	
 static Logger log=Logger.getLogger(FlumeDemo.class.getName());
	
	public static void main(String args[]) throws IOException
	{
		
		log.debug("debug msg");
	
		PropertyConfigurator.configure("log4j.properties");
		ThreadA a=new ThreadA();
		Thread t1=new Thread(a);
		Thread t2=new Thread(a);
		t1.start();
		t2.start();
		//log.debug("This is debug msg");
	
		log.info("Info msg");
		
	
	}

}
