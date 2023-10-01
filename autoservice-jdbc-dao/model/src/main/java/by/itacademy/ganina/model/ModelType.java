package by.itacademy.ganina.model;

public enum ModelType implements IdField {

    AUDI_A6_2006(1, "AUDI A6 2006"),
    BMW_X6_2015(2, "BMW X6 2015"),
    HONDA_CIVIC_2009(3, "HONDA CIVIC 2009"),
    LEXUS_RX_300_2016(4, "LEXUS RX 300 2016"),
    MERCEDES_SPRINTER_2000(5, "MERCEDES SPRINTER 2000"),
    IVECO_DAILY_2008(6, "IVECO DAILY 2008"),
    IVECO_EUROCARGO_1993(7, "IVECO EUROCARGO 1993"),
    MAN_TGM_2019(8, "MAN TGM 2019"),
    VW_TRANSPORTER_T1_1952(9, "VW TRANSPORTER T1 1952");

    private final Integer id;
    private final String modelName;

    ModelType(final Integer id, final String modelName) {
        this.id = id;
        this.modelName = modelName;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public String getModelName() {
        return modelName;
    }

    @Override
    public String toString() {
        return String.join(" | ",
                id.toString(),
                modelName
        );
    }
}
