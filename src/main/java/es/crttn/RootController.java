package es.crttn;

import com.fasterxml.jackson.databind.JsonNode;
import es.crttn.models.Hipoteca;
import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.util.converter.NumberStringConverter;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RootController implements Initializable {

    @FXML
    private TextField anyoTextField;

    @FXML
    private TextField capitalTextField;

    @FXML
    private TextField interesTextField;

    @FXML
    private TableView<?> hipotecaTableView;

    @FXML
    private TableColumn<Hipoteca, Double> capAmortizadoColumn;

    @FXML
    private TableColumn<Hipoteca, Double> capInicialColumn;

    @FXML
    private TableColumn<Hipoteca, Double> capPendienteColun;

    @FXML
    private TableColumn<Hipoteca, Integer> anyoColumn;

    @FXML
    private TableColumn<Hipoteca, Double> cuotaColumn;

    @FXML
    private TableColumn<Hipoteca, Double> interesesColumn;

    @FXML
    private TableColumn<Hipoteca, Integer> mesColumn;

    @FXML
    private TableColumn<Hipoteca, Integer> numeroColumn;

    @FXML
    private BorderPane root;

    private final String url = "http://localhost:3000/hipoteca?";

    private IntegerProperty capitalProperty = new SimpleIntegerProperty();
    private IntegerProperty interesProperty = new SimpleIntegerProperty();
    private IntegerProperty anyoProperty = new SimpleIntegerProperty();

    private ObservableList listaHipotecas;


    public RootController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RootView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        capitalTextField.textProperty().bindBidirectional(capitalProperty, new NumberStringConverter());
        interesTextField.textProperty().bindBidirectional(interesProperty, new NumberStringConverter());
        anyoTextField.textProperty().bindBidirectional(anyoProperty, new NumberStringConverter());

        numeroColumn.setCellValueFactory(cellData -> cellData.getValue().numeroProperty().asObject());
        anyoColumn.setCellValueFactory(cellData -> cellData.getValue().anyoProperty().asObject());
        mesColumn.setCellValueFactory(cellData -> cellData.getValue().mesProperty().asObject());
        capInicialColumn.setCellValueFactory(cellData -> cellData.getValue().capitalInicialProperty().asObject());
        interesesColumn.setCellValueFactory(cellData -> cellData.getValue().interesesProperty().asObject());
        capAmortizadoColumn.setCellValueFactory(cellData -> cellData.getValue().capitalAmortizadoProperty().asObject());
        cuotaColumn.setCellValueFactory(cellData -> cellData.getValue().cuotaProperty().asObject());
        capPendienteColun.setCellValueFactory(cellData -> cellData.getValue().capitalPendienteProperty().asObject());

        listaHipotecas = FXCollections.observableArrayList();
        hipotecaTableView.setItems(listaHipotecas);

    }

    public BorderPane getRoot() {
        return root;
    }

    @FXML
    void onCalculateAction(ActionEvent event) {

        if (capitalProperty.getValue() != 0 & interesProperty.getValue() != 0 & anyoProperty.getValue() != 0) {
            try {
                URL urlObj = new URL(url + "capital=" + capitalProperty.getValue() + "&interes=" + interesProperty.getValue() + "&plazo=" + anyoProperty.getValue());
                HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Procesar JSON manualmente
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(response.toString());

                JsonNode cuotasNode = root.get("cuotas");
                for (JsonNode cuotaNode : cuotasNode) {
                    Hipoteca hipoteca1 = mapper.treeToValue(cuotaNode, Hipoteca.class);
                    listaHipotecas.add(hipoteca1);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
