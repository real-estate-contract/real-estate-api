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

<<<<<<< HEAD
    public final com.realEstate.realEstate.model.QBaseEntity _super = new com.realEstate.realEstate.model.QBaseEntity(this);

=======
>>>>>>> zzim
    public final StringPath busStation = createString("busStation");

    public final NumberPath<Integer> busTime = createNumber("busTime", Integer.class);

    public final BooleanPath busWalk = createBoolean("busWalk");

<<<<<<< HEAD
    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

=======
>>>>>>> zzim
    public final StringPath departmentStore = createString("departmentStore");

    public final NumberPath<Integer> departmentTime = createNumber("departmentTime", Integer.class);

    public final BooleanPath departmentWalk = createBoolean("departmentWalk");

    public final BooleanPath dispreferredFacilities = createBoolean("dispreferredFacilities");

    public final StringPath dispreferredFacilitiesMemo = createString("dispreferredFacilitiesMemo");

    public final StringPath elementarySchool = createString("elementarySchool");

    public final NumberPath<Integer> elementaryTime = createNumber("elementaryTime", Integer.class);

    public final BooleanPath elementaryWalk = createBoolean("elementaryWalk");

    public final StringPath highSchool = createString("highSchool");

    public final NumberPath<Integer> highTime = createNumber("highTime", Integer.class);

    public final BooleanPath highWalk = createBoolean("highWalk");

    public final StringPath hospitalStore = createString("hospitalStore");

    public final NumberPath<Integer> hospitalTime = createNumber("hospitalTime", Integer.class);

    public final BooleanPath hospitalWalk = createBoolean("hospitalWalk");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.realEstate.realEstate.model.constant.ManagementType> managementType = createEnum("managementType", com.realEstate.realEstate.model.constant.ManagementType.class);

    public final StringPath middleSchool = createString("middleSchool");

    public final NumberPath<Integer> middleTime = createNumber("middleTime", Integer.class);

    public final BooleanPath middleWalk = createBoolean("middleWalk");

    public final StringPath parkingMemo = createString("parkingMemo");

    public final EnumPath<com.realEstate.realEstate.model.constant.ParkingOption> parkingOption = createEnum("parkingOption", com.realEstate.realEstate.model.constant.ParkingOption.class);

    public final QProperty property;

    public final BooleanPath securityOffice = createBoolean("securityOffice");

    public final BooleanPath streetAccessibility = createBoolean("streetAccessibility");

    public final NumberPath<Integer> streetL = createNumber("streetL", Integer.class);

    public final BooleanPath streetPaving = createBoolean("streetPaving");

    public final NumberPath<Integer> streetR = createNumber("streetR", Integer.class);

    public final StringPath subwayStation = createString("subwayStation");

    public final NumberPath<Integer> subwayTime = createNumber("subwayTime", Integer.class);

    public final BooleanPath subwayWalk = createBoolean("subwayWalk");

<<<<<<< HEAD
    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

=======
>>>>>>> zzim
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

