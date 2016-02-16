package codnackimpl

import codenarcEntity.CodenarcEntity
import codenarcEntity.Patterns
import groovy.transform.CompileStatic
import groovy.util.logging.Log

@Log
@CompileStatic
class ReadCodeNArcRules {
    static final String BASE_URL = 'http://codenarc.sourceforge.net/';

    public CodenarcEntity readFileCodeNarcRules(String entryPointHTML) {
        CodenarcEntity codenarcEntity = new CodenarcEntity();
        List<Patterns> patternList = new ArrayList<Patterns>();
        ExtractHTMLData extractHTMLData = new ExtractHTMLData();
        String partOfPatternId, partOfPatternId1, subURL;
        try {
            codenarcEntity.setProperty("name", "codenarc")
            entryPointHTML.eachLine { line, count ->
                String lineInString = line;
                if (lineInString.contains('''<h3><a href="./''')) {
                    subURL = lineInString.substring(lineInString.lastIndexOf("codenarc"), lineInString.lastIndexOf("html")) + "html"
                    partOfPatternId = subURL.substring("codenarc-rules-".length(), subURL.lastIndexOf(".html"))
                    String subURLHTML = extractHTMLData.executeCurlCommand(BASE_URL + subURL);

                    subURLHTML.eachLine { subURLLine, subURLCount ->
                        String subURLlineInString = subURLLine;
                        if (subURLlineInString.contains("<h3>")) {
                            Patterns pattern = new Patterns();
                            partOfPatternId1 = subURLlineInString.substring(subURLlineInString.lastIndexOf('''<a name="''')
                                    + 9, subURLlineInString.lastIndexOf('''">''') - 5)
                            pattern.setProperty("patternId", partOfPatternId + "_" + partOfPatternId1[0].toLowerCase() +
                                    partOfPatternId1.substring(1).replace("_", ""))
                            pattern.setProperty("level", "Warning")
                            pattern.setProperty("category", "ErrorProne")
                            patternList.add(pattern)
                        }
                    }
                }
            }

            codenarcEntity.setProperty("patterns", patternList)
            return codenarcEntity
        }
        catch (Exception e) {
            throw new Exception("Error in fetching data")
        }
    }

}
/*
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

        }
    }
}*/
/*

static final String BASE_URL = 'http://codenarc.sourceforge.net/';

public CodenarcEntity readFileCodeNarcRules(String entryPointHTML) {
    CodenarcEntity coadnarcEntity = new CodenarcEntity();
    List<Patterns> patternList = new ArrayList<Patterns>();
    ExtractHTMLData extractHTMLData = new ExtractHTMLData();
    String partOfPatternId, partOfPatternId1, subURL;
    try {
        coadnarcEntity.setProperty("name", "codenarc")
        entryPointHTML.eachLine { line, count ->
            String lineInString = line;
            if (lineInString.contains('''<h3><a href="./''')) {
                subURL = lineInString.substring(lineInString.lastIndexOf("codenarc"), lineInString.lastIndexOf("html")) + "html"
                partOfPatternId = subURL.substring("codenarc-rules-".length(), subURL.lastIndexOf(".html"))
                String subURLHTML = extractHTMLData.executeCurlCommand(BASE_URL + subURL);

                subURLHTML.eachLine { subURLLine, subURLCount ->
                    String subURLlineInString = subURLLine;
                    if (subURLlineInString.contains("<h3>")) {
                        Patterns pattern = new Patterns();
                        partOfPatternId1 = subURLlineInString.substring(subURLlineInString.lastIndexOf('''<a name="''')
                                + 9, subURLlineInString.lastIndexOf('''">''') - 5)
                        pattern.setProperty("patternId", partOfPatternId + "_" + partOfPatternId1[0].toLowerCase() +
                                partOfPatternId1.substring(1).replace("_", ""))
                        pattern.setProperty("level", "Warning")
                        pattern.setProperty("category", "ErrorProne")
                        patternList.add(pattern)
                    }
                }
            }
        }

        coadnarcEntity.setProperty("patterns", patternList)
        return coadnarcEntity
    }
    catch (Exception e) {
        throw new Exception("Error in fetching data")
    }
}*/
