# TestWorkForGPB

## Test Report in GitHub Pages
After the Tests are automatically run in the GitHub Actions pipeline, 
the report is published https://annalevitskii.github.io/TestTaskForGPB/

## Run in selenoid
Set up selenoid:\
(Check that Docker is up & Jenkins is not using port 8080)
-->  http://localhost:8080/  
### `gradlew runSelenoid`

Run tests in selenoid:
### `gradlew clean selenoid`

Tear down selenoid:
### `gradlew killSelenoid`


## Run locally options
Run smoke suite:
### `gradlew clean smoke`

Options run locally restAssured uis:
### `gradlew clean api`

Options run locally ui uis:
### `gradlew clean ui -Pbrowser=chrome -Pscreen=M_S`
### `gradlew clean ui -Pbrowser=chrome -Pscreen=T`
### `gradlew clean ui -Pbrowser=chrome -Pscreen=D_M`

### `gradlew clean ui -Pbrowser=firefox -Pscreen=D_S`
### `gradlew clean ui -Pbrowser=firefox -Pscreen=M_S`
### `gradlew clean ui -Pbrowser=firefox -Pscreen=T`

### `gradlew clean ui -Pbrowser=MicrosoftEdge -Pscreen=D_S`
### `gradlew clean ui -Pbrowser=MicrosoftEdge -Pscreen=M_S`
### `gradlew clean ui -Pbrowser=MicrosoftEdge -Pscreen=T`

### `gradlew clean ui -Pbrowser=safari -Pscreen=D_S`
### `gradlew clean ui -Pbrowser=safari -Pscreen=M_S`
### `gradlew clean ui -Pbrowser=safari -Pscreen=T`


Run Allure:
### `gradlew runAllure`


## Options run with different screen sizes
-Pscreen  --> com.core.models.enums.ScreenSize  --> com.core.providers.ScreenProvider\
DESKTOP_L(1920 x 1080),\
DESKTOP_M(1536 x 864),\
DESKTOP_S(1280 x 720),\
MOBILE_M(414 x 896),\
MOBILE_S(360 x 800),\
TABLET(768 x 1024);
