
public class ThreadA implements Runnable
	{

		public void run()
		{
			//FileWriter fw;
			try {
				//fw = new FileWriter("thread.txt",true);
			
			while(true)
			{
				System.out.println(Thread.currentThread().getName());
				

					FlumeDemo.log.debug(Thread.currentThread().getName());
					
				
				 
			}
			//fw.close();
		}	catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
				}
		}
	}

