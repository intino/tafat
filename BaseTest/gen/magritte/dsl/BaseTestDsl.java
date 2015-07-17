package magritte.dsl;

public class BaseTestDsl extends magritte.schema.Box.Dsl {
    public static final magritte.schema.Box box = new BaseTestDsl();

    protected magritte.schema.Box[] includes() {
        return new magritte.schema.Box[]{
            magritte.ontology.BaseTestStateChart.box
        };
    }
}