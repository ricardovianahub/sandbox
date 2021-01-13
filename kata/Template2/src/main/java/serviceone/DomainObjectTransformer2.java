package serviceone;

import domain.DomainObject;

public class DomainObjectTransformer2 {

    public DomainObject transform(InputObject inputObject) {
        guard(inputObject);
        return new DomainObject(
                transformId(inputObject.getId()),
                transformName(inputObject.getName())
        );
    }

    private void guard(InputObject inputObject) {
        if (inputObject == null || inputObject.getId() == null || inputObject.getName() == null) {
            throw new IllegalArgumentException();
        }
    }

    private int transformId(String id) {
        return Integer.parseInt(id.trim());
    }

    private String transformName(String name) {
        String result;
        String allLower = name.replaceAll(" ", "").toLowerCase();
        result = allLower.substring(0, 1).toUpperCase() + allLower.substring(1);
        return result;
    }

}
