package codenacPOC

import groovy.transform.CompileStatic
import groovy.util.logging.Log

@Log
@CompileStatic
public class ExtractLink {
    static final String BASE_URL = 'http://codenarc.sourceforge.net/';
    static final String ENTRY_POINT_URL = 'codenarc-rules-design.html';

    public static void main(String[] args) {
        ExtractLink extractLink = new ExtractLink();
        String entryPointHTML = extractLink.executeCurlCommand(BASE_URL + ENTRY_POINT_URL);
        extractLink.readFileCodeNarcRuleFile(entryPointHTML)
    }

    public void readFileCodeNarcRuleFile(String entryPointHTML) {

        println entryPointHTML

        String partOfPatternId,partOfPatternId1;
        String subURL
        int i=0
        entryPointHTML.eachLine { line, count ->
            String lineInString = line;
            if (lineInString.contains("<h3>") || lineInString.contains('''<td align="left">''')) {
                String lineInString1 = line;
                if(lineInString1.contains("<h3>")){
                partOfPatternId1 = lineInString1.substring(lineInString1.lastIndexOf('''<a name="''')+9, lineInString1.lastIndexOf('''">''')-5)
                println(partOfPatternId1)
                }else if(lineInString1.contains('''<td align="left"><b>''') ) {}
                else if(lineInString1.contains('''<td align="left">''') )   {

                    String tdVal = lineInString1.substring(lineInString1.lastIndexOf('''<td align="left">''')+17, lineInString1.lastIndexOf("</td>"))
                    println("---->${tdVal}")
                }
            }
        }
        println(i)
    }

    public String executeCurlCommand(String URL) {
        def url = "curl " + URL;
        def proc = url.execute();
        def HTMLData = new StringBuffer();
        proc.waitForProcessOutput(HTMLData, System.err)
        return HTMLData.toString();
    }
}
