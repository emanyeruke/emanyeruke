package springcommonsmodule.jpa;

public enum Operations {
    GREATER_THAN(">"),
    LESS_THAN("<"),
    EQUALS(":");

    String sign;

    private Operations(String sign) {
        this.sign = sign;
    }
}
