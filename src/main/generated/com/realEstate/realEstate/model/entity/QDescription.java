package com.realEstate.realEstate.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDescription is a Querydsl query type for Description
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDescription extends EntityPathBase<Description> {

    private static final long serialVersionUID = 2093412035L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDescription description = new QDescription("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath loanAvailable = createBoolean("loanAvailable");

    public final StringPath memo = createString("memo");

    public final BooleanPath petFriendly = createBoolean("petFriendly");

    public final QProperty property;

    public QDescription(String variable) {
        this(Description.class, forVariable(variable), INITS);
    }

    public QDescription(Path<? extends Description> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDescription(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDescription(PathMetadata metadata, PathInits inits) {
        this(Description.class, metadata, inits);
    }

    public QDescription(Class<? extends Description> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.property = inits.isInitialized("property") ? new QProperty(forProperty("property"), inits.get("property")) : null;
    }

}

