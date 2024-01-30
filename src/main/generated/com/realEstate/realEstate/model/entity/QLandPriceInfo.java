package com.realEstate.realEstate.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLandPriceInfo is a Querydsl query type for LandPriceInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLandPriceInfo extends EntityPathBase<LandPriceInfo> {

    private static final long serialVersionUID = 774248691L;

    public static final QLandPriceInfo landPriceInfo = new QLandPriceInfo("landPriceInfo");

    public final com.realEstate.realEstate.model.QBaseEntity _super = new com.realEstate.realEstate.model.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> landPriceId = createNumber("landPriceId", Long.class);

    public final StringPath pblntfPclnd = createString("pblntfPclnd");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QLandPriceInfo(String variable) {
        super(LandPriceInfo.class, forVariable(variable));
    }

    public QLandPriceInfo(Path<? extends LandPriceInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLandPriceInfo(PathMetadata metadata) {
        super(LandPriceInfo.class, metadata);
    }

}

