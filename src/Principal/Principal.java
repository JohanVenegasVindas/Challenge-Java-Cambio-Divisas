package Principal;
import Java.CurrencyConverter;
import Java.CurrencyResponse;
import com.google.gson.Gson;

import javax.swing.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

public class Principal {



        public static void main(String[] args) {
            try {

                String message= """
                          Bienvenidos al Conversor Divisas.
                            ********************************
                    La moneda por defecto es de CostaRica:CRC
                        -----------------------------------------------
                        Puedes cambiarla entre las distintas
                            opciones que te ofrecemos.
                            ********************************
                            Hecho por Johan Venegas Vindas 2024""";
                JOptionPane.showMessageDialog(null, message, "Bienvenida", JOptionPane.INFORMATION_MESSAGE);
                //Se crea el menu del panel 1 con las divisas
                String[] monedas = {"CRC","USD", "EUR", "GBP", "JPY", "KRW"};
                JComboBox<String> menu1 = new JComboBox<>(monedas);
                //Se crea el menu del panel 2 con las divisas a convertir
                String[] monedas2 = {"USD","EUR", "GBP", "JPY", "KRW", "CRC"};
                JComboBox<String> menu2 = new JComboBox<>(monedas2);

                //Se agrega la Clase
                JPanel panel1 = new JPanel();
                //Se crea el panel 1 para el menu de divisas
                panel1.add(new JLabel("Selecciona una divisa:"));
                panel1.add(menu1);
                //Se agrega la Clase para el panel 2
                JPanel panel2 = new JPanel();
                //Se crea el panel 2 para el menu de divisas a convertir
                panel2.add(new JLabel("Selecciona una divisa a covertir:"));
                panel2.add(menu2);

                //Se crea el panel principal.
                JPanel panelPrincipal = new JPanel();
                panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
                //Se agregan los menus al panel pricipal.
                panelPrincipal.add(panel1);
                panelPrincipal.add(panel2);

                // Opciones de botones para el Panel de dialogo.
                String[] opciones = {"Aceptar", "Cancelar"};
                // Mostrar el cuadro de diálogo
                int choice = JOptionPane.showOptionDialog(
                        null, // Componente padre (null para centrar en la pantalla)
                        panelPrincipal, // Panel principal con menús desplegables
                        "Conversor de Divisas", // Título
                        JOptionPane.DEFAULT_OPTION, // Tipo de opción (uso por defecto)
                        JOptionPane.INFORMATION_MESSAGE, // Tipo de mensaje
                        null, // Icono (null para usar el icono por defecto)
                        opciones, // Opciones de botón
                        opciones[0] // Opción predeterminada
                );
                //Se cambia el raio de conversion con el menu 1
                String conversionRates = (String) menu1.getSelectedItem();
                String direccionApi = "https://v6.exchangerate-api.com/v6/7449d4f8cd7850ced60fab0e/latest/" + conversionRates;
                //Se agrega la direccion de la api
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(direccionApi))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
                //Se extrae la informacion de la api de divisas.
                String json = response.body();
                Gson gson = new Gson();
                CurrencyResponse respuestaApi = gson.fromJson(json, CurrencyResponse.class);


                String valor = JOptionPane.showInputDialog("Ingrese la cantidad en:("+ menu1.getSelectedItem()+")");
                double monto = Double.parseDouble(valor);
                //Se realiza las operaciones de la conversion
                CurrencyConverter.convertAndDisplay(monto,respuestaApi, (String) menu2.getSelectedItem());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
