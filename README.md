# App Copa Feminina 2023

## API

Para facilitar a dinâmica de integração do nosso App, criamos uma Pseudo-API usando o GitHub Pages, a qual está disponível na seguinte URL: 

## Desafio de Projeto (Lab) 😎

1. :white_check_mark: Responsabilidades dos módulos:
    * **app**: Contém as classes de nível de aplicativo e scaffolding que vinculam o restante da base de código.
    * **data**: abstração para o acesso à fontes de dados, organizada da seguinte forma:
        * ***data***: Neste módulo são declarados os DataSources "remote" e "local", bem como a implementação dos repositórios de acordo com a lógica de negócio necessária;
        * ***local***: Contém uma implementação do [Room](https://developer.android.com/training/data-storage/room) como fonte de dados local;
        * ***remote***: Implementação de uma fonte de dados remota usando o [Retrofit](https://square.github.io/retrofit/) como client HTTP.
    * **domain**: Neste módulo são declarados os use cases da aplicação;
    * **notification-scheduler**: Módulo específico para a criação das Notificações via Work Manager.
2. :white_large_square: Criar os casos de uso para as seguintes funcionalidades:
    * Buscar Partidas: `GetMatchesUseCase.kt`;
    * Habilitar Notificação: `EnableNotificationUseCase.kt`;
    * Desabilitar Notificação: `DisableNotificationUseCase.kt`.
3. :white_large_square: Criar o `MainViewModel.kt` para orquestrar as interações com a `MainActivity.kt`;
4. :white_large_square: Criar a `MainScreen.kt` para criar a UI por meio do Jetpack Compose;
5. :white_large_square: Integrar o ViewModel e Activity, através da observação de estados;
6. :white_large_square: Por fim, criar o Work Manager para orquestrar as Notificações Push localmente.

## Materia/Live de Apoio

Todos esses TODOs foram realizados nesta live incrível, com a participação de [pedrox-hs](https://github.com/pedrox-hs), [EzequielMessore](https://github.com/EzequielMessore), [igorbag](https://github.com/igorbag) e [falvojr](https://github.com/falvojr). Segue a gente aqui no GitHub e no LinkedIn 😉

**[Android Mobile Week #2: Aprenda a Criar um App com Listagem e Notificações dos Jogos do Brasil na Copa](https://youtu.be/30ZiJmCWliI)**

Bons estudos galera 😘
