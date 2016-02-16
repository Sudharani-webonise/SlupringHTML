package codenarcEntity


class CodenarcEntity {
    private String name;
    private List<Patterns> patterns;

    @Override
    public String toString() {
        return "CodenarcEntity{" +
                "name='" + name + '\'' +
                ", patterns=" + patterns +
                '}';
    }
}
