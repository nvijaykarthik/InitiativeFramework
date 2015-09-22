import org.apache.log4j.Logger
import org.slf4j.LoggerFactory;


def log = LoggerFactory.getLogger(this.class);
log.info("Welcome");

html.html {
	head {
	
	}
	body{
		div 'Hello'
		h1 'Hello'
	}
}