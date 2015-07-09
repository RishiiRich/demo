import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.PollableSource;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.EventBuilder;
import org.apache.flume.instrumentation.SourceCounter;
import org.apache.flume.source.AbstractSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;

/**
 * Created by Naredra on 3/20/2015.
 */
public class Source extends AbstractSource implements Configurable,PollableSource {
    private String path;
    private BufferedReader bufferedReader;
    private SourceCounter sourceCounter;
    private final static Logger logger= LoggerFactory.getLogger(Source.class.getName());
    private static int SLEEP_TIME=1000;

    @Override
    public synchronized void configure(Context context) {
        //this.path=context.getString("./file.log");
    	this.path=context.getString("filepath");
    	this.path=this.path==null?"/etc/flume/conf/flume_config.conf":this.path;
        if(sourceCounter==null)
            sourceCounter=new SourceCounter(getName());
    }

    @Override
    public synchronized void start(){
        logger.info("staring..",this);
        try {
            bufferedReader=new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        super.start();
        sourceCounter.start();
        logger.info("started...",this);
    }

    @Override
    public synchronized void stop(){
        super.stop();
        sourceCounter.stop();
    }

    @Override
    public Status process() throws EventDeliveryException {
        String line=null;
        Status status;
        try {
            line = bufferedReader.readLine();
            while(line==null){
                Thread.sleep(SLEEP_TIME);
                line=bufferedReader.readLine();
            }
            line=line==null?"A":line;
            Event event= EventBuilder.withBody(line, Charset.forName("UTF-8"),new HashMap<String, String>(){{put("host","nakumar");}});
            getChannelProcessor().processEvent(event);
            status=Status.READY;
            sourceCounter.addToEventAcceptedCount(1);
        } catch (IOException e) {
            logger.error(e.getMessage());
            status=Status.BACKOFF;
        } catch (InterruptedException e) {
            logger.warn("sfsadf");
            status=Status.READY;
        }
        return status;
    }
}