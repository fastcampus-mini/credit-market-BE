<center><img width="100%" src="https://i.ibb.co/5vBJ53Z/3.png"></center>

# :moneybag: [Credit Market (Demo)](https://credit-market.netlify.app/)

- :page_with_curl: Check out project documents at [<img src="https://img.shields.io/badge/Notion-000000?style=flat-round&logo=Notion&logoColor=white"/>](https://www.notion.so/Mini-Project-b53fa0f445ce4afbacebffd01a813965?pvs=4)
- :triangular_ruler: Check out project structures at [<img src="https://img.shields.io/badge/Github Wiki-181717?style=flat-round&logo=Github&logoColor=white"/>](https://github.com/fastcampus-mini/credit-market-FE/wiki/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EA%B5%AC%EC%A1%B0)
 
<br/>

## :hammer: 기술 스택 (Technologies Used)
![Java](https://img.shields.io/badge/Java-007396?style=flat-round&logo=Java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat-round&logo=Spring%20Boot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?style=flat-round&logo=Spring&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-6DB33F?style=flat-round&logo=Spring&logoColor=white)
![Springfox Swagger](https://img.shields.io/badge/Springfox%20Swagger-85EA2D?style=flat-round&logo=Swagger&logoColor=black)

![nginx](https://img.shields.io/badge/nginx-009639?style=flat-round&logo=nginx&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-BC2055?style=flat-round&logo=Lombok&logoColor=white)
![JSON Web Tokens](https://img.shields.io/badge/JSON%20Web%20Tokens-000000?style=flat-round&logo=json-web-tokens)
![MariaDB](https://img.shields.io/badge/MariaDB-003545?style=flat-round&logo=MariaDB&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat-round&logo=MySQL&logoColor=white)
![Amazon Web Services](https://img.shields.io/badge/Amazon%20Web%20Services-232F3E?style=flat-round&logo=amazon-aws&logoColor=white)


<br/><br/>


## :triangular_ruler: 프로젝트 구조 (Project Structures)
```
📁 project/
   📂 src/
      📂 main/
         📂 java/
            📂 com.example.creditmarket/
               📂 configuration/
                  📄 AuthenticationCoonfig.java
                  📄 EncoderConfig.java
                  📄 JwtFilter.java
               📂 controller/
               📂 dto/
               📂 entity/
                  📄 EntityCart.java
                  📄 EntityFavorite.java
                  📄 EntityFProduct.java
                  📄 EntityOption.java
                  📄 EntityOrder.java
                  📄 EntityToken.java
                  📄 EntityUser.java
               📂 exception/
                  📄 AppException.java
                  📄 ErrorCode.java
                  📄 ExceptionManager.java
               📂 openAPI/
                  📂 crawling/
                     📄 CrawlingOpenAPI.java
                     📄 CrawlingRepositoryFProduct.java
                     📄 CrawlingRepositoryOption.java
                     📄 CrawlingService.java
               📂 repository/
                  📄 CartRepository.java
                  📄 FavoriteRepository.java
                  📄 FProductRepository.java
                  📄 OptionRepository.java
                  📄 OrderRepository.java
                  📄 ProductRepository.java
                  📄 TokenRepository.java
                  📄 UserRepository.java
               📂 service/
               📂 utils/
                  📄 JwtUtil.java
         📂 resources/
            📄 application.properties
      📂 test/
         📂 java/
            📂 com.example.creditmarket/
               📂 controller/
                  📄 UserControllerTest.java
```

<br/><br/>



## :floppy_disk: ER 다이어그램 (ER-Diagram with Cardinality Constraints)
![image](https://user-images.githubusercontent.com/113500771/220207986-4ae94752-b934-46ef-b98a-f97a1b9a575b.png)


<br/><br/>

## :sunglasses: Member

<table border>
  <tbody>
    <tr>
      <td align="center" width="150px">
        <img width="100%" src="https://avatars.githubusercontent.com/u/113500771?v=4"  alt=""/>
        <center>BE. 팀장</center>
        <br/>
        <a href="https://github.com/724thomas">
          <img src="https://img.shields.io/badge/최원준-6e34bf?style=flat-round&logo=GitHub&logoColor=white"/>
        </a>
      </br>
      <td align="center" width="150px">
        <img width="100%" src="https://avatars.githubusercontent.com/u/113500815?v=4"  alt=""/>
        <center>BE. 팀원</center>
        <br/>
        <a href="https://github.com/JeYeongR">
          <img src="https://img.shields.io/badge/류제영-345ebf?style=flat-round&logo=GitHub&logoColor=white"/>
        </a>
      </td>
      <td align="center" width="150px">
        <img width="100%" src="https://avatars.githubusercontent.com/u/102227839?v=4"  alt=""/>
        <center>BE. 팀원</center>
        <br/>
        <a href="https://github.com/Ryusunshine">
          <img src="https://img.shields.io/badge/염류선-ff5e5e?style=flat-round&logo=GitHub&logoColor=white"/>
        </a>
      </td>
      <td align="center" width="150px">
        <img width="100%" src="https://avatars.githubusercontent.com/u/107986524?v=4"  alt=""/>
        <center>BE. 팀원</center>
        <br/>
        <a href="https://github.com/devSojoong">
          <img src="https://img.shields.io/badge/윤소중-34bfa6?style=flat-round&logo=GitHub&logoColor=white"/>
        </a>
      </td>
     </tr>
    <tr>
      <td align="center" width="150px">
        <img width="100%" src="https://avatars.githubusercontent.com/u/87680906?v=4"  alt=""/>
        <center>FE. 팀장</center>
        <br/>
        <a href="https://github.com/autumnly1007">
          <img src="https://img.shields.io/badge/안가을-2E3084?style=flat-round&logo=GitHub&logoColor=white"/>
        </a>
      </br>
      <td align="center" width="150px">
        <img width="100%" src="https://avatars.githubusercontent.com/u/48847034?v=4"  alt=""/>
        <center>FE. 팀원</center>
        <br/>
        <a href="https://github.com/DavidOH77">
          <img src="https://img.shields.io/badge/오혜성-8B89CC?style=flat-round&logo=GitHub&logoColor=white"/>
        </a>
      </td>
      <td align="center" width="150px">
        <img width="100%" src="https://avatars.githubusercontent.com/u/102899881?v=4"  alt=""/>
        <center>FE. 팀원</center>
        <br/>
        <a href="https://github.com/helloavenir">
          <img src="https://img.shields.io/badge/이한나-F5AE29?style=flat-round&logo=GitHub&logoColor=white"/>
        </a>
      </td>
      <td align="center" width="150px">
        <img width="100%" src="https://avatars.githubusercontent.com/u/64674174?v=4"  alt=""/>
        <center>FE. 팀원</center>
        <br/>
        <a href="https://github.com/hyerimhan">
          <img src="https://img.shields.io/badge/한혜림-FF55B6?style=flat-round&logo=GitHub&logoColor=white"/>
        </a>
      </td>
     </tr>
  </tbody>
</table>

<br/><br/>
