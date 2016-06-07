package amplitude;
/*
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.NCSARequestLog;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ContextHandlerCollection;
import org.mortbay.jetty.handler.DefaultHandler;
import org.mortbay.jetty.handler.HandlerCollection;
import org.mortbay.jetty.handler.RequestLogHandler;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;
import org.mortbay.thread.BoundedThreadPool;

public class Amplitude
{
  public static void main(String[] args)
      throws Exception
  {
      Server server = new Server();
      
      BoundedThreadPool threadPool = new BoundedThreadPool();
      threadPool.setMaxThreads(100);
      server.setThreadPool(threadPool);
           
      Connector connector=new SelectChannelConnector();
      connector.setPort(80);
      server.setConnectors(new Connector[]{connector});    
      
      HandlerCollection handlers = new HandlerCollection();
      ContextHandlerCollection contexts = new ContextHandlerCollection();
      RequestLogHandler requestLogHandler = new RequestLogHandler();
      handlers.setHandlers(new Handler[]{contexts,new DefaultHandler(),requestLogHandler});
      server.setHandler(handlers);
      
      WebAppContext.addWebApplications(server, "../webapps", null, false, true);
      
      NCSARequestLog requestLog = new NCSARequestLog("../logs/amplitude-yyyy-mm-dd.log");
      requestLog.setExtended(true);
      requestLogHandler.setRequestLog(requestLog);
      
      server.setStopAtShutdown(true);
      server.setSendServerVersion(true);
      
      server.start();
      server.join();  
  }
}
*/
