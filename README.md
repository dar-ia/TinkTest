# Демо-проект по автоматизации тестирования для сайта компании [Тинькофф](https://www.tinkoff.ru/)

### Содержание:
  1. [Стэк](#стэк)
  2. [Локальный запуск](#локальный-запуск)
  3. [Сборка в Jenkins](#сборка-в-jenkins)
  4. [Allure отчёт](#allure-отчёт)
  5. [Настройка уведомлений](#настройка-уведомлений)
  6. [Интеграция с TestOps](#интеграция-с-testops)
  7. [Интеграци с Jira](#интеграци-с-jira)
  8. [Запись выполнения теста на Selenoid](#запись-выполнения-теста-на-selenoid)

   ## Стэк
   <p align="center">
   <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original-wordmark.svg" height="60" width="60"/>
   <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/gradle/gradle-original.svg"  height="60" width="60"/>
   <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/junit/junit-plain-wordmark.svg" height="60" width="60"/>
   <img src="https://avatars.githubusercontent.com/u/43955696?s=48&v=4" height="60" width="60"/>
   <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/github/github-original-wordmark.svg"  height="60" width="60"/>
   <img src="https://avatars.githubusercontent.com/u/5879127?s=200&v=4" height="60" width="60"/>
   <img src="https://plugins.jetbrains.com/files/12513/451639/icon/pluginIcon.svg" height="50" width="50"/>
   <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/jenkins/jenkins-original.svg" height="60" width="60"/>
   <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/jira/jira-original-wordmark.svg" height="60" width="60"/>
   <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/Telegram_2019_Logo.svg/512px-Telegram_2019_Logo.svg.png" height="60" width="60"/>
   </p>
   
   Автотесты разработаны на языке ```Java``` c использованием фреймворков ```Selenide``` и ```JUnit5```. В качестве сборщика использовался ```Gradle```. В ```Jenkins``` сконфигурена джоба для запуска автотестов на удалённом сервере ```Selenoid``` и отправки результатов прогона в ```Telegram```. С помощью ```Allure``` настроено создание репортов. Так же настроена интеграция с ```Jira``` и ```TestOps``` для ведения тестовой документации.

   ## Локальный запуск
   Команда для локального запуска с дефолтными конфигами:
   ```
   gradle clean run_full_scope
   ```
  <p align="center">
   <img src=https://github.com/dar-ia/TinkTest/assets/64155350/438643e4-759d-4907-b1ec-9da3695b9069  width="700" height="200">
  </p>
  В команду можно передать параметры, например:
  ```
  gradle clean run_full_scope -DbaseUrl="https://www.tinkoff.ru/" -Dresolution="1920x1080"
  ```
  Список параметров:
  
  - baseUrl - сервер запуска
  - browser - браузер
  - version - версия браузера
  - resolution - разрешение экрана
  - remote - удалённый Selenoid

   ## Сборка в Jenkins
   В ```Jenkins``` настроена [джоба для запуска](https://jenkins.autotests.cloud/job/C25-dashulkes-readme-unit/). Для запуска необходимо перейти в ```Build with Parameters``` и нажать ```Build```. Параметры сборки:
  1. SCOPE - список скоупов тестов для запуска (по дефолту run_full_scope).
     - run_full_scope - скоуп всех тестов
     - run_business_cases - скоуп тестов на секцию для бизнес пользователей
     - run_mobile_cases - скоуп тестов на мобильного оператора
     - run_auth_cases - скоуп тестов на авторизацию
  2. BASEURL - сервер, на котором будут запускаться тесты (по дефолту https://www.tinkoff.ru/)
  3. BROWSER - браузер, в котором будет проходить прогон (по дефолту chrome)
  4. VERSION - версия браузера (по дефолту 122.0)
  5. RESOLUTION  - разрешение окна браузера (по дефолту 1920x1080)
  6. REMOTE - удалённый селеноид (по дефолту selenoid.autotests.cloud)
  7. NOTIFICATION_COMMENT - комментарий для allure нотификации (по дефолту Simple trigger of ${SCOPE})

После прогона в ```Build Hisory``` появится ссылка на сборку, которая будет содержать ссылки на тест документацию в ```Allure TestOps``` и ```Allure отчёт```.
  <p align="center">
   <img src=https://github.com/dar-ia/TinkTest/assets/64155350/14e62bb9-6904-4d6c-a56e-ff144b00cc05  width="900" height="450">
  </p>

  ## Allure отчёт
  Основная страница [отчёта](https://jenkins.autotests.cloud/job/C25-dashulkes-readme-unit/18/allure/) с основной информацией о прогоне
  <p align="center">
   <img src=https://github.com/dar-ia/TinkTest/assets/64155350/f5f6710e-eecb-4291-8344-af664895cd25  width="900" height="450">
  </p>  
  Suites содержит список прогнанных кейсов с их шагами:
  <p align="center">
   <img src=https://github.com/dar-ia/TinkTest/assets/64155350/141655f3-8092-4e0e-868d-a17d13c6d4e4 width="900" height="450">
  </p>  
  В конце прогона к каждому кейсу прикрепляются:
  - Последний скрин экрана
  - HTML контент страницы
  - Логи консоли
  - Видео запись прогона

  ## Настройка уведомлений
  В рамках проекта настроены уведомлений в Telegram. ля этого был создан бот
  <p align="center">
   <img src=https://github.com/dar-ia/TinkTest/assets/64155350/765eb31a-1140-4570-b107-12029876084b width="450" height="450">
  </p> 

  ## Интеграция с TestOps
  На странице Launches хранится список запусков
  <p align="center">
   <img src=https://github.com/dar-ia/TinkTest/assets/64155350/061a8b7d-ec80-4b91-8cda-88e0f2e3acea width="900" height="450">
  </p> 
  Список [тест кейсов](https://allure.autotests.cloud/project/4148/test-cases?treeId=8121) на Test Cases можно отобразить списком или по сьютам. 
  <p align="center">
   <img src=https://github.com/dar-ia/TinkTest/assets/64155350/652379c3-0f6a-47de-9306-6bdc2c878ae9 width="900" height="450">
  </p> 

  ## Интеграци с Jira
  ```Jira``` тикеты [слинкованы](https://jira.autotests.cloud/browse/HOMEWORK-1171) с кейсами на TestOps
  <p align="center">
   <img src=https://github.com/dar-ia/TinkTest/assets/64155350/a69fa12a-de6a-41a9-b97c-299486438a35 width="900" height="450">
  </p> 

  ## Запись выполнения теста на Selenoid


![bf1aacd007fe17dd930a449e24031a92](https://github.com/dar-ia/TinkTest/assets/64155350/a4933645-82d6-4ac3-b162-5299f39cc731)

  




