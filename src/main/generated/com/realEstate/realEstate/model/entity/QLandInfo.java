package com.realEstate.realEstate.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLandInfo is a Querydsl query type for LandInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLandInfo extends EntityPathBase<LandInfo> {

    private static final long serialVersionUID = -318414894L;

    public static final QLandInfo landInfo = new QLandInfo("landInfo");

    public final NumberPath<Long> landId = createNumber("landId", Long.class);

    public final StringPath ldCodeNm = createString("ldCodeNm");

    public final StringPath lndcgrCodeNm = createString("lndcgrCodeNm");

    public final StringPath lndpclAr = createString("lndpclAr");

    public QLandInfo(String variable) {
        super(LandInfo.class, forVariable(variable));
    }

    public QLandInfo(Path<? extends LandInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLandInfo(PathMetadata metadata) {
        super(LandInfo.class, metadata);
    }

}

