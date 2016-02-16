package CoadnarcMain


import codenarcEntity.CodenarcEntity
import codnackimpl.ExtractHTMLData
import codnackimpl.ReadCodeNArcRules
import com.google.gson.Gson
import groovy.transform.CompileStatic
import groovy.util.logging.Log

@Log
@CompileStatic
public class SluperingCodeNarc {
    static final String BASE_URL = 'http://codenarc.sourceforge.net/';
    static final String ENTRY_POINT_URL = 'codenarc-rule-index.html';
    private static final String jsonFilePath="/home/webonise/slupringHTML.json";

    public static void main(String[] args) {
        try {
            ExtractHTMLData extractHTMLData = new ExtractHTMLData();
            ReadCodeNArcRules codeNArcRules = new ReadCodeNArcRules();
            String entryPointHTML = extractHTMLData.executeCurlCommand(BASE_URL + ENTRY_POINT_URL);
            CodenarcEntity coadnarcEntity = codeNArcRules.readFileCodeNarcRules(entryPointHTML)
            Gson gson = new Gson();
            String jsonRepresentation = gson.toJson(coadnarcEntity);
            FileWriter Filewriter = new FileWriter(jsonFilePath);
            Filewriter.write(jsonRepresentation);
            Filewriter.close();
        } catch (Exception e) {
            log.info("Error {} " + e)
        }
    }
}

