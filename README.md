# Демо-проект по автоматизации тестирования для сайта компании [Тинькофф](https://www.tinkoff.ru/)
Данный проект является 

### Содержание:
  1. [Стэк](https://github.com/dar-ia/TinkTest/new/master?filename=README.md#%D1%81%D1%82%D1%8D%D0%BA)
  2. [Локальный запуск](https://github.com/dar-ia/TinkTest/new/master?filename=README.md#%D0%BB%D0%BE%D0%BA%D0%B0%D0%BB%D1%8C%D0%BD%D1%8B%D0%B9-%D0%B7%D0%B0%D0%BF%D1%83%D1%81%D0%BA)
  3. [Сборка в Jenkins](https://github.com/dar-ia/TinkTest/new/master?filename=README.md#%D1%81%D0%B1%D0%BE%D1%80%D0%BA%D0%B0-%D0%B2-jenkins)
  4. [Allure отчёт](https://github.com/dar-ia/TinkTest/new/master?filename=README.md#allure-%D0%BE%D1%82%D1%87%D1%91%D1%82)
  5. [Настройка уведомлений](https://github.com/dar-ia/TinkTest/new/master?filename=README.md#%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0-%D1%83%D0%B2%D0%B5%D0%B4%D0%BE%D0%BC%D0%BB%D0%B5%D0%BD%D0%B8%D0%B9)
  6. [Интеграция с TestOps](https://github.com/dar-ia/TinkTest/new/master?filename=README.md#%D0%B8%D0%BD%D1%82%D0%B5%D0%B3%D1%80%D0%B0%D1%86%D0%B8%D1%8F-%D1%81-testops)
  7. [Интеграци с Jira](https://github.com/dar-ia/TinkTest/new/master?filename=README.md#%D0%B8%D0%BD%D1%82%D0%B5%D0%B3%D1%80%D0%B0%D1%86%D0%B8-%D1%81-jira)
  8. [Запись выполнения теста на Selenoid](https://github.com/dar-ia/TinkTest/new/master?filename=README.md#%D0%B7%D0%B0%D0%BF%D0%B8%D1%81%D1%8C-%D0%B2%D1%8B%D0%BF%D0%BE%D0%BB%D0%BD%D0%B5%D0%BD%D0%B8%D1%8F-%D1%82%D0%B5%D1%81%D1%82%D0%B0-%D0%BD%D0%B0-selenoid)

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

   ## Локальный запуск
   Команда для локального запуска с дефолтными конфигами:
   ```
   gradle clean run_full_scope
   ```
  ![image](https://github.com/dar-ia/TinkTest/assets/64155350/438643e4-759d-4907-b1ec-9da3695b9069)
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
  3. BASEURL - сервер, на котором будут запускаться тесты (по дефолту https://www.tinkoff.ru/)
  4. REMOTE - удалённый селеноид (по дефолту selenoid.autotests.cloud)
  5. BROWSER - браузер, в котором будет проходить прогон (по дефолту chrome)
  6. VERSION - версия браузера (по дефолту 122.0)
  7. RESOLUTION  - разрешение окна браузера (по дефолту 1920x1080)
  8. NOTIFICATION_COMMENT - комментарий для allure нотификации (по дефолту Simple trigger of ${SCOPE})

После прогона в ```Build Hisory``` появится ссылка на сборку, которая будет содержать ссылки на тест документацию в ```Allure TestOps``` и ```Allure отчёт```.
![image](https://github.com/dar-ia/TinkTest/assets/64155350/14e62bb9-6904-4d6c-a56e-ff144b00cc05)

  ## Allure отчёт
  Основная страница отчёта  основной информацией о прогоне
  ![image](https://github.com/dar-ia/TinkTest/assets/64155350/f5f6710e-eecb-4291-8344-af664895cd25)
  Suites содержит список прогнанных кейсов с их шагами:
  ![image](https://github.com/dar-ia/TinkTest/assets/64155350/141655f3-8092-4e0e-868d-a17d13c6d4e4)
  В конце прогона к каждому кейсу прикрепляются:
  - Последний скрин экрана
  - HTML контент страницы
  - Логи консоли
  - Видео запись прогона

  ## Настройка уведомлений
  В рамках проекта настроены уведомлений в Telegram. ля этого был создан бот
  ![image](https://github.com/dar-ia/TinkTest/assets/64155350/765eb31a-1140-4570-b107-12029876084b)


  ## Интеграция с TestOps
  На странице Launches хранится список запусков
  ![image](https://github.com/dar-ia/TinkTest/assets/64155350/061a8b7d-ec80-4b91-8cda-88e0f2e3acea)
  Список тест кейсов на Test Cases можно отобразить списком или по сьютам. 
  ![image](https://github.com/dar-ia/TinkTest/assets/64155350/652379c3-0f6a-47de-9306-6bdc2c878ae9)

  ## Интеграци с Jira
  ```Jira``` тикеты слинкованы с кейсами на TestOps
  ![image](https://github.com/dar-ia/TinkTest/assets/64155350/a69fa12a-de6a-41a9-b97c-299486438a35)

  ## Запись выполнения теста на Selenoid


![bf1aacd007fe17dd930a449e24031a92](https://github.com/dar-ia/TinkTest/assets/64155350/a4933645-82d6-4ac3-b162-5299f39cc731)

  




