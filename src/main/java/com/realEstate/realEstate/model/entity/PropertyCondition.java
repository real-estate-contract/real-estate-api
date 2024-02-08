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
@Table(name = "property_condition")
public class PropertyCondition extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //도로 관련
    @Column(name = "street_l")
    private int streetL; // 왼쪽 도로 폭
    @Column(name = "street_r")
    private int streetR; // 오른쪽 도로 폭
    @Column(name = "street_paving")
    private boolean streetPaving; // 도로 포장 여부( True : 포장 , False : 비포장)
    @Column(name = "street_accessibility")
    private boolean streetAccessibility; // 도로 접근성 여부(True : 용이함, False : 불편함)

    //대중 교통
    @Column(name = "bus_station")
    private String busStation; // 버스 정류장 이름
    @Column(name = "bus_walk")
    private boolean busWalk; // True : 도보 , False : 차량
    @Column(name = "bus_time")
    private int busTime; // 정류장 까지 소요 시간
    @Column(name = "subway_station")
    private String subwayStation; // 지하철 역 이름
    @Column(name = "subway_walk")
    private boolean subwayWalk; // True : 도보, False : 차량
    @Column(name = "subway_time")
    private int subwayTime; // 역까지 소요 시간

    //주차장
    @Column(name = "parking_option")
    private ParkingOption parkingOption; // 전용 주차 시설, 공동 주차 시설, 그 밖의 주차 시설
    @Column(name = "parking_memo")
    private String parkingMemo; // 그밖의 주차 시설 상세 입력


    //교육시설
    @Column(name = "elementary_school")
    private String elementarySchool; // 초등 학교 이름
    @Column(name = "elementary_walk")
    private boolean elementaryWalk; // True : 도보 , False : 차량
    @Column(name = "elementary_time")
    private int elementaryTime; // 학교 까지 소요 시간

    @Column(name = "middle_school")
    private String middleSchool; // 중학교 이름
    @Column(name = "middle_walk")
    private boolean middleWalk; // True : 도보 , False : 차량
    @Column(name = "middle_time")
    private int middleTime; // 학교 까지 소요 시간

    @Column(name = "high_school")
    private String highSchool; // 고등 학교 이름
    @Column(name = "high_walk")
    private boolean highWalk; // True : 도보 , False : 차량
    @Column(name = "high_time")
    private int highTime; // 학교 까지 소요 시간

    //판매 및 편의 시설
    @Column(name = "department_store")
    private String departmentStore; // 백화점 및 할인 매장
    @Column(name = "department_walk")
    private boolean departmentWalk; // True : 도보 , False : 차량
    @Column(name = "department_time")
    private int departmentTime; // 학교 까지 소요 시간

    @Column(name = "hospital_store")
    private String hospitalStore; // 병원 이름
    @Column(name = "hospital_walk")
    private boolean hospitalWalk; // True : 도보 , False : 차량
    @Column(name = "hospital_time")
    private int hospitalTime; // 학교 까지 소요 시간

    //관리에 관한 사항
    @Column(name = "security_office")
    private boolean securityOffice; // 경비실 유무 (True: 있음, False: 없음)
    @Enumerated(EnumType.STRING)
    @Column(name = "management_type")
    private ManagementType managementType; // 관리 주체(위탁 관리, 자체 관리, 그 밖의 유형)

    //비선호 시설(1km 이내)
    @Column(name = "dispreferred_facilities")
    private boolean dispreferredFacilities; // 비선호 시설 유무
    @Column(name = "dispreferred_facilities_memo")
    private String dispreferredFacilitiesMemo; // 종류 및 위치

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "propertyId")
    private Property property;

    public static PropertyCondition of(int streetL, int streetR, boolean streetPaving, boolean streetAccessibility, String busStation, boolean busWalk, int busTime, String subwayStation, boolean subwayWalk, int subwayTime, ParkingOption parkingOption, String parkingMemo, String elementarySchool, boolean elementaryWalk, int elementaryTime, String middleSchool, boolean middleWalk, int middleTime, String highSchool, boolean highWalk, int highTime, String departmentStore, boolean departmentWalk, int departmentTime, String hospitalStore, boolean hospitalWalk, int hospitalTime, boolean securityOffice, ManagementType managementType, boolean dispreferredFacilities, String dispreferredFacilitiesMemo, Property property) {
        PropertyCondition propertyCondition = new PropertyCondition();
        propertyCondition.setStreetL(streetL);
        propertyCondition.setStreetR(streetR);
        propertyCondition.setStreetPaving(streetPaving);
        propertyCondition.setStreetAccessibility(streetAccessibility);
        propertyCondition.setBusStation(busStation);
        propertyCondition.setBusWalk(busWalk);
        propertyCondition.setBusTime(busTime);
        propertyCondition.setSubwayStation(subwayStation);
        propertyCondition.setSubwayWalk(subwayWalk);
        propertyCondition.setSubwayTime(subwayTime);
        propertyCondition.setParkingOption(parkingOption);
        propertyCondition.setParkingMemo(parkingMemo);
        propertyCondition.setElementarySchool(elementarySchool);
        propertyCondition.setElementaryWalk(elementaryWalk);
        propertyCondition.setElementaryTime(elementaryTime);
        propertyCondition.setMiddleSchool(middleSchool);
        propertyCondition.setMiddleWalk(middleWalk);
        propertyCondition.setMiddleTime(middleTime);
        propertyCondition.setHighSchool(highSchool);
        propertyCondition.setHighWalk(highWalk);
        propertyCondition.setHighTime(highTime);
        propertyCondition.setDepartmentStore(departmentStore);
        propertyCondition.setDepartmentWalk(departmentWalk);
        propertyCondition.setDepartmentTime(departmentTime);
        propertyCondition.setHospitalStore(hospitalStore);
        propertyCondition.setHospitalWalk(hospitalWalk);
        propertyCondition.setHospitalTime(hospitalTime);
        propertyCondition.setSecurityOffice(securityOffice);
        propertyCondition.setManagementType(managementType);
        propertyCondition.setDispreferredFacilities(dispreferredFacilities);
        propertyCondition.setDispreferredFacilitiesMemo(dispreferredFacilitiesMemo);
        propertyCondition.setProperty(property);

        return propertyCondition;
    }
}
