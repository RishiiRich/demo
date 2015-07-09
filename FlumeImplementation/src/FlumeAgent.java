import java.awt.Event;
import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.flume.agent.embedded.EmbeddedAgent;
import org.apache.flume.event.*;

public class FlumeAgent {



	public void getagent()
	{
	HashMap<String,String> properties=new HashMap<String, String>();
	properties.put("channel1.type", "memory");
	properties.put("channel1.capacity", "200");
	properties.put("sinks", "sink1 sink2");
	properties.put("sink1.type","avro");
	properties.put("sink2.type", "avro");
	properties.put("sink1.hostname", "collector.org.apache.org");
	properties.put("sink1.port", "5564");
	properties.put("sink2.hostname", "collector2.apache.org");
	properties.put("sink2.port",  "5565");
	properties.put("processor.type", "load_balance");
	
	EmbeddedAgent agent=new EmbeddedAgent("myagent");
	
	agent.configure(properties);
	agent.start();
	
	
	
	}
	
}
