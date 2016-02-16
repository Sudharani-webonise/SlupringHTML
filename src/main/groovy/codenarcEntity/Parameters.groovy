package codenarcEntity

import org.codehaus.jackson.annotate.JsonProperty

class Parameters implements Serializable {

    private String name
    private String defaultValue


    String getDefaultValue() {
        return defaultValue
    }

    @JsonProperty("default")
    void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }
}
