package com.realEstate.realEstate.model.entity.chat;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseImageEntity is a Querydsl query type for BaseImageEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseImageEntity extends EntityPathBase<BaseImageEntity> {

    private static final long serialVersionUID = 1029879760L;

    public static final QBaseImageEntity baseImageEntity = new QBaseImageEntity("baseImageEntity");

    public final StringPath imageName = createString("imageName");

    public final StringPath imageType = createString("imageType");

    public final StringPath imageUrl = createString("imageUrl");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final StringPath regId = createString("regId");

    public QBaseImageEntity(String variable) {
        super(BaseImageEntity.class, forVariable(variable));
    }

    public QBaseImageEntity(Path<? extends BaseImageEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseImageEntity(PathMetadata metadata) {
        super(BaseImageEntity.class, metadata);
    }

}

