package com.realEstate.realEstate.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSignature is a Querydsl query type for Signature
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSignature extends EntityPathBase<Signature> {

    private static final long serialVersionUID = -1599534785L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSignature signature = new QSignature("signature");

    public final com.realEstate.realEstate.model.QBaseEntity _super = new com.realEstate.realEstate.model.QBaseEntity(this);

    public final QContract contract;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final SimplePath<java.sql.Blob> signatureData = createSimple("signatureData", java.sql.Blob.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QSignature(String variable) {
        this(Signature.class, forVariable(variable), INITS);
    }

    public QSignature(Path<? extends Signature> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSignature(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSignature(PathMetadata metadata, PathInits inits) {
        this(Signature.class, metadata, inits);
    }

    public QSignature(Class<? extends Signature> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.contract = inits.isInitialized("contract") ? new QContract(forProperty("contract"), inits.get("contract")) : null;
    }

}

