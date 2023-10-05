package by.itacademy.ganina.model;

public class BaseModel implements IdField {

    @ColumnName(name = "id")
    private final Integer id;

    public BaseModel(final Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }
}
