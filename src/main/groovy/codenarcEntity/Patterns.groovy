package codenarcEntity


class Patterns  implements Serializable  {
    private String patternId
    private String level
    private String category
    private List<Parameters> parameters

    String getPatternId() {
        return patternId
    }

    void setPatternId(String patternId) {
        this.patternId = patternId
    }

    String getLevel() {
        return level
    }

    void setLevel(String level) {
        this.level = level
    }

    String getCategory() {
        return category
    }

    void setCategory(String category) {
        this.category = category
    }

    List<Parameters> getParameters() {
        return parameters
    }

    void setParameters(List<Parameters> parameters) {
        this.parameters = parameters
    }
}
