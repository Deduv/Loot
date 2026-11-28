# LOOT — Biblioteca Pessoal de Jogos

Projeto final da avaliação A3 da disciplina **Programação de Soluções Computacionais**, ministrada pelo **Prof. Lucas Santos**, na **Universidade Salvador (UNIFACS)**.

O LOOT é um sistema de console em Java para registrar jogos já jogados ou em andamento, acompanhar horas acumuladas, guardar notas de avaliação e calcular o custo-benefício de cada título.

## Intenções do projeto

Em vez de fazer um cadastro genérico de "produtos" ou "clientes", escolhemos um tema próximo do dia a dia da equipe: organizar uma biblioteca pessoal de jogos. A ideia era que as regras do próprio domínio justificassem o uso de Orientação a Objetos sem forçar a barra.

Separar **jogos pagos** de **jogos gratuitos (free-to-play)** permitiu aplicar herança e polimorfismo com propósito real, já que cada tipo calcula o custo-benefício de um jeito diferente (preço de compra por hora jogada versus gastos com itens extras). Além disso, decidimos manter o projeto em Java puro, sem frameworks nem banco de dados, para focar na base da linguagem e garantir que todos os integrantes conseguissem ler e explicar qualquer parte do código.

## Como funciona

Ao executar o programa, um menu interativo é exibido no terminal com as operações de um CRUD completo:

1. **Cadastrar jogo:** o usuário escolhe se o jogo é pago (`1`) ou gratuito (`2`) e informa título, gênero, horas jogadas, nota (0 a 10), ano de lançamento e o valor correspondente (preço pago ou gasto com itens extras). O ID é gerado automaticamente.
2. **Listar todos os jogos:** exibe todos os títulos cadastrados com resumo, detalhes específicos do tipo de jogo e análise de custo-benefício.
3. **Buscar jogo por ID:** localiza e mostra os dados de um jogo específico.
4. **Atualizar jogo:** permite alterar as horas jogadas e a nota de um título já cadastrado.
5. **Excluir jogo por ID:** remove um título da biblioteca.
0. **Sair:** encerra o programa.

Cada operação roda uma vez e devolve o controle ao menu principal. Os dados ficam salvos em memória (`ArrayList`) enquanto o programa está aberto.

### Regras de cálculo e validação

- **Jogo Pago:** se o preço for `R$ 0,00`, informa que foi obtido sem custo; se ainda tiver `0` horas jogadas, informa o valor investido; caso contrário, divide o preço pago pelas horas jogadas (`R$/h`).
- **Jogo Gratuito:** se não houve gasto extra, exibe as horas aproveitadas gratuitamente; se houve gasto mas ainda não foi jogado, informa o total gasto; caso contrário, calcula o custo por hora em itens extras.
- **Validações:** o sistema aceita decimais com ponto ou vírgula (ex.: `9.5` ou `9,5`), impede campos vazios, bloqueia números negativos (horas, preço, gasto), limita notas entre `0` e `10`, valida o ano de lançamento entre `1950` e o ano atual e trata entradas inválidas sem fechar o programa.

## Estrutura do projeto

O código está separado em quatro pacotes em português, cada um com uma responsabilidade:

```text
Loot/
├── README.md
├── build.xml
├── manifest.mf
├── nbproject/
└── src/
    ├── modelo/
    │   ├── Jogo.java           # Superclasse abstrata com atributos base, validações e métodos abstratos
    │   ├── JogoPago.java       # Subclasse de Jogo com preço pago e cálculo de custo por hora
    │   └── JogoGratuito.java   # Subclasse de Jogo com gastos extras e análise F2P
    ├── controle/
    │   └── Biblioteca.java     # Armazena a lista de jogos e executa inclusão, busca, alteração e exclusão
    ├── visao/
    │   └── Menu.java           # Interface de console, leitura e tratamento das entradas do usuário
    └── programa/
        └── Programa.java       # Ponto de entrada (main), apenas inicializa e encerra o Menu
```

## Pré-requisitos e dependências

- **Java Development Kit (JDK) 21** ou superior instalado e configurado no `PATH`.
- **Git** (opcional, apenas para clonar o repositório; também é possível baixar o ZIP direto pelo GitHub).
- **Sem dependências externas:** o projeto utiliza apenas a biblioteca padrão do Java (JDK). Não é preciso instalar banco de dados, Maven ou Gradle.

## Como baixar e rodar na sua máquina

