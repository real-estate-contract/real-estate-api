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

    public final NumberPath<Integer> area1 = createNumber("area1", Integer.class);

    public final NumberPath<Integer> area2 = createNumber("area2", Integer.class);

    public final NumberPath<Integer> bathroomCount = createNumber("bathroomCount", Integer.class);

    public final StringPath contractType = createString("contractType");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final NumberPath<Integer> floor = createNumber("floor", Integer.class);

    public final BooleanPath loanFund = createBoolean("loanFund");

    public final BooleanPath management = createBoolean("management");

    public final NumberPath<Integer> managementFee = createNumber("managementFee", Integer.class);

    public final BooleanPath negotiationFee = createBoolean("negotiationFee");

    public final BooleanPath parkingAvailable = createBoolean("parkingAvailable");

    public final BooleanPath paymentType = createBoolean("paymentType");

    public final QPropertyCondition propertyCondition;

    public final NumberPath<Long> propertyId = createNumber("propertyId", Long.class);

    public final ListPath<PropertyImage, QPropertyImage> propertyImageList = this.<PropertyImage, QPropertyImage>createList("propertyImageList", PropertyImage.class, QPropertyImage.class, PathInits.DIRECT2);

    public final NumberPath<Integer> roomCount = createNumber("roomCount", Integer.class);

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final EnumPath<com.realEstate.realEstate.model.constant.Structure> structure = createEnum("structure", com.realEstate.realEstate.model.constant.Structure.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final BooleanPath UsageFee = createBoolean("UsageFee");

    public final QUser user;

    public final NumberPath<Integer> wholeFloor = createNumber("wholeFloor", Integer.class);

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
        this.propertyCondition = inits.isInitialized("propertyCondition") ? new QPropertyCondition(forProperty("propertyCondition"), inits.get("propertyCondition")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

