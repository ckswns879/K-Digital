# ReadMe: 사업용 차량 안전 위험 분석 웹서비스 프로젝트  

사업용 차량의 운행기록을 바탕으로 차량 안전 관리정보를 보여주는 웹서비스입니다.


## Team Members:family:
- 진주호 https://github.com/juho-jin
- 김민수 https://github.com/Minsu0207
- 박경관 https://github.com/kyunggwan
- 김찬준 https://github.com/ckswns879

## Rerquirements
## Project2
- MySQL DB와 스프링부트간의 연결이다.
- DataController - DataService - DataDao 의 구조로 구성되어 있다.
- Controller에서는 URL을 통해 React앱에서 온 Http Request를 받는다.
- Dao객체는 MySQL의 DB와 연동되어 있으며, DB에는 사업용 차량 테이블 정보가 들어있다.
- 추가적으로 LogDao로 Log를 남기는 기능도 구현했다.

## Project3
- Project2에서 JDBC Template을 통한 구현으로 바꾸는 중이다.
--- 

MySQL  
다음과 같이 MySQL 사용자 계정을 설정한 다음  
더미 데이터(car.csv)를 다운받아 import 해서 데이터를 준비합니다.

사용자 계정 이름 : first  
비밀번호 : Xptmxm1!  
모든권한 부여 설정  
  
db명 : db1  
테이블명 : car  


동기가 무엇이었나요?
왜 이 프로젝트를 기획했나요?
이 프로젝트는 어떤 문제를 해결하나요?
이 프로젝트를 통해 무엇을 배우셨나요?
이 프로젝트의 특징은 무엇인가요?
만약 프로젝트의 특징이 너무 많다면, '특징' 섹션을 추가해서 이곳에 나열하세요.

<!--
1. 프로젝트 명 
2. 프로젝트 설명
여러분의 애플리케이션이 무엇을 하는지,
왜 그 기술을 사용했는지,
여러분이 당면했던 문제나 나중에 추가하고 싶은 기능이 무엇인지
3. 목차(길면 추가할 것)
4. 프로젝트 설치 및 실행 방법
만약 사용자가 따로 설치하거나 포스기와 같은 기계에서 실행해야 하는 프로젝트를 작업하고 있다면, 프로젝트를 설치할 수 있는 방법과 필요한 경우 dependencies를 포함해 작성해야 합니다.

개발 환경을 세팅하고 실행할 수 있는 단계적인 설명을 제공하세요.
5. 프로젝트 사용 방법
사용자/기여자들이 프로젝트를 이용할 수 있는 방법과 예시를 작성하세요. 예상 문제에 대해 항상 참고할 수 있는 곳을 마련함으로써 그들이 문제에 직면했을 때 쉽게 해결할 수 있을 것입니다.
프로젝트 실행 예시 화면의 스크린샷과 같은 시각 자료를 사용할 수도 있고 프로젝트에서 사용된 구조나 디자인 원칙을 추가할 수 있습니다.
프로젝트에 비밀번호나 유저 네임이 필요한 경우 계정을 적어두는 것도 좋은 방법입니다.

6. 팀원 및 참고 자료
만약 팀이나 조직 단위로 작업한 프로젝트라면 팀원들을 같이 기재하세요. 팀원들의 깃허브 프로필과 SNS 링크도 연결해야 합니다.

사용자가 프로젝트를 설치하는 데 도움을 줄 수 있는 튜토리얼이나 자료를 참고했다면 그런 링크도 같이 첨부해야 합니다.

이렇게 함으로써 감사를 표현함과 동시에 사람들이 프로젝트의 첫 번째 사본을 얻을 수 있습니다.

7. 라이센스
대부분의 리드미에서 라이센스는 가장 마지막으로 고려되는 부분입니다. 라이센스를 보고 다른 개발자들은 여러분의 프로젝트로 무엇을 할 수 있고 무엇을 할 수 없는지 알 수 있습니다.

우리는 작업하고 있는 프로젝트의 종류에 따라 다른 라이센스를 가지고 있습니다. 여러분이 고르는 라이센스에 따라서 프로젝트의 기여가 달라질 수 있습니다.


8. 뱃지
뱃지는 꼭 필요하진 않지만, 뱃지를 사용하면 다른 개발자들이 프로젝트에 대해 쉽게 알 수 있습니다.

이 섹션이 있으면 중요한 툴로 연결해 주거나 포크, 기여자, 오픈된 이슈 등 여러분의 프로젝트와 관련된 간단한 통계를 보여줄 수도 있습니다.

다음 사진은 뱃지 사용 방법을 보여주는 제 프로젝트의 스크린샷입니다:추가적인부분

-->