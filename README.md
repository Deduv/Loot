# LOOT — Gerenciador de Biblioteca de Jogo

![Java 21](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)
![Status](https://img.shields.io/badge/Status-Estrutura%20Inicial-0066CC?style=for-the-badge)

Projeto final da avaliação A3 da disciplina **Programação de Soluções Computacionais**, ministrada pelo **Prof. Lucas Santos** na **Universidade Salvador (UNIFACS)**.

---

## Sobre o Projeto

O **LOOT** é uma aplicação de linha de comando desenvolvida em Java puro para o gerenciamento de uma biblioteca pessoal de jogos. A proposta central é registrar títulos jogados, contabilizar horas acumuladas, armazenar avaliações pessoais e gerar análises simples de custo-benefício.

Para garantir que toda a equipe acompanhe o desenvolvimento e domine integralmente a lógica aplicada, o sistema foi projetado utilizando apenas recursos nativos da linguagem e conceitos trabalhados em sala de aula, executando diretamente no terminal com armazenamento em memória.

---

## Alinhamento com os Requisitos da Avaliação A3

A modelagem do sistema atende diretamente aos critérios definidos para o projeto final da disciplina:

| Requisito da Disciplina | Aplicação no Projeto LOOT |
| :--- | :--- |
| **Desenvolvimento em Java** | Implementado integralmente em Java 21 padrão, sem uso de frameworks externos. |
| **Menu no Console (CRUD)** | Operações de inclusão, consulta, alteração, exclusão e impressão da lista completa via terminal. |
| **Registro Individual por Ação** | O menu opera dentro de um laço `do-while` com `switch-case`. Cada operação executa uma única ação e retorna ao menu principal, sem laços contínuos presos no cadastro. |
| **Mínimo de 4 Classes** | Arquitetura organizada em 5 classes distintas distribuídas em pacotes: `Jogo`, `JogoPago`, `JogoGratuito`, `Biblioteca` e `Programa`. |
| **Mínimo de 5 Atributos** | A superclasse `Jogo` define 6 atributos base: `id`, `titulo`, `genero`, `horasJogadas`, `notaAvaliacao` e `anoLancamento`. |
| **Herança Funcional** | A classe abstrata `Jogo` é estendida pelas classes concretas `JogoPago` e `JogoGratuito`, que adicionam atributos específicos (`precoPago` e `gastoItensExtras`). |
| **Polimorfismo Funcional** | Implementação do método abstrato `analisarCustoBeneficio()`, sobrescrito (`@Override`) nas subclasses com regras de cálculo distintas, sem depender de `toString()`. |

---

## Tecnologias e Padrão de Ambiente

- **Linguagem:** Java 21 (JDK 21)
- **Interface de Interação:** Console / Terminal (`java.util.Scanner`)
- **Armazenamento:** Em memória durante a execução (`java.util.ArrayList`)
- **Controle de Versão:** Git e GitHub
- **Editor / IDE:** Livre escolha entre os integrantes (VS Code, IntelliJ IDEA, Eclipse ou NetBeans), com arquivos locais de configuração filtrados pelo `.gitignore`

---

## Arquitetura Planejada

O código-fonte será estruturado em pacotes com nomenclatura em português para facilitar a leitura e a manutenção em equipe:

```text
Loot/
├── .gitignore
├── README.md
└── src/
    ├── modelo/
    │   ├── Jogo.java           # Superclasse abstrata com atributos e assinatura polimórfica
    │   ├── JogoPago.java       # Subclasse para títulos comprados (cálculo de valor por hora)
    │   └── JogoGratuito.java   # Subclasse para títulos gratuitos (análise de gastos extras)
    ├── controle/
    │   └── Biblioteca.java     # Gerenciamento da lista de jogos e operações do CRUD
    └── principal/
        └── Programa.java       # Ponto de entrada (main), exibição do menu e leitura de entrada
```

*(Nota: o repositório encontra-se na etapa inicial de organização, antes da criação das classes e implementação do código-fonte).*

---

## Fluxo de Trabalho da Equipe no Git

Para evitar conflitos e manter a integridade do código principal, a equipe adota as seguintes práticas:

1. A branch `main` armazena apenas código compilado e testado.
2. Cada integrante cria uma branch paralela para desenvolver sua respectiva tarefa antes de integrar as alterações.
3. Antes de iniciar qualquer alteração local, atualiza-se a cópia com `git pull origin main`.

---

## Equipe

Trabalho desenvolvido pelos cinco integrantes abaixo:

- Cauã
- Eduardo Luís
- Eduardo Santana
- Ícaro
- Magno
