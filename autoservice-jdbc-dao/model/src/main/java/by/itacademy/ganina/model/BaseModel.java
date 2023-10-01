package by.itacademy.ganina.model;

public class BaseModel implements IdField{

    private final Integer id;

    public BaseModel(final Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }
}
