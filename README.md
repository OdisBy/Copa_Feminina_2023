# Copa do Mundo Feminina 2023

<div style="display: flex;">
  <img src="https://github.com/OdisBy/Copa_Feminina_2023/assets/69876102/87abbe39-7a81-4239-97ec-346f73da9532" alt="Screenshot_20230724_014440" style="width: 24%;">
  <img src="https://github.com/OdisBy/Copa_Feminina_2023/assets/69876102/8d62c15b-1f1a-447c-b93e-1468e792fd52" alt="Screenshot_20230724_015337" style="width: 24%;">
  <img src="https://github.com/OdisBy/Copa_Feminina_2023/assets/69876102/b5575fb0-c7ca-4269-8fd0-5d49e3664308" alt="Screenshot_20230724_015313" style="width: 24%;">
  <img src="https://github.com/OdisBy/Copa_Feminina_2023/assets/69876102/55f1df66-8f1b-467a-ad47-aad664101445" alt="Screenshot_20230724_015313" style="width: 24%;">
</div>

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
2. :white_check_mark: Usecases:
    * Buscar Partidas: `GetMatchesUseCase.kt`;
    * Habilitar Notificação: `EnableNotificationUseCase.kt`;
    * Desabilitar Notificação: `DisableNotificationUseCase.kt`.
3. :white_check_mark: UI em `MainScreen.kt` feita por meio do Jetpack Compose;
4. :white_check_mark: Notificações com o Work Manager.

## Inspiração

Estou realizando esse projeto com inspiração no app desafio Copa 2022 Android da Dio.

**[Github do projeto de inspiração](https://github.com/digitalinnovationone/copa-2022-android/tree/main)**
