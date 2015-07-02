package magritte.dsl;

public class TafatDsl extends magritte.schema.Box.Dsl {
    public static final magritte.schema.Box box = new TafatDsl();

    protected magritte.schema.Box[] includes() {
        return new magritte.schema.Box[]{
            Magritte.ontology.TafatMain.box
        };
    }
}