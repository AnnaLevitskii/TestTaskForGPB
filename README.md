# TestWorkForGPB

## Run locally options
Run smoke suite:
### `gradle clean smoke`

Options run locally restAssured uis:
### `gradle clean api`

Options run locally ui uis:
### `gradle clean ui -Pbrowser=chrome -Pscreen=M_S`
### `gradle clean ui -Pbrowser=chrome -Pscreen=T`
### `gradle clean ui -Pbrowser=chrome -Pscreen=D_M`

### `gradle clean ui -Pbrowser=firefox -Pscreen=D_S`
### `gradle clean ui -Pbrowser=firefox -Pscreen=M_S`
### `gradle clean ui -Pbrowser=firefox -Pscreen=T`

### `gradle clean ui -Pbrowser=MicrosoftEdge -Pscreen=D_S`
### `gradle clean ui -Pbrowser=MicrosoftEdge -Pscreen=M_S`
### `gradle clean ui -Pbrowser=MicrosoftEdge -Pscreen=T`

### `gradle clean ui -Pbrowser=safari -Pscreen=D_S`
### `gradle clean ui -Pbrowser=safari -Pscreen=M_S`
### `gradle clean ui -Pbrowser=safari -Pscreen=T`

Run all uis:
### `gradle clean test`


Run Allure:
### `gradle runAllure`


## Options run with different screen sizes
-Pscreen  --> com.core.models.enums.ScreenSize  --> com.core.providers.ScreenProvider\
DESKTOP_L(1920 x 1080),\
DESKTOP_M(1536 x 864),\
DESKTOP_S(1280 x 720),\
MOBILE_M(414 x 896),\
MOBILE_S(360 x 800),\
TABLET(768 x 1024);
