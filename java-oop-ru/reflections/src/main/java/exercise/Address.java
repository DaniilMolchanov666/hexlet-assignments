package exercise;

class Address {

    @NotNull
    private String country;

    @MinLength
    @NotNull
    private String city;


    @MinLength
    @NotNull
    private String street;

    @NotNull
    private String houseNumber;

    private String flatNumber;

    Address(String country, String city, String street, String houseNumber, String flatNumber) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
    }
}
