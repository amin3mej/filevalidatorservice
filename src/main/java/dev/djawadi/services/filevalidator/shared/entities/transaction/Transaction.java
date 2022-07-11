package dev.djawadi.services.filevalidator.shared.entities.transaction;

import com.opencsv.bean.CsvBindByName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;


public class Transaction {
    @CsvBindByName(column = "Reference")
    private Long reference;
    @CsvBindByName(column = "AccountNumber")
    private String accountNumber;
    @CsvBindByName(column = "Description")
    private String description;
    @CsvBindByName(column = "Start Balance")
    private BigDecimal startBalance;
    @CsvBindByName(column = "Mutation")
    private BigDecimal mutation;
    @CsvBindByName(column = "End Balance")
    private BigDecimal endBalance;

    public Long getReference() {
        return reference;
    }

    public void setReference(Long reference) {
        this.reference = reference;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getStartBalance() {
        return startBalance;
    }

    public void setStartBalance(BigDecimal startBalance) {
        this.startBalance = startBalance;
    }

    public BigDecimal getMutation() {
        return mutation;
    }

    public void setMutation(BigDecimal mutation) {
        this.mutation = mutation;
    }

    public BigDecimal getEndBalance() {
        return endBalance;
    }

    public void setEndBalance(BigDecimal endBalance) {
        this.endBalance = endBalance;
    }
}
