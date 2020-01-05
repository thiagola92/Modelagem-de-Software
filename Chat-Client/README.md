TL;DR: Exercicio de Modelagem de Software, fazer um chat.

## Introdução
O objetivo deste exercício é construir um chat que permita reuniões virtuais com um número qualquer de participantes.

## Descrição

Os clientes e o servidor do chat devem atender aos seguintes requisitos:

1. Vários clientes podem se conectar ao servidor.
2. O cliente deve ser capaz de enviar e receber mensagens.
3. Ao exibir uma mensagem, um cliente deve exibir, também, o identificador de quem a enviou.
4. Ao enviar uma mensagem, um cliente deve enviar, também, seu identificador.
5. Uma mensagem deve corresponder a uma linha (sequência de caracteres terminada por ENTER).
6. O cliente deve abrir uma conexão ao enviar a primeira mensagem para o servidor. Essa conexão deve permanecer aberta até que o cliente saia do chat.
7. A saída do chat dar-se-á quando o cliente digitar uma linha contendo os caracteres “###”. Nesse momento, a mensagem “###” deve ser enviada para o servidor e a conexão ser fechada.
8. O servidor deve usar um HashMap para guardar os sockets usados para comunicação com cada usuário. Ele deve, também, criar uma thread para cada usuário participante do chat. Essa thread será responsável pelo recebimento de mensagens.
9. Após o recebimento de uma mensagem, o servidor deverá enviá-la para todos os outros participantes, juntamente com o identificador do cliente que a enviou.
10. Ao receber uma mensagem “###”, o servidor deverá encerrar a thread do cliente em questão e retirar sua entrada do HashMap.
11. O servido deve ser encerrado quando a mensagem “###” for digitada em seu console.
