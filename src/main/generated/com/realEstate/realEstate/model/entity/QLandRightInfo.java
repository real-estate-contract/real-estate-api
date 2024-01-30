package com.realEstate.realEstate.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLandRightInfo is a Querydsl query type for LandRightInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLandRightInfo extends EntityPathBase<LandRightInfo> {

    private static final long serialVersionUID = 1330874438L;

    public static final QLandRightInfo landRightInfo = new QLandRightInfo("landRightInfo");

    public final com.realEstate.realEstate.model.QBaseEntity _super = new com.realEstate.realEstate.model.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> landRightId = createNumber("landRightId", Long.class);

    public final StringPath ldaQotaRate = createString("ldaQotaRate");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QLandRightInfo(String variable) {
        super(LandRightInfo.class, forVariable(variable));
    }

    public QLandRightInfo(Path<? extends LandRightInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLandRightInfo(PathMetadata metadata) {
        super(LandRightInfo.class, metadata);
    }

}

