# Copa do Mundo Feminina 2023

## API

Para facilitar a dinâmica de integração do nosso App, criamos uma Pseudo-API usando o GitHub Pages, a qual está disponível na seguinte URL: https://odisby.github.io/Copa_Feminina_2023/docs/en/api.json ou https://odisby.github.io/Copa_Feminina_2023/docs/pt/api.json

## Desafio de Projeto (Lab) 😎

1. :white_check_mark: Responsabilidades dos módulos:
    * **app**: Contém as classes de nível de aplicativo e scaffolding que vinculam o restante da base de código.
    * **data**: abstração para o acesso à fontes de dados, organizada da seguinte forma:
        * ***data***: Neste módulo são declarados os DataSources "remote" e "local", bem como a implementação dos repositórios de acordo com a lógica de negócio necessária;
        * ***local***: Contém uma implementação do [Room](https://developer.android.com/training/data-storage/room) como fonte de dados local;
        * ***remote***: Implementação de uma fonte de dados remota usando o [Retrofit](https://square.github.io/retrofit/) como client HTTP.
    * **domain**: Neste módulo são declarados os use cases da aplicação;
    * **notification-scheduler**: Módulo específico para a criação das Notificações via Work Manager.
2. :white_large_square: Usecases:
    * Buscar Partidas: `GetMatchesUseCase.kt`;
    * Habilitar Notificação: `EnableNotificationUseCase.kt`;
    * Desabilitar Notificação: `DisableNotificationUseCase.kt`.
3. :white_large_square: UI em `MainScreen.kt` feita por meio do Jetpack Compose;
4. :white_large_square: Notificações com o Work Manager.

## Inspiração

Estou realizando esse projeto com inspiração no app desafio Copa 2022 Android da Dio.

**[Github do projeto de inspiração](https://github.com/digitalinnovationone/copa-2022-android/tree/main)**
