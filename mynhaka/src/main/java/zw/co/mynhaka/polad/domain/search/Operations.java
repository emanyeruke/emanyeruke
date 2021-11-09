package zw.co.mynhaka.polad.domain.search;

public enum Operations {
    GREATER_THAN(">"),
    LESS_THAN("<"),
    EQUALS(":");

    String sign;

    private Operations(String sign) {
        this.sign = sign;
    }
}
