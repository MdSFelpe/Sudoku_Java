# Jogo de Sudoku em Java (Console & GUI) 🎮

Este projeto implementa um jogo de Sudoku funcional em Java, oferecendo duas formas de interação: uma interface de linha de comando (console) robusta e uma interface gráfica de usuário (GUI) construída com Java Swing.

O jogo permite iniciar um tabuleiro com números pré-definidos passados como argumentos, inserir e remover números (respeitando as regras do Sudoku e os números fixos), visualizar o tabuleiro formatado, verificar o status e validar a solução final. O projeto utiliza o padrão Observer (EventListener/NotifierService) para desacoplar a lógica do jogo da interface.

## ✨ Funcionalidades

* **Inicialização Configurável:** O estado inicial do tabuleiro (números fixos e solução esperada) é definido através de argumentos de linha de comando.
* **Interface de Console (`Main.java`):**
    * Menu interativo completo para jogar via terminal.
    * Visualização clara do tabuleiro formatado usando um template ASCII (`BoardTemplate`).
    * **Inserir Números:** Permite ao jogador colocar um número (1-9) em uma célula vazia (coluna/linha). Impede a alteração de números fixos.
    * **Remover Números:** Permite ao jogador limpar uma célula que ele preencheu. Impede a remoção de números fixos.
    * **Limpar Jogo:** Remove todos os números inseridos pelo jogador, mantendo apenas os fixos, com confirmação.
    * **Verificar Status:** Informa se o jogo está "Não iniciado", "Incompleto" ou "Completo".
    * **Verificar Erros:** Indica se o tabuleiro atual contém erros (números que não correspondem à solução esperada).
    * **Finalizar Jogo:** Verifica se o tabuleiro está completo e sem erros para declarar a vitória ou indicar o problema.
* **Interface Gráfica (`UIMain.java`):**
    * Ponto de entrada para uma versão visual do jogo utilizando Java Swing.
    * (Presumivelmente utiliza `EventListener` e `NotifierService` para atualizar a UI quando a lógica do jogo muda).

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java [24]
* **Interface Gráfica:** Java Swing (para a versão `UIMain`)

## 🏗️ Estrutura do Código

O projeto é organizado com as seguintes classes/componentes principais:

* **`model`:**
    * `Board`: Representa o tabuleiro 9x9 e contém a lógica central do jogo (regras, verificação de status, erros, finalização).
    * `Space`: Representa uma única célula do tabuleiro, armazenando o valor esperado, o valor atual e se é um número fixo.
    * `Gamestatus`: Enumeração que define os possíveis estados do jogo (`NON_STARTED`, `INCOMPLETE`, `COMPLETE`).
* **`service`:**
    * `BoardService`: Inicializa o `Board` a partir da configuração e atua como uma fachada para as operações do tabuleiro.
    * `BoardTemplate`: Contém o template ASCII para a exibição do tabuleiro no console.
    * `EventListener`, `EventEnum`, `NotifierService`: Implementação do padrão Observer para notificar mudanças de estado (ex: `CLEAR_SPACE`).
* **`ui` (para a versão GUI):**
    * Contém as classes relacionadas à interface gráfica Swing (ex: `MainFrame`, `MainPanel`, `MainScreen`).
* **Raiz:**
    * `Main.java`: Ponto de entrada e loop principal para a versão de **console**.
    * `UIMain.java`: Ponto de entrada para a versão com **interface gráfica (GUI)**.

## ▶️ Como Executar

O jogo pode ser executado de duas formas, ambas requerendo a configuração inicial do tabuleiro via argumentos de linha de comando.

### Formato dos Argumentos de Linha de Comando

Cada argumento deve representar uma célula no formato `"linha;coluna;esperado,fixo"` (ou `"linha,coluna"` como chave no `BoardService` - verifique seu código).
* `linha`: Número da linha (0 a 8).
* `coluna`: Número da coluna (0 a 8).
* `esperado`: O número correto para aquela célula (1 a 9, ou 0 para vazio inicialmente).
* `fixo`: `true` se for um número inicial visível, `false` se for uma célula vazia que o jogador deve preencher.

**É necessário fornecer todos os 81 argumentos para que o tabuleiro seja inicializado corretamente.**

### 1. Versão Console

Compile e execute a classe `Main.java` fornecendo os 81 argumentos.

**Exemplo de Comando:**
```bash
java Main "0;0;5,true" "0;1;3,false" "0;2;0,false" # ... (mais 78 argumentos)
```
👤 Autor
Fellype Augusto de Oliveira Santos

GitHub: [(https://github.com/MdSFelpe)]

LinkedIn: [(https://www.linkedin.com/in/fellype-augusto-1b145326b/)]
