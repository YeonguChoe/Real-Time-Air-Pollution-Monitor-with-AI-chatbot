package ca.terrahacks.backend.pollutant;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Pollutant {
    private String type;
    private double amount;
    private LocalDateTime time;

    public Pollutant(String type, double amount, LocalDateTime time) {
        this.type = type;
        this.amount = amount;
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Pollutant{" +
                "type='" + type + '\'' +
                ", amount=" + amount +
                ", time=" + time +
                '}';
    }
}
