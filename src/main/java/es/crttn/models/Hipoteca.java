package es.crttn.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Hipoteca {
    private int numero;
    private int anyo;
    private int mes;
    private double capitalInicial;
    private double intereses;
    private double capitalAmortizado;
    private double cuota;
    private double capitalPendiente;

    @JsonIgnore
    private final IntegerProperty numeroProperty = new SimpleIntegerProperty();
    @JsonIgnore
    private final IntegerProperty anyoProperty = new SimpleIntegerProperty();
    @JsonIgnore
    private final IntegerProperty mesProperty = new SimpleIntegerProperty();
    @JsonIgnore
    private final DoubleProperty capitalInicialProperty = new SimpleDoubleProperty();
    @JsonIgnore
    private final DoubleProperty interesesProperty = new SimpleDoubleProperty();
    @JsonIgnore
    private final DoubleProperty capitalAmortizadoProperty = new SimpleDoubleProperty();
    @JsonIgnore
    private final DoubleProperty cuotaProperty = new SimpleDoubleProperty();
    @JsonIgnore
    private final DoubleProperty capitalPendienteProperty = new SimpleDoubleProperty();

    // Constructor vacío
    @JsonIgnoreProperties(ignoreUnknown = true)
    public Hipoteca() {}

    // Constructor completo
    public Hipoteca(int numero, int anyo, int mes, double capitalInicial, double intereses, double capitalAmortizado, double cuota, double capitalPendiente) {
        setNumero(numero);
        setAnyo(anyo);
        setMes(mes);
        setCapitalInicial(capitalInicial);
        setIntereses(intereses);
        setCapitalAmortizado(capitalAmortizado);
        setCuota(cuota);
        setCapitalPendiente(capitalPendiente);
    }

    // Getters y setters para los campos simples
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
        this.numeroProperty.set(numero);
    }

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
        this.anyoProperty.set(anyo);
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
        this.mesProperty.set(mes);
    }

    public double getCapitalInicial() {
        return capitalInicial;
    }

    public void setCapitalInicial(double capitalInicial) {
        this.capitalInicial = capitalInicial;
        this.capitalInicialProperty.set(capitalInicial);
    }

    public double getIntereses() {
        return intereses;
    }

    public void setIntereses(double intereses) {
        this.intereses = intereses;
        this.interesesProperty.set(intereses);
    }

    public double getCapitalAmortizado() {
        return capitalAmortizado;
    }

    public void setCapitalAmortizado(double capitalAmortizado) {
        this.capitalAmortizado = capitalAmortizado;
        this.capitalAmortizadoProperty.set(capitalAmortizado);
    }

    public double getCuota() {
        return cuota;
    }

    public void setCuota(double cuota) {
        this.cuota = cuota;
        this.cuotaProperty.set(cuota);
    }

    public double getCapitalPendiente() {
        return capitalPendiente;
    }

    public void setCapitalPendiente(double capitalPendiente) {
        this.capitalPendiente = capitalPendiente;
        this.capitalPendienteProperty.set(capitalPendiente);
    }

    // Propiedades ignoradas por Jackson
    @JsonIgnore
    public IntegerProperty numeroProperty() {
        return numeroProperty;
    }

    @JsonIgnore
    public IntegerProperty anyoProperty() {
        return anyoProperty;
    }

    @JsonIgnore
    public IntegerProperty mesProperty() {
        return mesProperty;
    }

    @JsonIgnore
    public DoubleProperty capitalInicialProperty() {
        return capitalInicialProperty;
    }

    @JsonIgnore
    public DoubleProperty interesesProperty() {
        return interesesProperty;
    }

    @JsonIgnore
    public DoubleProperty capitalAmortizadoProperty() {
        return capitalAmortizadoProperty;
    }

    @JsonIgnore
    public DoubleProperty cuotaProperty() {
        return cuotaProperty;
    }

    @JsonIgnore
    public DoubleProperty capitalPendienteProperty() {
        return capitalPendienteProperty;
    }
}
