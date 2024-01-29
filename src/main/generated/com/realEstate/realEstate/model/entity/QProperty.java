package com.realEstate.realEstate.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProperty is a Querydsl query type for Property
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProperty extends EntityPathBase<Property> {

    private static final long serialVersionUID = 306102382L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProperty property = new QProperty("property");

    public final com.realEstate.realEstate.model.QBaseEntity _super = new com.realEstate.realEstate.model.QBaseEntity(this);

    public final QAddress address;

    public final QPropertyAmenities amenities;

    public final NumberPath<Integer> area = createNumber("area", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> deposit = createNumber("deposit", Integer.class);

    public final QDescription description;

    public final StringPath direction = createString("direction");

    public final NumberPath<Integer> floor = createNumber("floor", Integer.class);

    public final BooleanPath hasElevator = createBoolean("hasElevator");

    public final NumberPath<Integer> managementFee = createNumber("managementFee", Integer.class);

    public final NumberPath<Integer> monthlyRent = createNumber("monthlyRent", Integer.class);

    public final DatePath<java.time.LocalDate> moveInDate = createDate("moveInDate", java.time.LocalDate.class);

    public final QPropertyOption option;

    public final BooleanPath parkingAvailable = createBoolean("parkingAvailable");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Long> propertyId = createNumber("propertyId", Long.class);

    public final ListPath<PropertyImage, QPropertyImage> propertyImageList = this.<PropertyImage, QPropertyImage>createList("propertyImageList", PropertyImage.class, QPropertyImage.class, PathInits.DIRECT2);

    public final EnumPath<com.realEstate.realEstate.model.constant.Structure> structure = createEnum("structure", com.realEstate.realEstate.model.constant.Structure.class);

    public final EnumPath<com.realEstate.realEstate.model.constant.CType> transactionType = createEnum("transactionType", com.realEstate.realEstate.model.constant.CType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final QUser user;

    public final ListPath<Wish, QWish> wishes = this.<Wish, QWish>createList("wishes", Wish.class, QWish.class, PathInits.DIRECT2);

    public QProperty(String variable) {
        this(Property.class, forVariable(variable), INITS);
    }

    public QProperty(Path<? extends Property> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProperty(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProperty(PathMetadata metadata, PathInits inits) {
        this(Property.class, metadata, inits);
    }

    public QProperty(Class<? extends Property> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new QAddress(forProperty("address")) : null;
        this.amenities = inits.isInitialized("amenities") ? new QPropertyAmenities(forProperty("amenities"), inits.get("amenities")) : null;
        this.description = inits.isInitialized("description") ? new QDescription(forProperty("description"), inits.get("description")) : null;
        this.option = inits.isInitialized("option") ? new QPropertyOption(forProperty("option"), inits.get("option")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

