# Sistema de Gerenciamento de Receitas - WebService SOAP

Um sistema completo de gerenciamento de receitas culinárias desenvolvido em Java utilizando WebServices SOAP. O projeto permite cadastrar, editar, visualizar e remover receitas e ingredientes através de uma API SOAP.

## 📋 Funcionalidades

### Gerenciamento de Ingredientes
- ✅ Criar novos ingredientes
- ✅ Listar todos os ingredientes
- ✅ Buscar ingrediente por nome
- ✅ Atualizar informações do ingrediente
- ✅ Remover ingredientes

### Gerenciamento de Receitas
- ✅ Criar novas receitas com instruções
- ✅ Listar todas as receitas
- ✅ Buscar receitas por nome ou ID
- ✅ Atualizar receitas existentes
- ✅ Remover receitas
- ✅ Adicionar ingredientes às receitas (com quantidade e unidade)
- ✅ Remover ingredientes das receitas
- ✅ Atualizar quantidade/unidade de ingredientes nas receitas

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas:

```
src/
├── model/                          # Modelos de dados
│   ├── Recipe.java                 # Entidade Receita
│   ├── Ingredient.java             # Entidade Ingrediente
│   └── RecipeIngredient.java       # Relacionamento Receita-Ingrediente
├── serv/                           # Serviços SOAP
│   ├── ingredient/                 # WebService de Ingredientes
│   │   ├── IngredientServer.java
│   │   └── IngredientServerImpl.java
│   ├── recipe/                     # WebService de Receitas
│   │   ├── RecipeServer.java
│   │   └── RecipeServerImpl.java
│   └── publisher/                  # Publicador dos WebServices
│       └── ServerPublisher.java
├── controller/                     # Controllers (compilados)
└── App.java                        # Cliente de demonstração
```

## 🛠️ Tecnologias Utilizadas

- **Java 8**
- **JAX-WS** (Java API for XML Web Services)
- **JAXB** (Java Architecture for XML Binding)
- **SOAP** (Simple Object Access Protocol)
- **XML** para serialização de dados

## 📦 Dependências

As dependências estão localizadas na pasta `lib/` e foram fornecidas pelo professor da matéria:
- `javax.activation-api-1.2.0.jar`
- `javax.annotation-api-1.3.2.jar`
- `javax.xml.soap-api-1.4.0.jar`
- `jaxb-api-2.3.1.jar`
- `jaxws-api-2.3.1.jar`

## 🚀 Como Executar

### 1. Iniciar o Servidor SOAP

```bash
# Navegar até o diretório do projeto
cd trabalho-soap

# Iniciar o servidor
java -cp "bin;lib/*" serv.publisher.ServerPublisher
```

O servidor será iniciado nas seguintes URLs:
- **Ingredientes**: `http://127.0.0.1:8080/api/ingredient`
- **Receitas**: `http://127.0.0.1:8080/api/recipe`

### 2. Executar o Cliente de Demonstração

```bash
# Em outro terminal, executar o cliente
java -cp "bin;lib/*" App
```

### 3. Acessar os WSDLs

- **WSDL Ingredientes**: `http://127.0.0.1:8080/api/ingredient?wsdl`
- **WSDL Receitas**: `http://127.0.0.1:8080/api/recipe?wsdl`

## 🧪 Exemplo de Uso

O arquivo `App.java` contém um exemplo completo de utilização que:

1. Cria ingredientes básicos (Farinha, Leite, Chocolate, etc.)
2. Cria receitas variadas:
   - Chocolate Quente
   - Bolo de Chocolate
   - Panqueca Simples
   - Vitamina de Morango
3. Adiciona ingredientes às receitas com quantidades específicas
4. Lista todas as receitas cadastradas
5. Remove ingredientes e receitas para demonstrar exclusões

## 📡 Endpoints SOAP

### IngredientServer
- `createIngredient(String name)`
- `returnIngredientByName(String name)`
- `returnAllIngredients()`
- `updateIngredient(String currentName, String newName)`
- `deleteIngredient(String name)`

### RecipeServer
- `createRecipe(String name, String instructions)`
- `returnRecipeById(UUID id)`
- `returnAllRecipes()`
- `returnRecipesByName(String name)`
- `updateRecipe(UUID id, String newName, String newInstructions)`
- `deleteRecipe(UUID id)`
- `addIngredientToRecipe(UUID recipeId, String ingredientName, int quantity, String unit)`
- `removeIngredientFromRecipe(UUID recipeId, String ingredientName)`
- `updateIngredientInRecipe(UUID recipeId, String ingredientName, int newQuantity, String newUnit)`

## 🏃‍♀️ Executando no VS Code

Este projeto está configurado para execução no VS Code:

1. Abra o projeto no VS Code
2. Certifique-se de que a extensão Java está instalada
3. Use `F5` para executar em modo debug ou `Ctrl+F5` para execução normal
4. Escolha a classe principal (`ServerPublisher` para servidor ou `App` para cliente)

## 📁 Estrutura de Pastas

- `src/`: Código-fonte Java
- `bin/`: Arquivos compilados (.class)
- `lib/`: Dependências JAR
- `.vscode/`: Configurações do VS Code

## 📄 Licença

Este projeto foi desenvolvido para fins educacionais como parte da disciplina de Programação Web II (2025/2) da Universidade Regional de Blumenau (FURB).
