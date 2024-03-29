package com.realEstate.realEstate.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Setter
@Table(name = "property_option")
public class PropertyOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private Long optionId;


    private boolean sink;
    @Column(name = "air_conditioner")
    private boolean airConditioner;
    @Column(name = "shoe_rack")
    private boolean shoeRack;
    @Column(name = "washing_machine")
    private boolean washingMachine;
    private boolean refrigerator;
    private boolean wardrobe;
    @Column(name = "gas_range")
    private boolean gasRange;
    private boolean induction;

    private boolean bed;
    private boolean desk;
    private boolean microwave;
    private boolean bookshelf;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "property_id")
    private Property property;

    public static PropertyOption of(boolean sink, boolean airConditioner, boolean shoeRack, boolean washingMachine, boolean refrigerator, boolean wardrobe, boolean gasRange, boolean induction, boolean bed, boolean desk, boolean microwave, boolean bookshelf, Property property) {
        PropertyOption option = new PropertyOption();
        option.setSink(sink);
        option.setAirConditioner(airConditioner);
        option.setShoeRack(shoeRack);
        option.setWashingMachine(washingMachine);
        option.setRefrigerator(refrigerator);
        option.setWardrobe(wardrobe);
        option.setGasRange(gasRange);
        option.setInduction(induction);
        option.setBed(bed);
        option.setDesk(desk);
        option.setMicrowave(microwave);
        option.setBookshelf(bookshelf);
        option.setProperty(property);

        return  option;

    }
}
