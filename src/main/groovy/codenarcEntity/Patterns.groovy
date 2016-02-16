package codenarcEntity

import groovy.transform.CompileStatic


@CompileStatic
class Patterns implements Serializable {
    private String patternId
    private String level
    private String category
    private List<Parameters> parameters

    @Override
    public String toString() {
        return "{" +
                "patternId='" + patternId  +
                ", level='" + level  +
                ", category='" + category  +
                ", parameters=" + parameters +
                '}';
    }
}
