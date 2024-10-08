# SISTEMA DE GERENCIAMENTO DE HOTEL

### ESCOPO:

- Desenvolver um sistema completo para gerenciar reservas, clientes e pagamentos em um hotel. O sistema terá uma interface gráfica em Java que permitirá aos funcionários realizar operações diárias de forma eficiente.

### OBJETIVOS: 
**Específicos:**

- Cadastro de Hóspedes;
- Permitir que os funcionários cadastrem, editem e excluam os hóspedes;
- Realizar a reserva dos quartos e o pagamento da reserva;
- Listar hóspedes e permitir baixar um relatório;
- Listar todas as reservas;
- Permitir que ao fazer a reserva do quarto, crie um arquivo com o número da reserva;
- Permitir que ao fazer o pagamento da reserva, crie um arquivo com o recibo.

**Mensuráveis:**

- Garantir que o sistema cadastre até 5 hospede;
- Garantir que o funcionário realize até 10 reservas;
- Garantir que o funcionário finalize até 5 pagamentos das reservas;
- Garantir que até 3 arquivos de número de reserva e recibo sejam criados;
- Garantir que o sistema não permita realizar a reserva de um quarto nas mesmas datas.

**Atingíveis:**

- Utilizar Java e bibliotecas bem documentadas (Java Swing...) para assegurar o cumprimento dos prazos e qualidade do código;
- Fazer uma interface fácil e intuitiva de usar.

**Relevantes:**

- Facilitar o processo de reserva de quartos de hotel, cadastro de hóspedes e realização de pagamentos e relatórios.

# Análise de Riscos


## 1. Problemas Técnicos
**Risco:** O sistema pode apresentar lentidão, bugs ou até parar de funcionar, o que pode irritar os usuários.

**Mitigação:**
- Fazer muitos testes antes de lançar a plataforma.
- Monitorar o sistema o tempo todo para identificar e corrigir problemas rapidamente.
- Fazer backup dos dados regularmente e ter um plano para recuperar informações se algo der errado.

## 2. Segurança dos Dados
**Risco:** Dados pessoais e financeiros dos usuários podem ser roubados ou vazados.

**Mitigação:**
- Proteger os dados com criptografia.
- Seguir as regras de proteção de dados, como GDPR e LGPD.
- Fazer revisões regulares de segurança para manter o sistema seguro.


### RECURSOS:

- Postgres Database;
- Visual Studio Code;
- Miro (Diagramas);
- Mockito (Testes Unitários);
- Git/GitHub;
- Equipe (Gerente de Projeto,Desenvolvedores Backend (2-3 membros),Desenvolvedores Frontend (2 membros),Designer UX/UI,Tester/QA (Controle de Qualidade)).


### DIAGRAMA DE USO:
<p><img src="img/DiagramaUso.png" width="500px">

### DIAGRAMA DE CLASSE:
<p><img src="img/DiagramaClasse.png" width="500px">

### DIAGRAMA DE FLUXO:

<p><img src="img/DiagramaFluxo.png" width="500px">

### MANUAL DO USUÁRIO:
