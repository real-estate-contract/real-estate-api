package com.realEstate.realEstate.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPropertyOption is a Querydsl query type for PropertyOption
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPropertyOption extends EntityPathBase<PropertyOption> {

    private static final long serialVersionUID = -531912061L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPropertyOption propertyOption = new QPropertyOption("propertyOption");

    public final BooleanPath airConditioner = createBoolean("airConditioner");

    public final BooleanPath bed = createBoolean("bed");

    public final BooleanPath bookshelf = createBoolean("bookshelf");

    public final BooleanPath desk = createBoolean("desk");

    public final BooleanPath gasRange = createBoolean("gasRange");

    public final BooleanPath induction = createBoolean("induction");

    public final BooleanPath microwave = createBoolean("microwave");

    public final NumberPath<Long> optionId = createNumber("optionId", Long.class);

    public final QProperty property;

    public final BooleanPath refrigerator = createBoolean("refrigerator");

    public final BooleanPath shoeRack = createBoolean("shoeRack");

    public final BooleanPath sink = createBoolean("sink");

    public final BooleanPath wardrobe = createBoolean("wardrobe");

    public final BooleanPath washingMachine = createBoolean("washingMachine");

    public QPropertyOption(String variable) {
        this(PropertyOption.class, forVariable(variable), INITS);
    }

    public QPropertyOption(Path<? extends PropertyOption> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPropertyOption(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPropertyOption(PathMetadata metadata, PathInits inits) {
        this(PropertyOption.class, metadata, inits);
    }

    public QPropertyOption(Class<? extends PropertyOption> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.property = inits.isInitialized("property") ? new QProperty(forProperty("property"), inits.get("property")) : null;
    }

}

