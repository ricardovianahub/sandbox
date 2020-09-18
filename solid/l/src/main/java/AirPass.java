public class AirPass extends PlaneTicket {
    private String company;

    public AirPass(String origin, String destination,
                   String duration, double cost, String company) {
        super(origin, destination, duration, cost);
        this.company = company;
    }

    public String getCompany() {
        return company;
    }
}
