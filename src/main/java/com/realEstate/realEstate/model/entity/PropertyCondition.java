package com.realEstate.realEstate.model.entity;

import com.realEstate.realEstate.model.BaseEntity;
import com.realEstate.realEstate.model.constant.ManagementType;
import com.realEstate.realEstate.model.constant.ParkingOption;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class PropertyCondition{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lineMemo; // 한줄정리
    @Column
    private String memo; // 상세설명
    //도로 관련
    private int streetL; // 왼쪽 도로 폭
    private int streetR; // 오른쪽 도로 폭
    private boolean streetPaving; // 도로 포장 여부( True : 포장 , False : 비포장)

    //대중 교통
    private String busStation; // 버스 정류장 이름
    private boolean busWalk; // True : 도보 , False : 차량
    private int busTime; // 정류장 까지 소요 시간
    private String subwayStation; // 지하철 역 이름
    private boolean subwayWalk; // True : 도보, False : 차량
    private int subwayTime; // 역까지 소요 시간

    //주차장
    private ParkingOption parkingOption; // 전용 주차 시설, 공동 주차 시설, 그 밖의 주차 시설
    private String parkingMemo; // 그밖의 주차 시설 상세 입력


    //판매 및 편의 시설
    private String departmentStore; // 백화점 및 할인 매장
    private boolean departmentWalk; // True : 도보 , False : 차량
    private int departmentTime; // 백화점 까지 소요 시간

    private String hospitalStore; // 병원 이름
    private boolean hospitalWalk; // True : 도보 , False : 차량
    private int hospitalTime; // 학교 까지 소요 시간


    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "property_id")
    private Property property;

    public static PropertyCondition of(String lineMemo, String memo, int streetL, int streetR, boolean streetPaving, String busStation, boolean busWalk, int busTime, String subwayStation, boolean subwayWalk, int subwayTime, ParkingOption parkingOption, String parkingMemo,  String departmentStore, boolean departmentWalk, int departmentTime, String hospitalStore, boolean hospitalWalk, int hospitalTime,  Property property) {
        PropertyCondition propertyCondition = new PropertyCondition();
        propertyCondition.setLineMemo(lineMemo);
        propertyCondition.setMemo(memo);
        propertyCondition.setStreetL(streetL);
        propertyCondition.setStreetR(streetR);
        propertyCondition.setStreetPaving(streetPaving);
        propertyCondition.setBusStation(busStation);
        propertyCondition.setBusWalk(busWalk);
        propertyCondition.setBusTime(busTime);
        propertyCondition.setSubwayStation(subwayStation);
        propertyCondition.setSubwayWalk(subwayWalk);
        propertyCondition.setSubwayTime(subwayTime);
        propertyCondition.setParkingOption(parkingOption);
        propertyCondition.setParkingMemo(parkingMemo);
        propertyCondition.setDepartmentStore(departmentStore);
        propertyCondition.setDepartmentWalk(departmentWalk);
        propertyCondition.setDepartmentTime(departmentTime);
        propertyCondition.setHospitalStore(hospitalStore);
        propertyCondition.setHospitalWalk(hospitalWalk);
        propertyCondition.setHospitalTime(hospitalTime);
        propertyCondition.setProperty(property);

        return propertyCondition;
    }
}