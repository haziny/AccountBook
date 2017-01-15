package com.example.haziny.accountbook;

public class IncomeExpense {
    // PK
    private int _id;
    private String name;
    private Integer price;
    private Integer iconindex;
    private Integer classification;
    public int get_id() { return _id; }
    public String getName() { return name; }
    public Integer getPrice() { return price; }
    public Integer getIconindex() { return iconindex; }
    public Integer getClassification() { return classification; }

    public void set_id(int _id) { this._id = _id; }
    public void setName(String name) { this.name = name; }
    public void setAge(Integer price) { this.price = price; }
    public void setPhone(Integer iconindex) { this.iconindex = iconindex; }
    public void setClassification(Integer classification) { this.classification = classification; }
}