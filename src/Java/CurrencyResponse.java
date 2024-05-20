package Java;

public class CurrencyResponse {
    private String base_code;
    private ConversionRates conversion_rates;
    // Getters y setters
    public String getBase_code() {
        return base_code;
    }

    public void setBase_code(String base_code) {
        this.base_code = base_code;
    }

    public ConversionRates getConversion_rates() {
        return conversion_rates;
    }

    public void setConversion_rates(ConversionRates conversion_rates) {
        this.conversion_rates = conversion_rates;
    }
}
