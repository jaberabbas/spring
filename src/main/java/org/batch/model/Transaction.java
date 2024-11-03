package org.batch.model;
import java.time.LocalDateTime;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Setter;
import org.batch.service.adapter.LocalDateTimeAdapter;

@Setter
@SuppressWarnings("restriction")
@XmlRootElement(name = "transactionRecord")
public class Transaction {
    private String username;
    private int userId;
    private int age;
    private String postCode;
    private LocalDateTime transactionDate;
    private double amount;

    /* getters and setters for the attributes */

    public String getUsername() {
        return username;
    }

    public int getUserId() {
        return userId;
    }

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    public int getAge() {
        return age;
    }

    public String getPostCode() {
        return postCode;
    }

    @Override
    public String toString() {
        return "Transaction [username=" + username + ", userId=" + userId + ", age=" + age + ", postCode=" + postCode + ", transactionDate=" + transactionDate + ", amount=" + amount + "]";
    }

}
