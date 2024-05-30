package com.realEstate.realEstate.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPropertyCondition is a Querydsl query type for PropertyCondition
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPropertyCondition extends EntityPathBase<PropertyCondition> {

    private static final long serialVersionUID = -469538739L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPropertyCondition propertyCondition = new QPropertyCondition("propertyCondition");

    public final StringPath busStation = createString("busStation");

    public final NumberPath<Integer> busTime = createNumber("busTime", Integer.class);

    public final BooleanPath busWalk = createBoolean("busWalk");

    public final StringPath departmentStore = createString("departmentStore");

    public final NumberPath<Integer> departmentTime = createNumber("departmentTime", Integer.class);

    public final BooleanPath departmentWalk = createBoolean("departmentWalk");

    public final StringPath hospitalStore = createString("hospitalStore");

    public final NumberPath<Integer> hospitalTime = createNumber("hospitalTime", Integer.class);

    public final BooleanPath hospitalWalk = createBoolean("hospitalWalk");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lineMemo = createString("lineMemo");

    public final StringPath memo = createString("memo");

    public final StringPath parkingMemo = createString("parkingMemo");

    public final EnumPath<com.realEstate.realEstate.model.constant.ParkingOption> parkingOption = createEnum("parkingOption", com.realEstate.realEstate.model.constant.ParkingOption.class);

    public final QProperty property;

    public final NumberPath<Integer> streetL = createNumber("streetL", Integer.class);

    public final BooleanPath streetPaving = createBoolean("streetPaving");

    public final NumberPath<Integer> streetR = createNumber("streetR", Integer.class);

    public final StringPath subwayStation = createString("subwayStation");

    public final NumberPath<Integer> subwayTime = createNumber("subwayTime", Integer.class);

    public final BooleanPath subwayWalk = createBoolean("subwayWalk");

    public QPropertyCondition(String variable) {
        this(PropertyCondition.class, forVariable(variable), INITS);
    }

    public QPropertyCondition(Path<? extends PropertyCondition> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPropertyCondition(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPropertyCondition(PathMetadata metadata, PathInits inits) {
        this(PropertyCondition.class, metadata, inits);
    }

    public QPropertyCondition(Class<? extends PropertyCondition> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.property = inits.isInitialized("property") ? new QProperty(forProperty("property"), inits.get("property")) : null;
    }

}

