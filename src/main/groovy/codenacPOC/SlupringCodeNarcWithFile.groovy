package codenacPOC

import groovy.transform.CompileStatic
import groovy.util.logging.Log

@Log
@CompileStatic
class SlupringCodeNarcWithFile {
	public static void main(String[] args){
        SlupringCodeNarcWithFile slupringCodeNarc = new SlupringCodeNarcWithFile();
        def baseURL = 'http://codenarc.sourceforge.net/';
        String entryPointHTML =  slupringCodeNarc.executeCurlCommand(baseURL+'codenarc-rule-index.html');
        slupringCodeNarc.readFileCodeNarcRuleFile(entryPointHTML)
    }

    void readFileCodeNarcRuleFile(String entryPontHTML) {
        File file = new File("codenarc-rule-index.html");
        String line
        String matchString = '''<h3><a href="./'''
        file.withReader { reader ->
            while ((line = reader.readLine())!=null) {
                if(line.contains(matchString)){
                    String suburl = line.substring(line.lastIndexOf("codenarc"),line.lastIndexOf("html"))+"html"
                    log.info("SUB URL ${suburl}")
                    log.info("SUBSTING ${suburl.substring("codenarc-rules-".length(),suburl.lastIndexOf(".html"))}")
                }
            }
        }
    }

    String executeCurlCommand(String URL) {
        def url = "curl " + URL; //wget for storing file || curl is for get html file
        def proc = url.execute();
        def entryPointHTMLData = new StringBuffer();
        proc.waitForProcessOutput(entryPointHTMLData, System.err)
        return entryPointHTMLData.toString();
    }
}