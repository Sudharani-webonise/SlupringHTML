package codenarcEntity

import groovy.transform.CompileStatic

@CompileStatic
class Parameters implements Serializable {
    private String name
    private String description
    private String defaultValue


    @Override
    public String toString() {
        return "{" +
                "name='" + name + ", default='" + defaultValue  +
                '}';
    }
}
