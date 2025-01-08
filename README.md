
# GerenciamentoProdutosLojaAgilStore 

Este é um projeto de gerenciamento de produtos utilizando Java e MySQL.

## Como Rodar a Aplicação Localmente

### Pré-requisitos

- Java JDK 8 ou superior instalado
- MySQL instalado e em execução

### Passos

1. **Clone o Repositório:**
   ```sh
   git clone https://github.com/dougffjw/GerenciamentoProdutosLojaAgilStore.git
   cd gerenciador

2. **Configure o Banco de Dados:**

- Crie o banco de dados e a tabela executando os seguintes comandos no MySQL:
 ```sh
CREATE DATABASE IF NOT EXISTS agilstoredb;
USE agilstoredb;

CREATE TABLE IF NOT EXISTS produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    categoria VARCHAR(100),
    quantidade INT,
    preco DOUBLE
);

 ```
 3. **Compile e Execute a Aplicação:**

- Compile a aplicação:
```sh
javac -d bin src/*.java
```
- Execute a aplicação:

```sh
java -cp bin Main