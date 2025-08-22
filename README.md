📚 StudyFlow: Gerenciador de Estudos

📖 Sobre o Projeto

O StudyFlow é um aplicativo Android desenvolvido para auxiliar estudantes a organizar e gerenciar suas rotinas de estudo .
O objetivo principal é oferecer uma plataforma simples, intuitiva e eficiente , permitindo adicionar, editar e acompanhar o progresso dos assuntos, mantendo todas as informações importantes em um só lugar.

🚀 Funcionalidades
🔐 Sistema de Autenticação

Cadastro de Usuário : Criação de contas com validação de e-mail (evita registros duplicados).

Login Seguro : Acesso com e-mail e senha.

Recuperação de Senha : Redefinição da senha diretamente pelo aplicativo.

📘 Gestão de Matérias

novos materiais (com nome e descrição).

Editar informações de materiais existentes.

Excluir matérias da lista.

💾 Armazenamento de Dados

SharedPreferences : utilizado para preferências do usuário e dados de sessão.

SQLite : persistência de usuários e materiais através de um banco de dados local.

🔔 Notificações

Sistema integrado de notificações para lembretes e avisos importantes.

🏗️ Arquitetura do Projeto

O projeto segue o padrão MVC (Model-View-Controller) , garantindo organização e fácil manutenção:

Modelo :
Classes de dados ( Materia, Usuario) e DatabaseHelper(operações do banco de dados).

View :
Interface do usuário desenvolvida em XML e controlada pelas Atividades .

Controller :
Camada interna que gerencia o fluxo entre Model e View (ex.: UsuarioController, MateriaController).

📱 Telas do Aplicativo

SplashActivity → Tela inicial de apresentação.

LoginActivity → Autenticação do usuário.

RegisterActivity → Cadastro de novos usuários.

ForgotPasswordActivity / ResetPasswordActivity → Recuperação e redefinição de senha.

MainActivity → Tela principal com a lista de materiais.

AddMateriaActivity → Adicionar nova matéria.

EditMateriaActivity → Editar ou excluir materiais existentes.

🛠️ Tecnologias Utilizadas

Linguagem : Java

Estrutura : Android SDK

Banco de Dados : SQLite (com DatabaseHelper)

Armazenamento de Sessão : SharedPreferences

Layout : XML (utilizando LinearLayout, CardView)
