package ca.terrahacks.backend.pollutant;

public class Pollutant {

    private double carbonMonoxide;
    private double nitrogenDioxide;
    private double ozone;
    private double inhalableParticulateMatter;
    private double fineParticulateMatter;
    private double sulfurDioxide;

    public Pollutant(double carbonMonoxide, double nitrogenDioxide, double ozone, double inhalableParticulateMatter, double fineParticulateMatter, double sulfurDioxide) {
        this.carbonMonoxide = carbonMonoxide;
        this.nitrogenDioxide = nitrogenDioxide;
        this.ozone = ozone;
        this.inhalableParticulateMatter = inhalableParticulateMatter;
        this.fineParticulateMatter = fineParticulateMatter;
        this.sulfurDioxide = sulfurDioxide;
    }

    public double getCarbonMonoxide() {
        return carbonMonoxide;
    }

    public void setCarbonMonoxide(double carbonMonoxide) {
        this.carbonMonoxide = carbonMonoxide;
    }

    public double getNitrogenDioxide() {
        return nitrogenDioxide;
    }

    public void setNitrogenDioxide(double nitrogenDioxide) {
        this.nitrogenDioxide = nitrogenDioxide;
    }

    public double getOzone() {
        return ozone;
    }

    public void setOzone(double ozone) {
        this.ozone = ozone;
    }

    public double getInhalableParticulateMatter() {
        return inhalableParticulateMatter;
    }

    public void setInhalableParticulateMatter(double inhalableParticulateMatter) {
        this.inhalableParticulateMatter = inhalableParticulateMatter;
    }

    public double getFineParticulateMatter() {
        return fineParticulateMatter;
    }

    public void setFineParticulateMatter(double fineParticulateMatter) {
        this.fineParticulateMatter = fineParticulateMatter;
    }

    public double getSulfurDioxide() {
        return sulfurDioxide;
    }

    public void setSulfurDioxide(double sulfurDioxide) {
        this.sulfurDioxide = sulfurDioxide;
    }

    @Override
    public String toString() {
        return "Pollutant{" +
                "carbonMonoxide=" + carbonMonoxide +
                ", nitrogenMonoxide=" + nitrogenDioxide +
                ", ozone=" + ozone +
                ", inhalableParticulateMatter=" + inhalableParticulateMatter +
                ", fineParticulateMatter=" + fineParticulateMatter +
                ", sulfurDioxide=" + sulfurDioxide +
                '}';
    }
}
