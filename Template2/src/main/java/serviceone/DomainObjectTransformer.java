package serviceone;

import domain.DomainObject;

public class DomainObjectTransformer {
    public DomainObject transform(InputObject inputObject) {
        guard(inputObject);

        return new DomainObject(
                1,
                transformName(inputObject.getName())
        );
    }

    private void guard(InputObject inputObject) {
        if (inputObject == null || inputObject.getName() == null) {
            throw new IllegalArgumentException();
        }
    }

    private String transformName(String name) {
        String result = name.replaceAll(" ", "").toLowerCase();
        return result.substring(0, 1).toUpperCase() +
                result.substring(1);
    }
}
