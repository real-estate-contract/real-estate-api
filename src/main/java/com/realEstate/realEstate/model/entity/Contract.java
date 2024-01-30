package com.realEstate.realEstate.model.entity;

import com.realEstate.realEstate.model.BaseEntity;
import com.realEstate.realEstate.model.constant.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Getter
@ToString
@Setter
@Table
public class Contract extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractId;

    // 융자금
    private BigDecimal loanAmount;

    // 승계은행
    private String succeedingBank;

    // 1차 중도금
    private BigDecimal firstInstallment;

    // 1차 지급일
    private LocalDate firstPaymentDate;

    // 2차 중도금
    private BigDecimal secondInstallment;

    // 2차 지급일
    private LocalDate secondPaymentDate;

    // 잔금
    private BigDecimal balance;

    // 잔금 지급일
    private LocalDate balancePaymentDate;

    // 계약일
    private LocalDate contractDate;

    // 인도일
    private LocalDate deliveryDate;

    // 특약사항
    private String specialAgreement;

    // 거래예정금액
    private BigDecimal transactionAmount;

    // 권리사항
    private String rightsClaim;

    // 수도 파손
    private boolean waterDamage;

    // 수도 용수량
    private boolean sufficientWaterSupply;

    // 전기 공급상태
    private boolean electricitySupplyStatus;

    // 가스 공급방식
    private boolean gasSupplyMethod;

    // 소방
    private boolean fireSafety;

    // 난방 공급방식
    private HeatingSupplyMethod heatingSupplyMethod;  // 중앙공급, 개별공급

    // 난방 시설작동
    private boolean heatingSystemOperation;

    // 난방 종류
    private HeatingType heatingType;  // 도시가스, 기름, 프로판가스, 연탄, etc

    // 승강기
    private ElevatorStatus elevatorStatus;  // 양호, 불량, 없음

    // 배수
    private boolean drainage;

    // 그 밖의 시설물
    private String otherFacilities;

    // 벽면균열
    private boolean wallCracks;

    // 벽면누수
    private boolean wallLeakage;

    // 박닥면상태
    private FloorCondition floorCondition;  // 깨끗함, 보통임, 수리필요

    // 도배상태
    private TilingCondition tilingCondition;  // 깨끗함, 보통임, 도배필요

    // 일조량
    private SunlightAmount sunlightAmount;  // 풍부함, 보통임, 불충분

    // 소음
    private NoiseLevel noiseLevel;  // 아주작음, 보통임, 심한편임

    // 진동
    private VibrationLevel vibrationLevel;  // 아주작음, 보통임, 심한편임

    // 수리필요시설
    private boolean repairsNeeded;


    @ManyToOne // Contract는 여러 개의 계약이 하나의 부동산과 연결될 수 있음
    @JoinColumn(name = "propertyId")
    private Property property;

    @ManyToOne // 여러 개의 계약이 하나의 구매자와 연결될 수 있음
    @JoinColumn(name = "buyerId")
    private User buyer;

    @Transient //데이터베이스에 저장 하지 않음
    private BuildingInfo buildingInfo;

    @Transient //데이터베이스에 저장 하지 않음
    private LandInfo landInfo;



    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public static Contract of(BigDecimal loanAmount, String succeedingBank, BigDecimal firstInstallment,
                              LocalDate firstPaymentDate, BigDecimal secondInstallment, LocalDate secondPaymentDate,
                              BigDecimal balance, LocalDate balancePaymentDate, LocalDate contractDate,
                              LocalDate deliveryDate, String specialAgreement, BigDecimal transactionAmount,
                              String rightsClaim, boolean waterDamage, boolean sufficientWaterSupply,
                              boolean electricitySupplyStatus, boolean gasSupplyMethod, boolean fireSafety,
                              HeatingSupplyMethod heatingSupplyMethod, boolean heatingSystemOperation,
                              HeatingType heatingType, ElevatorStatus elevatorStatus, boolean drainage,
                              String otherFacilities, boolean wallCracks, boolean wallLeakage,
                              FloorCondition floorCondition, TilingCondition tilingCondition,
                              SunlightAmount sunlightAmount, NoiseLevel noiseLevel,
                              VibrationLevel vibrationLevel, boolean repairsNeeded,
                              Property property, User buyer) {

        Contract contract = new Contract();
        contract.setLoanAmount(loanAmount);
        contract.setSucceedingBank(succeedingBank);
        contract.setFirstInstallment(firstInstallment);
        contract.setFirstPaymentDate(firstPaymentDate);
        contract.setSecondInstallment(secondInstallment);
        contract.setSecondPaymentDate(secondPaymentDate);
        contract.setBalance(balance);
        contract.setBalancePaymentDate(balancePaymentDate);
        contract.setContractDate(contractDate);
        contract.setDeliveryDate(deliveryDate);
        contract.setSpecialAgreement(specialAgreement);
        contract.setTransactionAmount(transactionAmount);
        contract.setRightsClaim(rightsClaim);
        contract.setWaterDamage(waterDamage);
        contract.setSufficientWaterSupply(sufficientWaterSupply);
        contract.setElectricitySupplyStatus(electricitySupplyStatus);
        contract.setGasSupplyMethod(gasSupplyMethod);
        contract.setFireSafety(fireSafety);
        contract.setHeatingSupplyMethod(heatingSupplyMethod);
        contract.setHeatingSystemOperation(heatingSystemOperation);
        contract.setHeatingType(heatingType);
        contract.setElevatorStatus(elevatorStatus);
        contract.setDrainage(drainage);
        contract.setOtherFacilities(otherFacilities);
        contract.setWallCracks(wallCracks);
        contract.setWallLeakage(wallLeakage);
        contract.setFloorCondition(floorCondition);
        contract.setTilingCondition(tilingCondition);
        contract.setSunlightAmount(sunlightAmount);
        contract.setNoiseLevel(noiseLevel);
        contract.setVibrationLevel(vibrationLevel);
        contract.setRepairsNeeded(repairsNeeded);
        contract.setProperty(property);
        contract.setBuyer(buyer);

        return contract;
    }



    // 판매자 정보는 Property에서 가져올 수 있으므로 sellerID 필드는 제거

}