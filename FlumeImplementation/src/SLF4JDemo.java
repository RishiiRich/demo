
import org.slf4j.*;

public class SLF4JDemo {
	
	static Logger log=LoggerFactory.getLogger(SLF4JDemo.class);
	
	
	public static void main(String args[])
	{
		for(int i=0;i<5;i++)
		{
			if(i%2==0)
			{
			log.info("this is info msg {}",i);
		
			}
			else
			{
				log.debug("This is debug msg {}",i);
			}
		}
	}

}
