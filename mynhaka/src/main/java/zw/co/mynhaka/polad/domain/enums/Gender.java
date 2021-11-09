package zw.co.mynhaka.polad.domain.enums;

/*
 *author Venon Mapfunde
 */
public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE");

    public final String label;

    private Gender(String label){
        this.label = label;
    }
}
