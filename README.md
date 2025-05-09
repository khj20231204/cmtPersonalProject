1. # ITWILL 심화반 TEAM POJECT 📒
    <img src="/readmeImg/cmt.png" width="100%">   

    Centralized Manufacturing & Tracking

    연결 - <a href="15.165.75.24:8080" style="font-weight:bold;cursor:pointer;text-decoration:underline">팀프로젝트 수정 사이트</a>

    <span style='color:red;font-weight:bold'>CMT Co., Ltd.</span>
    
1. # Tech Stack

- FRONTEND   
HTML5, CSS3, JAVASCRIPT, JASPER, TOAST UI   

- BACKEND   
JDK : 21 LTS   
Spring Boot : v 3.4.3   
Spring Security : v 6.2.3   
Oracle : Oracle Database 21c Release   
JPA : Hibernate (ORM) 6.4.2.Final   
Thymeleaf : 3.1.3.RELEASE   
MyBatis / Gradle   

- INFRA   
TOMCAT : v 10.1.40   
GitHub - Project   
AWS CI / CD   

    ![HTML5](https://img.shields.io/badge/html5-red.svg?style=for-the-badge&logo=html5&logoColor=white)
    ![CSS3](https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white)
    ![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E)
    ![Oracle](https://img.shields.io/badge/Oracle-%231572B6.svg?style=for-the-badge&logo=oracle&logoColor=blue)
    ![MyBatis](https://img.shields.io/badge/mybatis-%2300f.svg?style=for-the-badge&logo=mybatis&logoColor=white)
    ![Thymeleaf](https://img.shields.io/badge/Thymeleaf-%23121011.svg?style=for-the-badge&logo=thymeleaf&logoColor=white)
    ![SpringBoot](https://img.shields.io/badge/springboot-green.svg?style=for-the-badge&logo=springboot&logoColor=white)
    ![Spring Security](https://img.shields.io/badge/Spring%20Security-%236DB33F.svg?style=for-the-badge&logo=springsecurity&logoColor=white)
    ![AWS](https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=aws&logoColor=white)

1. # Member / Period 🧑‍🤝‍🧑
    1명 / 2개월   
    Team프로젝트인 CMT PROJECT(https://github.com/khj20231204/cmtProject) 에서 개선 해야할 부분과 기능을 추가하여 완성한 개인 프로젝트.   

    *MES파트에서 공정 부분만 제가 했고, 작업지시서와 LOT추적은 다른 팀원이 했지만 흐름상 추가함   

2. # MES 🎆
    MES는 Manufacturing Execution System의 약자로, 제조 실행 시스템이라고 번역됩니다. 원자재부터 완제품까지 제조 과정을 실시간으로 모니터링, 추적, 문서화, 제어하는 소프트웨어 시스템입니다. 생산 계획의 실행, 공정 추적, 품질 관리, 작업 지시 관리 등을 지원하여 생산 효율성을 높이고 품질을 향상시키는 데 도움을 줍니다.    

3. # FLOW CHART
    
4. # SUBJECT 🚓
    __자동차 프레임 생산__ 을 주제로 MES를 구성했습니다.   
    자동차 프레임은 자동차의 구조적 뼈대이며, 차체 조립의 기반이 됩니다. 프레임 공정은 일반적으로 다음과 같은 단계를 포함합니다.   

    1단계 - 압축(PRESS)   
    2단계 - 용접(WELDING)   
    3단계 - 도장(PAINT)   
    4단계 - 조립(ASSEMBLE)   

    이 모든 과정은 높은 정밀도와 추적성이 요구되며, 수많은 부품과 설비가 유기적으로 연결되어 있습니다.  

5. # Role PART 🔍
    ✅ 실시간 LOT 추적   
    MES는 각 프레임 부품의 LOT 번호를 기준으로 이력 추적을 가능. 용접된 부품이 어떤 소재에서 나왔는지, 언제 어떤 설비에서 가공되었는지를 실시간으로 확인할 수 있습니다. 이는 리콜 대응 및 품질 분석에 핵심적인 기능.     

    ✅ BOM 기반 작업 흐름   
    자동차 프레임은 다단계 BOM 구조로 이루어져 있으며, MES는 이를 기반으로 하위 부품의 생산 흐름을 제어. 예를 들어, 특정 용접 조립이 선행되어야만 다음 공정이 활성화되도록 설정.   

    ✅ 공정 현황 Recod   
    공정이 이루어지는 모든 단계를 기록했다가 재로딩시 이전 상황가 일치하게 로딩되는 페이지 구현.   
    
6. # DETAIL ROLE 🎉   
    [수주/발주](https://github.com/khj20231204/cmtPersonalProject/wiki/so_po)

    [BOM 기준 정보](https://github.com/khj20231204/cmtPersonalProject/wiki/Bom_info)

    [공정](https://github.com/khj20231204/cmtPersonalProject/wiki/prc)

7. # GitHub Project
    Main, Develop, pull request, merge

8. # AWS CI/CD

    <img src="../../imgs/LESSON/SPRING(Lesson)/server_error.png" style="border:3px solid black;border-radius:9px;width:300px">   

9. # 남기고 싶은 말
    BOM테이블에서 계층 구조를 구성하는 부분과 LOT를 생성하는 부분에서 많은 시간과 노력이 들어갔습니다. 이 부분을 함께 고민하고 구현하는데 많은 조언을 주신 같은 공정 파트 팀원에게 감사하단 말씀을 드리고싶습니다.   