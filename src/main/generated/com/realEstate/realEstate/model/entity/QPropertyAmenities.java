package com.realEstate.realEstate.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPropertyAmenities is a Querydsl query type for PropertyAmenities
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPropertyAmenities extends EntityPathBase<PropertyAmenities> {

    private static final long serialVersionUID = 548442185L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPropertyAmenities propertyAmenities = new QPropertyAmenities("propertyAmenities");

    public final com.realEstate.realEstate.model.QBaseEntity _super = new com.realEstate.realEstate.model.QBaseEntity(this);

    public final StringPath bank = createString("bank");

    public final StringPath bus = createString("bus");

    public final StringPath cafe = createString("cafe");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath hospital = createString("hospital");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath laundry = createString("laundry");

    public final StringPath mart = createString("mart");

    public final QProperty property;

    public final StringPath subway = createString("subway");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QPropertyAmenities(String variable) {
        this(PropertyAmenities.class, forVariable(variable), INITS);
    }

    public QPropertyAmenities(Path<? extends PropertyAmenities> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPropertyAmenities(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPropertyAmenities(PathMetadata metadata, PathInits inits) {
        this(PropertyAmenities.class, metadata, inits);
    }

    public QPropertyAmenities(Class<? extends PropertyAmenities> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.property = inits.isInitialized("property") ? new QProperty(forProperty("property"), inits.get("property")) : null;
    }

}

