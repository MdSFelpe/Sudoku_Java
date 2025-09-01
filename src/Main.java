import model.Board;
import model.Space;
import service.BoardTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


//Todo esse Main possui que é preciso para ter o jogo funcionando no console
public class Main {

    private final static Scanner sc = new Scanner(System.in);

    private static Board board;

    private final static int BOARD_LIMIT = 9;


    public static void main(String[] args) {

        //Como os números vão ser dividos na stream
        final var positions = Stream.of(args)
                .collect(Collectors.toMap(
                        k -> k.split(";")[0],
                        v -> v.split(";")[1]
                ));



        //Menu de introdução para o jogo no console
        int option;
        boolean running = true;
        while (running) {
            System.out.println("======================================");
            System.out.println("      MENU PRINCIPAL DO JOGO");
            System.out.println("======================================");
            System.out.println("Selecione uma das opções a seguir:");
            System.out.println("1 - Iniciar um novo Jogo");
            System.out.println("2 - Colocar um novo Número");
            System.out.println("3 - Remover um Número");
            System.out.println("4 - Visualizar jogo atual");
            System.out.println("5 - Verificar status do jogo");
            System.out.println("6 - Limpar jogo");
            System.out.println("7 - Finalizar jogo");
            System.out.println("8 - Sair");
            System.out.println("======================================");
            System.out.print("Digite sua opção: ");

            option = sc.nextInt();

            switch (option){
                case 1 -> Startgame(positions); // O jogo coloca os números fixos para dar começo ao jogo
                case 2 -> inputNumber(); // Insirir um número na tabela, em algum espaço que não tenha número fixo
                case 3 -> removeNumber();  // remover um número que não seja fixo.
                case 4 -> showCurrentGame();  // Mostrar como está o board para o usuário
                case 5 -> showGameStatus(); // Saber o jogo foi ou não iniciado
                case 6 -> clearGame(); //Limpar o jogo, tirando os números fixos
                case 7 -> finishGame(); // Terminar o game caso tudo esteja encaixado de forma correta
                case 8 -> System.exit(0); // Sair das opções
                default ->  System.out.println("\n>> Opção inválida! Por favor, escolha um número entre 1 e 8.\n");

            }

        }
    }

    //Lógica para terminar o jogo
    private static void finishGame() {
        if (isNull(board)){
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        if (board.gameisFinished()){
            System.out.println("Parabéns você concluiu o jogo");
            showCurrentGame();
            board = null;
        }else if(board.haserrors()){
            System.out.println("Seu jogo contém erros, verifique seu board e ajuste-o");
        }else {
            System.out.println("Você ainda precisa preencher algum espaço");
        }
    }

    //Lógica retirar todos os números não fixos do board de uma só vez.
    private static void clearGame() {
        if (isNull(board)){
            System.out.println("Você limpou todos os números não fixos");
            return;
        }

        System.out.println("Tem certeza que deseja limpar seu jogo e perder todo o seu progresso ?");
        var confirm = sc.next();
        while(!confirm.equalsIgnoreCase("sim") && !confirm.equalsIgnoreCase("não")){
          System.out.println("Informe 'sim' ou 'não'");
           confirm = sc.next();
        }

        if(confirm.equalsIgnoreCase("sim")){
            board.reset();
        }
    }

    //Mostrar se o jogo já teve os números fixos colocados ou não para começar o jogo
    private static void showGameStatus() {
        if (isNull(board)){
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        System.out.printf("O jogo atualmente se encontra no status %s \n", board.getStatus().getLabel());
        if(board.haserrors()){
            System.out.println("O jogo contém erros");
        }else {
            System.out.println("O jogo ainda não contém erros");
        }
    }

    //Lógica para saber como o jogo se encontra no momento atual
    private static void showCurrentGame() {
        if (isNull(board)){
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        var args = new Object[81];
        var argPos = 0;
        for(int i = 0; i < BOARD_LIMIT; i++){
            for(var col: board.getSpace()){
                args[argPos ++ ] = " " + ((isNull(col.get(i).getActual())) ? " ": col.get(i).getActual());
            }
        }
        
        System.out.println("Seu jogo se encontra da seguinte forma");
        System.out.printf((BoardTemplate.BoardTemplate) + "\n", args);
    }
//Lógica para remover um número do board, para caso ele não seja fixo
    private static void removeNumber() {
        if (isNull(board)){
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        System.out.println("Informe a coluna que o número sera inserido");
        var col = runUntilGetValidNumber(0, 8);
        System.out.println("Informe a linha que o número sera inserido");
        var row = runUntilGetValidNumber(0, 8);
        System.out.printf("Informe o número que vai entrar na posição [%s, %s] \n", col, row);
        if(!board.clearValue(col, row)){
            System.out.printf("A posição [%s,%s] tem um valor fixo \n", col, row);
        }
    }

    //Lógica para insirir um número no board seguindo as regras do sudoku

    private static void inputNumber() {
        if (isNull(board)){
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        System.out.println("Informe a coluna que o número sera inserido");
        var col = runUntilGetValidNumber(0, 8);
        System.out.println("Informe a linha que o número sera inserido");
        var row = runUntilGetValidNumber(0, 8);
        System.out.printf("Informe o número que vai entrar na posição [%s, %s] \n", col, row);
        var value = runUntilGetValidNumber(1, 9);
        if(!board.changeValue(col, row, value)){
            System.out.printf("A posição [%s,%s] tem um valor fixo \n", col, row);
        }
    }

    //Lógica para colocar os números inicias fixos para começar o jogo
    private static void Startgame(Map<String, String> positions) {
        if(nonNull(board)){
            System.out.println("O jogo foi iniciado");
            return;
        }

        List<List<Space>> spaces = new ArrayList<>();
        for (int i = 0; i < BOARD_LIMIT; i++){
            spaces.add(new ArrayList<>());
            for(int j = 0; j < BOARD_LIMIT; j++){
                var positionConfing = positions.get("%s %s".formatted(i, j));
                var expected = Integer.parseInt(positionConfing.split(",")[0]);
                var fixed = Boolean.parseBoolean(positionConfing.split(",")[1]);
                var currentSpace = new Space(expected, fixed);
                spaces.get(i).add(currentSpace);
            }
        }

        board = new Board(spaces);
        System.out.println("O jogo esta pronto para começar");
    }

    private static int runUntilGetValidNumber(final int min, final int max){
        var current = sc.nextInt();
        while(current < min || current > max){
            System.out.printf("Informe um número entre %s e %s\n", min, max);
            current = sc.nextInt();
        }
        return current;
    }
}