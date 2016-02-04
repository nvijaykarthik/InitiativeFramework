import groovy.sql.Sql


logger.info("Hello World")

def method = request.method

def sql = new Sql(datasource);


if (!session) {
    session = request.getSession(true)
}

if (!session.groovlet) {
    session.groovlet = 'Groovlets rock!'
}

sql.eachRow('select * from module') { row -> logger.info "${row.description}"};
 

html.html {
    head {
        title 'Groovlet info'
    }
    body {
        h1 'General info'
        ul {
            li "Method: ${method}"
            li "RequestURI: ${request.requestURI}"
            li "session.groovlet: ${session.groovlet}"
            li "application.version: ${application.version}"
			li "application.serverInfo: ${application.serverInfo}"
        }
        
        h1 'Headers'
        ul {
            headers.each {
                li "${it.key} = ${it.value}"
            }
        }
        
        
    }
}