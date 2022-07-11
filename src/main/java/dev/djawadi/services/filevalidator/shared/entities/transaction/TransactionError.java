package dev.djawadi.services.filevalidator.shared.entities.transaction;

public class TransactionError {
    private Long reference;
    private String description;

    public Long getReference() {
        return reference;
    }

    public void setReference(Long reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TransactionError{" +
                "reference='" + reference + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
