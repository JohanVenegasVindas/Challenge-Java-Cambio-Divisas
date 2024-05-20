package Java;

import javax.swing.*;

public class CurrencyConverter {

    public static void convertAndDisplay(double monto, CurrencyResponse response, String currency) {
        ConversionRates rates = response.getConversion_rates();
        double montoConvertido = 0.0;
        switch (currency) {
            case "CRC":
                montoConvertido = monto * rates.getCRC();
                break;
            case "USD":
                montoConvertido = monto * rates.getUSD();
                break;
            case "EUR":
                montoConvertido = monto * rates.getEUR();
                break;
            case "GBP":
                montoConvertido = monto * rates.getGBP();
                break;
            case "JPY":
                montoConvertido = monto * rates.getJPY();
                break;
            case "KRW":
                montoConvertido = monto * rates.getKRW();
                break;
        }
        JOptionPane.showMessageDialog(null,
                "Cantidad en CRC: "+ monto + "\n" +
                        "Cantidad en: " + currency + ": " + montoConvertido,
                "Resultado de Conversión",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
