package com.pragya.erp.model;

import jakarta.persistence.*;

@Entity
public class FeeItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category; // tuition, lab, library, hostel, misc
    private double amount;
    private String semester;

    public FeeItem() {}
    public FeeItem(String name, String category, double amount, String semester) {
        this.name = name; this.category = category; this.amount = amount; this.semester = semester;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }
}
