package codnackimpl

class ExtractHTMLData {
    public String executeCurlCommand(String URL) {
        try {
            def url = "curl " + URL
            def proc = url.execute();
            def HTMLData = new StringBuffer();
            proc.waitForProcessOutput(HTMLData, System.err)
            return HTMLData.toString();
        }
        catch (Exception e) {
            throw new Exception("error in reading HTML")
        }
    }
}
