package tafat.engine;

import tafat.framework.SGIFramework;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static tafat.sgi.exception.ExceptionHandler.getSafe;


public class SimulationViewer extends SGIFramework {

    private final Control control;
    private final Scene scene;

    public SimulationViewer(Control control, Scene scene) {
        super("EnergyDemand5", control.initialDate(), control.finalDate());
        this.control = control;
        this.scene = scene;
        control.pause();
    }

    @Override
    public Collection<String> roots() {
        return scene.collect(ModelObject.class, false).stream().map(m -> m.id).collect(Collectors.<String>toList());
    }

    @Override
    public Collection<String> childrenOf(String objectId) {
        return scene.search(objectId).collect(ModelObject.class, false).stream().filter(m -> !(m instanceof BehaviorContainer)).map(m -> m.id).collect(Collectors.<String>toList());
    }

    @Override
    public Collection<String> attributesOf(String objectId) {
        return Arrays.asList(scene.search(objectId).getClass().getFields()).stream().filter(f -> f.getType().isPrimitive()).map(Field::getName).collect(Collectors.<String>toList());
    }

    @Override
    public String valueGet(String attributeId) {
        ModelObject object = scene.search(attributeId.substring(0, attributeId.lastIndexOf("/")));
        try {
            return object.getClass().getField(attributeId.substring(attributeId.lastIndexOf("/") + 1)).get(object) + "";
        } catch (IllegalAccessException | NoSuchFieldException e) {
            return attributeId + " not found or type is not valid";
        }

    }

    @Override
    public boolean valueSet(String attributeId, String value) {
        ModelObject object = scene.search(attributeId.substring(0, attributeId.lastIndexOf("/")));
        Field field;
        try {
            field = object.getClass().getField(attributeId.substring(attributeId.lastIndexOf("/") + 1));
        } catch (NoSuchFieldException e) {
            return false;
        }
        if(!setValue(Double.parseDouble(value), object, field))
            return setValue(Integer.valueOf(value), object, field);
        return true;
    }

    private boolean setValue(Object value, ModelObject object, Field field) {
        return getSafe(()-> {
            field.set(object, value);
            return true;
        }, ()->false);
    }

    @Override
    public boolean play() {
        control.play();
        return true;
    }

    @Override
    public boolean pause() {
        control.pause();
        return true;
    }

    @Override
    public boolean reset() {
        control.reset();
        return true;
    }
}
