package app;

public class CityState2 {

    private Properties1 properties = new Properties1();

    public Properties1 getProperties() {
        return properties;
    }

    public void setProperties(Properties1 properties) {
        this.properties = properties;
    }

    public static class Properties1 {
        private RelativeLocation relativeLocation = new RelativeLocation();

        public RelativeLocation getRelativeLocation() {
            return relativeLocation;
        }

        public void setRelativeLocation(RelativeLocation relativeLocation) {
            this.relativeLocation = relativeLocation;
        }
    }

    public static class RelativeLocation {
        private Properties2 properties = new Properties2();

        public Properties2 getProperties() {
            return properties;
        }

        public void setProperties(Properties2 properties) {
            this.properties = properties;
        }
    }

    public static class Properties2 {
        private String city;
        private String state;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}

