package com.realEstate.realEstate.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QContract is a Querydsl query type for Contract
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QContract extends EntityPathBase<Contract> {

    private static final long serialVersionUID = 732296107L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QContract contract = new QContract("contract");

    public final com.realEstate.realEstate.model.QBaseEntity _super = new com.realEstate.realEstate.model.QBaseEntity(this);

    public final NumberPath<java.math.BigDecimal> balance = createNumber("balance", java.math.BigDecimal.class);

    public final DatePath<java.sql.Date> balanceDate = createDate("balanceDate", java.sql.Date.class);

    public final StringPath contractDateFrom = createString("contractDateFrom");

    public final StringPath contractDateTo = createString("contractDateTo");

    public final NumberPath<java.math.BigDecimal> contractPrice = createNumber("contractPrice", java.math.BigDecimal.class);

    public final DatePath<java.sql.Date> contractPriceDate = createDate("contractPriceDate", java.sql.Date.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<java.math.BigDecimal> Installment = createNumber("Installment", java.math.BigDecimal.class);

    public final DatePath<java.sql.Date> InstallmentDate = createDate("InstallmentDate", java.sql.Date.class);

    public final BooleanPath internet = createBoolean("internet");

    public final DatePath<java.sql.Date> loanAmountDate = createDate("loanAmountDate", java.sql.Date.class);

    public final DatePath<java.sql.Date> parkFeeDate = createDate("parkFeeDate", java.sql.Date.class);

    public final QProperty property;

    public final StringPath propertyType = createString("propertyType");

    public final StringPath section = createString("section");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final QUser user;

    public final BooleanPath utilities = createBoolean("utilities");

    public QContract(String variable) {
        this(Contract.class, forVariable(variable), INITS);
    }

    public QContract(Path<? extends Contract> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QContract(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QContract(PathMetadata metadata, PathInits inits) {
        this(Contract.class, metadata, inits);
    }

    public QContract(Class<? extends Contract> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.property = inits.isInitialized("property") ? new QProperty(forProperty("property"), inits.get("property")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