### 1. Verifique se o Java 21 está instalado

```bash
java -version
javac -version
```

### 2. Baixe o projeto

Com Git:

```bash
git clone https://github.com/Deduv/Loot.git
cd Loot
```

Se preferir, baixe o arquivo `.zip` no GitHub, extraia e abra o terminal dentro da pasta `Loot`.

### 3. Compile e execute pelo terminal

**Linux / macOS:**

```bash
mkdir -p bin
javac -encoding UTF-8 -d bin src/*/*.java
java -cp bin programa.Programa
```

**Windows (PowerShell ou Prompt de Comando):**

```powershell
mkdir bin
javac -encoding UTF-8 -d bin src\modelo\*.java src\controle\*.java src\visao\*.java src\programa\*.java
java -cp bin programa.Programa
```

### 4. Executando por uma IDE (opcional)

O projeto pode ser aberto diretamente no **NetBeans** (já inclui `nbproject/` e `build.xml`), **VS Code**, **IntelliJ IDEA** ou **Eclipse**. Basta apontar para o JDK 21 e executar a classe `src/programa/Programa.java`.

## Assuntos de Java e POO abordados

- **Classes, objetos e construtores:** modelagem das entidades e encadeamento de construtores com `super(...)`.
- **Encapsulamento:** atributos declarados como `private`, acessados por getters e setters que validam os dados antes de gravar, além de `Collections.unmodifiableList(...)` na listagem para proteger a lista interna da `Biblioteca`.
- **Abstração:** classe abstrata `Jogo` definindo o contrato comum (`analisarCustoBeneficio()` e `exibirDetalhes()`) sem permitir instanciar um jogo genérico.
- **Herança:** `JogoPago` e `JogoGratuito` estendendo `Jogo` (`extends`) e reaproveitando atributos, validações e o resumo base.
- **Polimorfismo:** a `Biblioteca` trabalha com uma única coleção `ArrayList<Jogo>`, e o `Menu` chama `exibirDetalhes()` e `analisarCustoBeneficio()` diretamente pela referência de `Jogo`, sem usar `toString()` nem `instanceof`.
- **Estruturas de controle e coleções:** uso de `ArrayList`, `List`, `do-while`, `for-each` e `switch-case`.
- **Tratamento de exceções:** lançamento de `IllegalArgumentException` nas regras de negócio e captura no `Menu` (`try/catch`), junto com `NumberFormatException` e `NoSuchElementException` para evitar que entradas erradas encerrem a execução.
- **API padrão do Java:** `Scanner` para entrada no console, `String.format` para formatação de saída e `java.time.Year` para validação do ano de lançamento.
- **Organização em pacotes:** separação entre `modelo`, `controle`, `visao` e `programa`.

## O que aprendemos

- **Separar responsabilidades facilita muito a manutenção:** no início, o menu ficava dentro da classe `Programa` e a `Biblioteca` imprimia direto no console. Quando criamos `visao/Menu.java` e deixamos a `Biblioteca` só retornando dados, o código ficou mais limpo e bem mais fácil de alterar sem quebrar outras partes.
- **Polimorfismo na prática evita condicionais espalhadas:** substituir verificações com `instanceof` pelo método abstrato `exibirDetalhes()` mostrou na prática por que o polimorfismo reduz acoplamento.
- **Validar entrada de usuário dá trabalho real:** testar o programa com letras no lugar de números, decimais com ponto e vírgula, notas acima de 10 e valores negativos revelou vários pontos onde o sistema travava antes de tratarmos as exceções corretamente.
- **Colaboração com Git e GitHub:** trabalhar em quatro pessoas no mesmo repositório exigiu combinar estrutura de pastas, dividir responsabilidades por classe e manter o histórico organizado.

## Equipe

**Disciplina:** Programação de Soluções Computacionais — UNIFACS  
**Professor:** Lucas Santos

| Integrante | RA | Responsabilidade |
| :--- | :--- | :--- |
| **Eduardo Santana dos Santos** | 12725222129 | `controle/Biblioteca.java` e `visao/Menu.java` |
| **Ícaro Santos Silva** | 1272627053 | `modelo/Jogo.java` e `programa/Programa.java` |
| **Eduardo Luis de Oliveira Mendonça** | 12725217607 | `modelo/JogoGratuito.java` e `modelo/JogoPago.java` |
| **Saulo Magno Santos de Jesus** | 1272322763 | `programa/Programa.java` |
