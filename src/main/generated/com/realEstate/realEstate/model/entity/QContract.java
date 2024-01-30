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

    public final DatePath<java.time.LocalDate> balancePaymentDate = createDate("balancePaymentDate", java.time.LocalDate.class);

    public final QUser buyer;

    public final DatePath<java.time.LocalDate> contractDate = createDate("contractDate", java.time.LocalDate.class);

    public final NumberPath<Long> contractId = createNumber("contractId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DatePath<java.time.LocalDate> deliveryDate = createDate("deliveryDate", java.time.LocalDate.class);

    public final BooleanPath drainage = createBoolean("drainage");

    public final BooleanPath electricitySupplyStatus = createBoolean("electricitySupplyStatus");

    public final EnumPath<com.realEstate.realEstate.model.constant.ElevatorStatus> elevatorStatus = createEnum("elevatorStatus", com.realEstate.realEstate.model.constant.ElevatorStatus.class);

    public final BooleanPath fireSafety = createBoolean("fireSafety");

    public final NumberPath<java.math.BigDecimal> firstInstallment = createNumber("firstInstallment", java.math.BigDecimal.class);

    public final DatePath<java.time.LocalDate> firstPaymentDate = createDate("firstPaymentDate", java.time.LocalDate.class);

    public final EnumPath<com.realEstate.realEstate.model.constant.FloorCondition> floorCondition = createEnum("floorCondition", com.realEstate.realEstate.model.constant.FloorCondition.class);

    public final BooleanPath gasSupplyMethod = createBoolean("gasSupplyMethod");

    public final EnumPath<com.realEstate.realEstate.model.constant.HeatingSupplyMethod> heatingSupplyMethod = createEnum("heatingSupplyMethod", com.realEstate.realEstate.model.constant.HeatingSupplyMethod.class);

    public final BooleanPath heatingSystemOperation = createBoolean("heatingSystemOperation");

    public final EnumPath<com.realEstate.realEstate.model.constant.HeatingType> heatingType = createEnum("heatingType", com.realEstate.realEstate.model.constant.HeatingType.class);

    public final NumberPath<java.math.BigDecimal> loanAmount = createNumber("loanAmount", java.math.BigDecimal.class);

    public final EnumPath<com.realEstate.realEstate.model.constant.NoiseLevel> noiseLevel = createEnum("noiseLevel", com.realEstate.realEstate.model.constant.NoiseLevel.class);

    public final StringPath otherFacilities = createString("otherFacilities");

    public final QProperty property;

    public final BooleanPath repairsNeeded = createBoolean("repairsNeeded");

    public final StringPath rightsClaim = createString("rightsClaim");

    public final NumberPath<java.math.BigDecimal> secondInstallment = createNumber("secondInstallment", java.math.BigDecimal.class);

    public final DatePath<java.time.LocalDate> secondPaymentDate = createDate("secondPaymentDate", java.time.LocalDate.class);

    public final StringPath specialAgreement = createString("specialAgreement");

    public final StringPath succeedingBank = createString("succeedingBank");

    public final BooleanPath sufficientWaterSupply = createBoolean("sufficientWaterSupply");

    public final EnumPath<com.realEstate.realEstate.model.constant.SunlightAmount> sunlightAmount = createEnum("sunlightAmount", com.realEstate.realEstate.model.constant.SunlightAmount.class);

    public final EnumPath<com.realEstate.realEstate.model.constant.TilingCondition> tilingCondition = createEnum("tilingCondition", com.realEstate.realEstate.model.constant.TilingCondition.class);

    public final NumberPath<java.math.BigDecimal> transactionAmount = createNumber("transactionAmount", java.math.BigDecimal.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final EnumPath<com.realEstate.realEstate.model.constant.VibrationLevel> vibrationLevel = createEnum("vibrationLevel", com.realEstate.realEstate.model.constant.VibrationLevel.class);

    public final BooleanPath wallCracks = createBoolean("wallCracks");

    public final BooleanPath wallLeakage = createBoolean("wallLeakage");

    public final BooleanPath waterDamage = createBoolean("waterDamage");

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
        this.buyer = inits.isInitialized("buyer") ? new QUser(forProperty("buyer")) : null;
        this.property = inits.isInitialized("property") ? new QProperty(forProperty("property"), inits.get("property")) : null;
    }

}

