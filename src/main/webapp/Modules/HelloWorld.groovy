import org.apache.log4j.Logger


def log = Logger.getLogger(this.class);
log.info("Welcome");
log.debug("Welcome Debug");

html.html {
	head {
	
	}
	body{
		div 'Hello'
		h1 'Hello'
	}
}