package codenarcEntity

import com.fasterxml.jackson.annotation.JsonInclude


@JsonInclude(JsonInclude.Include.NON_NULL)
class CodenarcEntity implements Serializable{
    private String name;
    private List<Patterns> patterns;

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    List<Patterns> getPatterns() {
        return patterns
    }

    void setPatterns(List<Patterns> patterns) {
        this.patterns = patterns
    }
}
