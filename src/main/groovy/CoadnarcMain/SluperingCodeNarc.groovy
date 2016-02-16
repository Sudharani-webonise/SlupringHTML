package CoadnarcMain


import codenarcEntity.CodenarcEntity
import codnackimpl.ExtractHTMLData
import codnackimpl.ReadCodeNArcRules
import groovy.transform.CompileStatic
import groovy.util.logging.Log
import org.codehaus.jackson.JsonGenerationException
import org.codehaus.jackson.map.JsonMappingException
import org.codehaus.jackson.map.ObjectMapper
import org.codehaus.jackson.map.annotate.JsonSerialize

@Log
@CompileStatic
public class SluperingCodeNarc {
    static final String BASE_URL = 'http://codenarc.sourceforge.net/';
    static final String ENTRY_POINT_URL = 'codenarc-rule-index.html';
    private static final String jsonFilePath="/home/webonise/slupringHTMLPARM.json";

    public static void main(String[] args) {
        try {
            ExtractHTMLData extractHTMLData = new ExtractHTMLData();
            ReadCodeNArcRules codeNArcRules = new ReadCodeNArcRules();
            String entryPointHTML = extractHTMLData.executeCurlCommand(BASE_URL + ENTRY_POINT_URL);
            CodenarcEntity codenarcEntity = codeNArcRules.readFileCodenarcRules(entryPointHTML)
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL)
            objectMapper.writeValue(new File(jsonFilePath), codenarcEntity);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}


