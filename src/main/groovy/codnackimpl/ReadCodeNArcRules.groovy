package codnackimpl

import codenarcEntity.CodenarcEntity
import codenarcEntity.Parameters
import codenarcEntity.Patterns
import groovy.transform.CompileStatic
import groovy.util.logging.Log

@Log
@CompileStatic
class ReadCodeNArcRules {
    static final String BASE_URL = 'http://codenarc.sourceforge.net/';
    public CodenarcEntity readFileCodenarcRules(String entryPointHTML) {
        CodenarcEntity codenarcEntity = new CodenarcEntity();
        List<Patterns> patternList = new ArrayList<Patterns>();
        List<Parameters> parametersList
        ExtractHTMLData extractHTMLData = new ExtractHTMLData();
        String subURL;

        Patterns singlePattern
        Parameters singleParameter

        try {
            codenarcEntity.setProperty("name", "codenarc")
            entryPointHTML.eachLine { line, count ->
                String lineInString = line;
                int tableCount = 0;
                if (lineInString.contains('''<h3><a href="./''')) {
                    subURL = lineInString.substring(lineInString.lastIndexOf("codenarc"), lineInString.lastIndexOf("html")) + "html"
                    String partOfPatternId = subURL.substring("codenarc-rules-".length(), subURL.lastIndexOf(".html"))
                    String subURLHTML = extractHTMLData.executeCurlCommand(BASE_URL + subURL);

                    subURLHTML.eachLine { subURLLine, subURLCount ->
                        if (lineInString.contains("<h3>") || lineInString.contains('''<td align="left">''')) {
                            String subURLlineInString = subURLLine;

                            if (subURLlineInString.contains("<h3>")) {
                                //check if single pattern already initialized or not
                                if(singlePattern!= null){
                                    patternList.add(singlePattern);
                                }
                                singlePattern = new Patterns();
                                String partOfPatternId1 = subURLlineInString.substring(subURLlineInString.lastIndexOf('''<a name="''')
                                        + 9, subURLlineInString.lastIndexOf('''">''') - 5)
                                singlePattern.setProperty("patternId", partOfPatternId + "_" + partOfPatternId1[0].toLowerCase() +
                                        partOfPatternId1.substring(1).replace("_", ""))
                                singlePattern.setProperty("level", "Warning")
                                singlePattern.setProperty("category", "ErrorProne")
                                parametersList = new ArrayList<Parameters>();
                            } else if (subURLlineInString.contains('''<td align="left"><b>''')) {
                                //table header so skipping line read
                            } else if (subURLlineInString.contains('''<td align="left">''')) {
                                String tdVal
                                switch (tableCount) {
                                    case 0:
                                        singleParameter = new Parameters();
                                        tdVal = subURLlineInString.substring(subURLlineInString.lastIndexOf('''<td align="left">''') + 17, subURLlineInString.lastIndexOf("</td>"))
                                        singleParameter.setProperty("name", tdVal);
                                        tableCount++
                                        break;
                                    case 1: tableCount++
                                        break;
                                    case 2: if (subURLlineInString.contains("<i>")) {
                                            tdVal = subURLlineInString.substring(subURLlineInString.lastIndexOf('''<td align="left"><i>''') + 20, subURLlineInString.lastIndexOf("</i>"))
                                        } else if (subURLlineInString.contains("<tt>")) {
                                            tdVal = subURLlineInString.substring(subURLlineInString.lastIndexOf('''<td align="left"><tt>''') + 21, subURLlineInString.lastIndexOf("</tt>"))
                                        } else {
                                            tdVal = subURLlineInString.substring(subURLlineInString.lastIndexOf('''<td align="left">''') + 17, subURLlineInString.lastIndexOf("</td>"))
                                        }
                                        singleParameter.setProperty("defaultValue", tdVal)
                                        parametersList.add(singleParameter)

                                        if (subURLlineInString.contains("</table>")) {
                                            singlePattern.setProperty("parameters",parametersList)
                                            patternList.add(singlePattern)
                                            singlePattern=null
                                        }

                                        tableCount = 0
                                        break;
                                    default:
                                        tableCount = 0
                                }
                            }
                        }
                    }
                }
            }

            codenarcEntity.setProperty("patterns", patternList)
            return codenarcEntity
        }
        catch (Exception e) {
            throw new Exception("Error in fetching data" + e)
        }
    }

}
