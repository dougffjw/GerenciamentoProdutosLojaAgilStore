import java.util.List;
import java.util.Scanner;

public class Main {
    private static ProdutoDAO produtoDAO = new ProdutoDAO();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Adicionar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Atualizar Produto");
            System.out.println("4. Excluir Produto");
            System.out.println("5. Buscar Produto");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    adicionarProduto(scanner);
                    break;
                case 2:
                    listarProdutos();
                    break;
                case 3:
                    atualizarProduto(scanner);
                    break;
                case 4:
                    excluirProduto(scanner);
                    break;
                case 5:
                    buscarProduto(scanner);
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void adicionarProduto(Scanner scanner) {
        System.out.print("Nome do Produto: ");
        String nome = scanner.nextLine();
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        System.out.print("Quantidade em Estoque: ");
        int quantidade = scanner.nextInt();
        System.out.print("Preço: ");
        double preco = scanner.nextDouble();
        scanner.nextLine(); // Limpar o buffer

        Produto produto = new Produto(nome, categoria, quantidade, preco);
        produtoDAO.adicionarProduto(produto);
        System.out.println("Produto adicionado com sucesso!");
    }

    private static void listarProdutos() {
        List<Produto> produtos = produtoDAO.listarProdutos();
        System.out.printf("%-5s %-20s %-15s %-10s %-10s%n", "ID", "Nome", "Categoria", "Quantidade", "Preço");
        for (Produto produto : produtos) {
            System.out.printf("%-5d %-20s %-15s %-10d %-10.2f%n", produto.getId(), produto.getNome(),
                    produto.getCategoria(), produto.getQuantidade(), produto.getPreco());
        }
    }

    private static void atualizarProduto(Scanner scanner) {
        System.out.print("ID do Produto: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        Produto produto = produtoDAO.buscarProdutoPorId(id);
        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Novo Nome (deixe em branco para não alterar): ");
        String nome = scanner.nextLine();
        if (!nome.isEmpty()) {
            produto.setNome(nome);
        }

        System.out.print("Nova Categoria (deixe em branco para não alterar): ");
        String categoria = scanner.nextLine();
        if (!categoria.isEmpty()) {
            produto.setCategoria(categoria);
        }

        System.out.print("Nova Quantidade (deixe em branco para não alterar): ");
        String quantidadeStr = scanner.nextLine();
        if (!quantidadeStr.isEmpty()) {
            int quantidade = Integer.parseInt(quantidadeStr);
            produto.setQuantidade(quantidade);
        }

        System.out.print("Novo Preço (deixe em branco para não alterar): ");
        String precoStr = scanner.nextLine();
        if (!precoStr.isEmpty()) {
            double preco = Double.parseDouble(precoStr);
            produto.setPreco(preco);
        }

        produtoDAO.atualizarProduto(produto);
        System.out.println("Produto atualizado com sucesso!");
    }

    private static void excluirProduto(Scanner scanner) {
        System.out.print("ID do Produto: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        Produto produto = produtoDAO.buscarProdutoPorId(id);
        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Tem certeza que deseja excluir o produto (S/N)? ");
        String confirmacao = scanner.nextLine();
        if (confirmacao.equalsIgnoreCase("S")) {
            produtoDAO.excluirProduto(id);
            System.out.println("Produto excluído com sucesso!");
        } else {
            System.out.println("Ação cancelada.");
        }
    }

    private static void buscarProduto(Scanner scanner) {
        System.out.print("Buscar por ID ou Nome: ");
        String busca = scanner.nextLine();

        if (busca.matches("\\d+")) {
            int id = Integer.parseInt(busca);
            Produto produto = produtoDAO.buscarProdutoPorId(id);
            if (produto != null) {
                System.out.printf("ID: %d%nNome: %s%nCategoria: %s%nQuantidade: %d%nPreço: %.2f%n",
                        produto.getId(), produto.getNome(), produto.getCategoria(), produto.getQuantidade(),
                        produto.getPreco());
            } else {
                System.out.println("Produto não encontrado.");
            }
        } else {
            List<Produto> produtos = produtoDAO.buscarProdutoPorNome(busca);
            if (!produtos.isEmpty()) {
                for (Produto produto : produtos) {
                    System.out.printf("ID: %d%nNome: %s%nCategoria: %s%nQuantidade: %d%nPreço: %.2f%n",
                            produto.getId(), produto.getNome(), produto.getCategoria(), produto.getQuantidade(),
                            produto.getPreco());
                }
            } else {
                System.out.println("Produto não encontrado.");
            }
        }
    }
}
