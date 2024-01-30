package com.realEstate.realEstate.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBuildingInfo is a Querydsl query type for BuildingInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBuildingInfo extends EntityPathBase<BuildingInfo> {

    private static final long serialVersionUID = 1409834907L;

    public static final QBuildingInfo buildingInfo = new QBuildingInfo("buildingInfo");

    public final com.realEstate.realEstate.model.QBaseEntity _super = new com.realEstate.realEstate.model.QBaseEntity(this);

    public final NumberPath<Integer> archArea = createNumber("archArea", Integer.class);

    public final NumberPath<Integer> bcRat = createNumber("bcRat", Integer.class);

    public final StringPath bjdongCode = createString("bjdongCode");

    public final NumberPath<Long> buildingId = createNumber("buildingId", Long.class);

    public final StringPath bun = createString("bun");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath etcRoof = createString("etcRoof");

    public final StringPath ji = createString("ji");

    public final StringPath mainPurpsCdNm = createString("mainPurpsCdNm");

    public final StringPath newPlatPlc = createString("newPlatPlc");

    public final StringPath pnu = createString("pnu");

    public final StringPath sigunguCode = createString("sigunguCode");

    public final StringPath strctCdNm = createString("strctCdNm");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath useAprDay = createString("useAprDay");

    public final NumberPath<Integer> vlRat = createNumber("vlRat", Integer.class);

    public QBuildingInfo(String variable) {
        super(BuildingInfo.class, forVariable(variable));
    }

    public QBuildingInfo(Path<? extends BuildingInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBuildingInfo(PathMetadata metadata) {
        super(BuildingInfo.class, metadata);
    }

}

